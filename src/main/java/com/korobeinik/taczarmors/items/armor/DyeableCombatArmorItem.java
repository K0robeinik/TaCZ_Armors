package com.korobeinik.taczarmors.items.armor;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.client.render.CombatArmorLayer;
import com.korobeinik.taczarmors.client.render.CombatArmorRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import java.util.function.Consumer;

public class DyeableCombatArmorItem extends CombatArmorItem implements DyeableLeatherItem {
    private final int defaultColor;
    private final boolean overlay;

    public DyeableCombatArmorItem(CombatArmorMaterials armorMaterial, Type type) {
        this(armorMaterial, type, 0xFFFFFF);
    }

    public DyeableCombatArmorItem(CombatArmorMaterials armorMaterial, Type type, int defaultColor) {
        this(armorMaterial, type, defaultColor, false);
    }

    public DyeableCombatArmorItem(CombatArmorMaterials armorMaterial, Type type, int defaultColor, boolean overlay) {
        super(armorMaterial, type);
        this.defaultColor = defaultColor;
        this.overlay = overlay;
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
                    this.renderer = new CombatArmorRenderer(item.getSuitName(), overlay);
                }
                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

                return this.renderer;
            }
        });
    }
}
