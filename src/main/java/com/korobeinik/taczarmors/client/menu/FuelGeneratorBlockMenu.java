package com.korobeinik.taczarmors.client.menu;

import com.korobeinik.taczarmors.blocks.entity.FuelGeneratorBlockEntity;
import com.korobeinik.taczarmors.init.BlockInit;
import com.korobeinik.taczarmors.init.MenuInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class FuelGeneratorBlockMenu extends AbstractContainerMenu {
    private final FuelGeneratorBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;
    public FuelGeneratorBlockMenu(int containerId, Inventory inventory, FriendlyByteBuf buffer) {
        this(containerId, inventory, inventory.player.level().getBlockEntity(buffer.readBlockPos()), new SimpleContainerData(4));
    }

    public FuelGeneratorBlockMenu(int containerId, Inventory inventory, BlockEntity blockEntity, ContainerData data) {
        super(MenuInit.FUEL_GENERATOR_MENU.get(), containerId);
        if (blockEntity instanceof FuelGeneratorBlockEntity be){
            this.blockEntity = be;
        }
        else {
            throw new IllegalStateException("Incorrect block entity class (%s) passed into ExampleMenu!".formatted(blockEntity.getClass().getCanonicalName()));
        }
        this.level = inventory.player.level();
        this.data = data;

        createPlayerHotbar(inventory);
        createPlayerInventory(inventory);
        createBlockEntityInventory(be);

        addDataSlots(data);
    }

    public boolean isBurning(){
        return data.get(0) > 0;
    }

    public int getBurningTime(){
        int i = this.data.get(1);
        if (i == 0) {
            i = 200;
        }

        return this.data.get(0) * 13 / i;
    }

    public int getEnergyStored(){
        int i = getMaxEnergy();
        return getEnergy() * 54 / i;
    }

    public int getEnergy() {
        return this.data.get(2);
    }

    public int getMaxEnergy() {
        return this.data.get(3);
    }

    private void createBlockEntityInventory(FuelGeneratorBlockEntity be) {
        be.getLazyInventory().ifPresent(inventory -> {
            addSlot(new SlotItemHandler(inventory, 0, 65, 17)); //energy items
            addSlot(new SlotItemHandler(inventory, 1, 65, 53)); //fuel
        });
    }

    private void createPlayerInventory(Inventory inventory) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(inventory, 9 + column + (row * 9), 8 + (column * 18), 84 + (row * 18)));
            }
        }
    }

    private void createPlayerHotbar(Inventory inventory) {
        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(inventory, column, 8 + (column * 18), 142));
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int pIndex) {
        Slot fromSlot = getSlot(pIndex);
        ItemStack fromStack = fromSlot.getItem();

        if (fromStack.getCount() <= 0) fromSlot.set(ItemStack.EMPTY);
        if (!fromSlot.hasItem()) return ItemStack.EMPTY;

        ItemStack copyFromStack = fromStack.copy();

        if (pIndex < 36){
            if (this.isFuel(fromStack)){
                if (!moveItemStackTo(fromStack, 37, 38, false)){
                    return ItemStack.EMPTY;
                }
            }
            if (!moveItemStackTo(fromStack, 36, 38, false)){
                return ItemStack.EMPTY;
            }
        }
        else if (pIndex < 38){
            if (!moveItemStackTo(fromStack, 0, 36, false)){
                return ItemStack.EMPTY;
            }
        }
        else {
            System.err.println("Invalid slot type: " + pIndex);
        }

        fromSlot.setChanged();
        fromSlot.onTake(player, fromStack);

        return copyFromStack;
    }

    protected boolean isFuel(ItemStack pStack) {
        return net.minecraftforge.common.ForgeHooks.getBurnTime(pStack, RecipeType.SMELTING) > 0;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, BlockInit.FUEL_GENERATOR.get());
    }

    public FuelGeneratorBlockEntity getBlockEntity() {
        return blockEntity;
    }
}
