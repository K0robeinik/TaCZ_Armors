package com.korobeinik.taczarmors.client.render;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.items.armor.CombatArmorItem;
import com.korobeinik.taczarmors.items.armor.VariedCombatArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class CombatArmorRenderer extends NewArmorRenderer<CombatArmorItem> {

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
        if (anime instanceof VariedCombatArmorItem){
            return new ResourceLocation(TaczArmors.MODID, "textures/item/armor/" + anime.getSuitName() + "_armor_" + ((VariedCombatArmorItem)anime).getCurrentVariant(currentStack) + ".png");
        }
        return super.getTextureLocation(anime);
    }
}