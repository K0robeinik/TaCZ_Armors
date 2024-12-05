package com.korobeinik.taczarmors.init;

import com.korobeinik.taczarmors.TaczArmors;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AttributeInit {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, TaczArmors.MODID);
    private static final String ATTRIBUTE_NAMESPACE = "attribute.name.taczarmors.";
    public static final RegistryObject<Attribute> JUMP_HEIGHT = ATTRIBUTES.register("jump_height", ()-> new RangedAttribute(ATTRIBUTE_NAMESPACE + "jump_height", 0, 0, 256));
    public static final RegistryObject<Attribute> FALL_HEIGHT = ATTRIBUTES.register("fall_height", ()-> new RangedAttribute(ATTRIBUTE_NAMESPACE + "fall_height", 0, -256, 256));

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeModificationEvent event){
        event.getTypes().forEach(type -> {
            event.add(type, JUMP_HEIGHT.get());
            event.add(type, FALL_HEIGHT.get());
        });
    }
}
