package com.korobeinik.taczarmors.client.render;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.content.CombatArmorItem;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class CombatArmorRenderer extends GeoArmorRenderer<CombatArmorItem> {
    private static final ResourceLocation animationLocation = new ResourceLocation(TaczArmors.MODID, "animations/item/armor/default.animation.json");
    //private final String suit;
    public CombatArmorRenderer(String suit) {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(TaczArmors.MODID, "armor/"+suit+"_armor")){
            @Override
            public ResourceLocation getAnimationResource (CombatArmorItem anime){
                return animationLocation;
            }
        });
        //this.suit = suit;
    }

    @Override
    public ResourceLocation getTextureLocation(CombatArmorItem anime) {
        return super.getTextureLocation(anime);
    }

    /*@Override
    public GeoModel<CombatArmorItem> getGeoModel() {
        if (currentEntity instanceof Player) {
            if (getRenderLayers().contains(ModelLayers.PLAYER_SLIM)) {
                return new DefaultedItemGeoModel<>(new ResourceLocation(TaczArmors.MODID, "armor/assassin_armor"));
            }
        }
        return super.getGeoModel();
    }*/
}