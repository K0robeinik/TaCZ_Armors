package com.korobeinik.taczarmors.client.render;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.content.VariedCombatArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class VariedCombatArmorRenderer extends NewArmorRenderer<VariedCombatArmorItem> {

    private static final ResourceLocation animationLocation = new ResourceLocation(TaczArmors.MODID, "animations/item/armor/default.animation.json");
    public VariedCombatArmorRenderer(String suit) {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(TaczArmors.MODID, "armor/"+ suit +"_armor")){
            @Override
            public ResourceLocation getAnimationResource (VariedCombatArmorItem anime){
                return animationLocation;
            }
        });
    }
    @Override
    public ResourceLocation getTextureLocation (VariedCombatArmorItem anime) {
        return new ResourceLocation(TaczArmors.MODID, "textures/item/armor/" + anime.getSuitName() + "_armor_" + anime.getCurrentVariant(currentStack) + ".png");
    }
}
