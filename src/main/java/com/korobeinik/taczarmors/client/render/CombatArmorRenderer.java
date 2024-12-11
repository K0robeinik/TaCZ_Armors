package com.korobeinik.taczarmors.client.render;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.items.armor.CombatArmorItem;
import com.korobeinik.taczarmors.items.armor.VariedCombatArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class CombatArmorRenderer extends NewArmorRenderer<CombatArmorItem> {

    private static final ResourceLocation animationLocation = new ResourceLocation(TaczArmors.MODID, "animations/item/armor/default.animation.json");
    public CombatArmorRenderer(String suit) {
        this(suit, false);
    }
    public CombatArmorRenderer(String suit, boolean overlay) {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(TaczArmors.MODID, "armor/"+suit+"_armor")){
            @Override
            public ResourceLocation getAnimationResource (CombatArmorItem anime){
                return animationLocation;
            }
        });
        if (overlay) {
            /*this.addRenderLayer(new GeoRenderLayer<>(this) {
                @Override
                public void render(PoseStack poseStack, CombatArmorItem animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
                    RenderType armorRenderType = RenderType.armorCutoutNoCull(new ResourceLocation(TaczArmors.MODID, "textures/item/armor/"+suit+"_overlay.png"));
                    this.getRenderer().reRender(this.getDefaultBakedModel(animatable), poseStack, bufferSource, animatable, armorRenderType, bufferSource.getBuffer(armorRenderType), partialTick, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                }
            });*/
            this.addRenderLayer(new CombatArmorLayer(this));
        }
    }

    @Override
    public ResourceLocation getTextureLocation(CombatArmorItem anime) {
        if (anime instanceof VariedCombatArmorItem varAnime){
            return new ResourceLocation(TaczArmors.MODID, "textures/item/armor/" + varAnime.getSuitName() + "_armor_" + varAnime.getCurrentVariant(currentStack) + ".png");
        }
        return super.getTextureLocation(anime);
    }
}