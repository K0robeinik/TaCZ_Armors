package com.korobeinik.taczarmors.content;

import com.korobeinik.taczarmors.client.render.CombatArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.function.Consumer;

public class DyeableCombatArmorItem extends VariedCombatArmorItem implements DyeableLeatherItem {
    private final int defaultColor;

    public DyeableCombatArmorItem(CombatArmorMaterials armorMaterial, Type type, String[] variant) {
        this(armorMaterial, type, variant, 0xFFFFFF);
    }

    public DyeableCombatArmorItem(CombatArmorMaterials armorMaterial, Type type, String[] variant, int defaultColor) {
        super(armorMaterial, type, variant);
        this.defaultColor = defaultColor;
    }

    public int getColor(ItemStack pStack) {
        CompoundTag compoundtag = pStack.getTagElement("display");
        return compoundtag != null && compoundtag.contains("color", 99) ? compoundtag.getInt("color") : defaultColor;
    }

    public static int getItemColor(ItemStack stack, int tint){ return tint > 0 ? 0xFFFFFF : ((DyeableCombatArmorItem)stack.getItem()).getColor(stack);}

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                DyeableCombatArmorItem item = (DyeableCombatArmorItem) itemStack.getItem();
                if (this.renderer == null) {
                    this.renderer = new CombatArmorRenderer(item.getSuitName());
                }

                // This prepares our GeoArmorRenderer for the current render frame.
                // These parameters may be null however, so we don't do anything further with them
                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

                return this.renderer;
            }
        });
    }
}
