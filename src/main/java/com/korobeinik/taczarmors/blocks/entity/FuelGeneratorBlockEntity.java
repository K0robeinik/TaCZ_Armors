package com.korobeinik.taczarmors.blocks.entity;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.blocks.FuelGeneratorBlock;
import com.korobeinik.taczarmors.client.menu.FuelGeneratorBlockMenu;
import com.korobeinik.taczarmors.init.BlockEntityTypeInit;
import com.korobeinik.taczarmors.util.CustomEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FuelGeneratorBlockEntity extends BlockEntity implements MenuProvider {
    protected final ContainerData data;
    private int burnTime, burnDuration;
    private final ItemStackHandler inventory = new ItemStackHandler(2){
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            FuelGeneratorBlockEntity.this.setChanged();
        }
    };
    private LazyOptional<ItemStackHandler> lazyInventory = LazyOptional.empty();
    private final CustomEnergyStorage energyStorage = new CustomEnergyStorage(10000, 0, 500, 0);
    private LazyOptional<CustomEnergyStorage> lazyEnergy = LazyOptional.empty();

    public FuelGeneratorBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityTypeInit.FUEL_GENERATOR_BLOCK_ENTITY.get(), pos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> burnTime;
                    case 1 -> burnDuration;
                    case 2 -> energyStorage.getEnergyStored();
                    case 3 -> energyStorage.getMaxEnergyStored();
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> burnTime = pValue;
                    case 1 -> burnDuration = pValue;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public void onLoad() {
        super.onLoad();
        this.lazyInventory = LazyOptional.of(() -> this.inventory);
        this.lazyEnergy = LazyOptional.of(() -> this.energyStorage);
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        CompoundTag data = nbt.getCompound(TaczArmors.MODID);
        this.inventory.deserializeNBT(data.getCompound("Inventory"));
        this.energyStorage.deserializeNBT(data.get("Energy"));
        burnTime = data.getInt("burnTime");
        burnDuration = data.getInt("burnDuration");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);
        CompoundTag data = nbt.getCompound(TaczArmors.MODID);
        data.put("Inventory", this.inventory.serializeNBT());
        data.put("Energy", this.energyStorage.serializeNBT());
        data.putInt("burnTime", burnTime);
        data.putInt("burnDuration", burnDuration);

        nbt.put(TaczArmors.MODID, data);
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if (pLevel == null || pLevel.isClientSide()) return;
        boolean flag = isBurning();
        boolean shouldChange = false;
        if (!isMaxEnergy()) {
            if (isBurning()) {
                this.burnTime--;
                this.energyStorage.addEnergy(50);
                shouldChange = true;
            }
            if (!isBurning()){
                ItemStack fuel = this.inventory.getStackInSlot(1);
                if (isFuel(fuel)){
                    this.burnTime = this.burnDuration = getFuelBurnTime(fuel);
                    fuel.shrink(1);
                    if (fuel.isEmpty()) this.inventory.setStackInSlot(1, fuel.getCraftingRemainingItem());
                    shouldChange = true;
                }
            }
        }
        if (energyStorage.getEnergyStored()>0){
            ItemStack energyItem = this.inventory.getStackInSlot(0);
            if (!energyItem.isEmpty()) this.chargeItem(energyItem);
            shouldChange = true;
        }
        if (flag != isBurning()){
            //System.out.println("Changed lit to: " + isBurning());
            pLevel.setBlock(pPos, pState.setValue(FuelGeneratorBlock.LIT, isBurning()), 3);
            shouldChange = true;
        }
        if (shouldChange) setChanged(pLevel, pPos, pState);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(this.inventory.getSlots());
        for(int i = 0; i < this.inventory.getSlots(); i++) {
            inventory.setItem(i, this.inventory.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        saveAdditional(nbt);
        return nbt;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.ITEM_HANDLER){
            return this.lazyInventory.cast();
        }
        else if (cap == ForgeCapabilities.ENERGY){
            return this.lazyEnergy.cast();
        }
        return super.getCapability(cap);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.lazyInventory.invalidate();
        this.lazyEnergy.invalidate();
    }

    public ItemStackHandler getInventory() {
        return this.inventory;
    }

    public ItemStack getStackInSlot(int slot){
        return this.inventory.getStackInSlot(slot);
    }

    public void setStackInSlot(int slot, ItemStack stack){
        this.inventory.setStackInSlot(slot, stack);
    }

    public LazyOptional<ItemStackHandler> getLazyInventory() {
        return this.lazyInventory;
    }

    public CustomEnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    public LazyOptional<CustomEnergyStorage> getLazyEnergy() {
        return lazyEnergy;
    }

    private boolean isBurning() {
        return this.data.get(0) > 0;
    }

    private boolean isMaxEnergy() {return this.energyStorage.getEnergyStored()>=this.energyStorage.getMaxEnergyStored();}

    public static boolean isFuel(ItemStack pStack) {
        return getFuelBurnTime(pStack) > 0;
    }

    public static int getFuelBurnTime(ItemStack stack) {
        return net.minecraftforge.common.ForgeHooks.getBurnTime(stack, RecipeType.SMELTING);
    }

    protected void chargeItem(ItemStack stack) {
        if (stack.isEmpty()) return;
        stack.getCapability(ForgeCapabilities.ENERGY, null).ifPresent(iEnergyStorage -> {
            int to = iEnergyStorage.receiveEnergy(energyStorage.tryTransfer(), false);
            energyStorage.extractEnergy(to, false);
        });
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal("Fuel Generator");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
        return new FuelGeneratorBlockMenu(pContainerId, pPlayerInventory, this, this.data);
    }
}
