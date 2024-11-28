package com.korobeinik.taczarmors.client.render;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.content.CombatArmorItem;
import com.korobeinik.taczarmors.content.DyeableCombatArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.model.GeoModel;

public class DyeableCombatArmorRenderer extends NewArmorRenderer<DyeableCombatArmorItem>{

    private static final ResourceLocation animationLocation = new ResourceLocation(TaczArmors.MODID, "animations/item/armor/default.animation.json");
    public DyeableCombatArmorRenderer(String suit) {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(TaczArmors.MODID, "armor/"+suit+"_armor")){
            @Override
            public ResourceLocation getAnimationResource (DyeableCombatArmorItem anime){
                return animationLocation;
            }
        });
    }
}
