package com.korobeinik.taczarmors.datagen;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.init.ItemInit;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class DGItemModelProvider extends ItemModelProvider {
    public DGItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TaczArmors.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ItemInit.ASSASSIN_HELMET);
        simpleItem(ItemInit.ASSASSIN_CHESTPLATE);
        simpleItem(ItemInit.ASSASSIN_LEGGINGS);
        simpleItem(ItemInit.ASSASSIN_BOOTS);
        simpleItem(ItemInit.EXOSKELETON_HELMET);
        simpleItem(ItemInit.EXOSKELETON_CHESTPLATE);
        simpleItem(ItemInit.EXOSKELETON_LEGGINGS);
        simpleItem(ItemInit.EXOSKELETON_BOOTS);
        simpleItem(ItemInit.GHILLIE_HELMET);
        simpleItem(ItemInit.GHILLIE_CHESTPLATE);
        simpleItem(ItemInit.GHILLIE_LEGGINGS);
        simpleItem(ItemInit.GHILLIE_BOOTS);
        simpleItem(ItemInit.MODERN_HELMET);
        simpleItem(ItemInit.MODERN_CHESTPLATE);
        simpleItem(ItemInit.MODERN_LEGGINGS);
        simpleItem(ItemInit.MODERN_BOOTS);
        simpleItem(ItemInit.SECURITY_HELMET);
        simpleItem(ItemInit.SECURITY_CHESTPLATE);
        simpleItem(ItemInit.SECURITY_LEGGINGS);
        simpleItem(ItemInit.SECURITY_BOOTS);
        simpleItem(ItemInit.T1_COMBAT_HELMET);
        simpleItem(ItemInit.T1_COMBAT_CHESTPLATE);
        simpleItem(ItemInit.T1_COMBAT_LEGGINGS);
        simpleItem(ItemInit.T1_COMBAT_BOOTS);
        simpleItem(ItemInit.GENERAL);
        simpleItem(ItemInit.BERET);
        simpleItem(ItemInit.BATTERY);
        simpleItem(ItemInit.KEVLAR_HORSE_ARMOR);
        materialItem(ItemInit.STEEL_INGOT);
        materialItem(ItemInit.COPPER_WIRE);
        materialItem(ItemInit.PLASTIC_SHEET);
        //dyeable
        dyeableItem(ItemInit.COLOR_BOTTLE);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TaczArmors.MODID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder materialItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TaczArmors.MODID,"item/materials/" + item.getId().getPath()));
    }

    private ItemModelBuilder dyeableItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated"))
                .texture("layer0",
                new ResourceLocation(TaczArmors.MODID,"item/" + item.getId().getPath()))
                .texture("layer1",
                new ResourceLocation(TaczArmors.MODID,"item/" + item.getId().getPath() + "_overlay"));
    }
}
