package com.korobeinik.taczarmors.client.render;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.content.CombatArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class CombatArmorRenderer extends GeoArmorRenderer<CombatArmorItem> {
    private static final ResourceLocation animationLocation = new ResourceLocation(TaczArmors.MODID, "animations/item/armor/default.animation.json");
    public CombatArmorRenderer(String suit) {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(TaczArmors.MODID, "armor/"+suit+"_armor")){
            @Override
            public ResourceLocation getAnimationResource (CombatArmorItem anime){
                return animationLocation;
            }
        });
    }

    @Override
    public ResourceLocation getTextureLocation(CombatArmorItem anime) {
        return super.getTextureLocation(anime);
    }
}