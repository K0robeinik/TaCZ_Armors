package com.korobeinik.taczarmors.client.render;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.content.VariedCombatArmorItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class VariedCombatArmorRenderer extends GeoArmorRenderer<VariedCombatArmorItem> {

    private static final ResourceLocation animationLocation = new ResourceLocation(TaczArmors.MODID, "animations/item/armor/default.animation.json");
    public VariedCombatArmorRenderer(String suit, ItemStack stack) {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(TaczArmors.MODID, "armor/"+ suit +"_armor")){
            @Override
            public ResourceLocation getAnimationResource (VariedCombatArmorItem anime){
                return animationLocation;
            }
            @Override
            public ResourceLocation getTextureResource (VariedCombatArmorItem anime) {
                return new ResourceLocation(TaczArmors.MODID, "textures/item/armor/" + suit + "_armor_" + anime.getCurrentVariant(stack) + ".png");
            }
        });
    }
}
