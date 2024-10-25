package com.korobeinik.taczarmors.content;

import com.korobeinik.taczarmors.client.render.VariedCombatArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class VariedCombatArmorItem extends CombatArmorItem{
    private final String[] variant;
    public VariedCombatArmorItem(CombatArmorMaterials armorMaterial, Type type, String[] variant) {
        super(armorMaterial, type);
        this.variant = variant;
    }
    public VariedCombatArmorItem(String suitName, CombatArmorMaterials armorMaterial, Type type, String[] variant) {
        super(suitName, armorMaterial, type);
        this.variant = variant;
    }

    public String[] getVariant() {
        return variant;
    }

    public byte switchVariant(ItemStack itemStack) {
        CompoundTag tags = itemStack.getTag();
        if (tags==null){
            tags = new CompoundTag();
            itemStack.setTag(tags);
        }
        byte tv = 0;
        if (tags.contains("taVariant")) {
            tv = tags.getByte("taVariant");
        }
        VariedCombatArmorItem item = (VariedCombatArmorItem) itemStack.getItem();
        tv++;
        tv = (byte) (tv%item.getVariant().length);
        tags.putByte("taVariant", tv);
        itemStack.setTag(tags);
        return tv;
    }

    public byte getCurrentVariant(ItemStack itemStack) {
        CompoundTag cur = itemStack.getTag();
        if (cur.contains("taVariant")){
            return cur.getByte("taVariant");
        }
        return (byte) 0;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, Player pPlayer, @NotNull InteractionHand pHand) {
        boolean shiftPressed = pPlayer.isShiftKeyDown();
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        if(shiftPressed) {
            CompoundTag nbtData = new CompoundTag();
            nbtData.putByte("taVariant", switchVariant(itemStack));
            itemStack.setTag(nbtData);
            if (!pLevel.isClientSide()) {
                pPlayer.sendSystemMessage(Component.literal("Switched camo to: " + getVariant()[getCurrentVariant(itemStack)]));
            }
            return InteractionResultHolder.success(itemStack);
        }
        return super.use(pLevel, pPlayer, pHand);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> list, @NotNull TooltipFlag pIsAdvanced) {
        list.add(Component.literal("The Variant is: " + getVariant()[getCurrentVariant(pStack)]));
        super.appendHoverText(pStack, pLevel, list, pIsAdvanced);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                VariedCombatArmorItem item = (VariedCombatArmorItem) itemStack.getItem();
                if (this.renderer == null) {
                    this.renderer = new VariedCombatArmorRenderer(item.getSuitName()){

                    };
                }

                // This prepares our GeoArmorRenderer for the current render frame.
                // These parameters may be null however, so we don't do anything further with them
                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

                return this.renderer;
            }
        });
    }
}
