package com.korobeinik.taczarmors.datagen;

import com.korobeinik.taczarmors.TaczArmors;
import com.korobeinik.taczarmors.init.ArmorItemInit;
import com.korobeinik.taczarmors.init.ItemInit;
import com.korobeinik.taczarmors.items.armor.DyeableCombatArmorItem;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class DGItemModelProvider extends ItemModelProvider {
    public DGItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TaczArmors.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ArmorItemInit.NORMAL.forEach(supplier -> basicItem(supplier.get().asItem()));
        ArmorItemInit.DYEABLE.forEach(supplier -> dyeableItem(supplier.get().asItem()));
        simpleItem(ItemInit.BATTERY);
        simpleItem(ItemInit.KEVLAR_HORSE_ARMOR);
        materialItem(ItemInit.STEEL_INGOT);
        materialItem(ItemInit.COPPER_WIRE);
        materialItem(ItemInit.PLASTIC_SHEET);
        //dyeable
        dyeableItem(ItemInit.COLOR_BOTTLE);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return simpleItem(item.get());
    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(item.toString(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TaczArmors.MODID,"item/" + item));
    }

    private ItemModelBuilder materialItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TaczArmors.MODID,"item/materials/" + item.getId().getPath()));
    }

    private ItemModelBuilder dyeableItem(RegistryObject<Item> item){
        return dyeableItem(item.get());
    }

    private ItemModelBuilder dyeableItem(Item item) {
        ResourceLocation overlay = new ResourceLocation(TaczArmors.MODID,"item/" + item.toString() + "_overlay");
        ItemModelBuilder builder = withExistingParent(item.toString(),
                new ResourceLocation("item/generated"))
                .texture("layer0",
                new ResourceLocation(TaczArmors.MODID,"item/" + item));
        return existingFileHelper.exists(overlay, ModelProvider.TEXTURE) ?
                builder.texture("layer1", overlay):
                builder;
    }
}
