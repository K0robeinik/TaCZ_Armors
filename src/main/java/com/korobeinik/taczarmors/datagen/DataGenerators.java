package com.korobeinik.taczarmors.datagen;

import com.korobeinik.taczarmors.TaczArmors;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = TaczArmors.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput packOutput = dataGenerator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        //dataGenerator.addProvider(event.includeServer(), DGLootTableProvider.create(packOutput));
        dataGenerator.addProvider(event.includeServer(), new DGRecipeProvider(packOutput));
        dataGenerator.addProvider(event.includeClient(), new DGBlockStateProvider(packOutput, existingFileHelper));
        dataGenerator.addProvider(event.includeClient(), new DGItemModelProvider(packOutput, existingFileHelper));

        DGBlockTagProvider blockTagProvider = dataGenerator.addProvider(event.includeServer(), new DGBlockTagProvider(packOutput, lookupProvider, existingFileHelper));
        dataGenerator.addProvider(event.includeServer(), new DGItemTagProvider(packOutput, lookupProvider, blockTagProvider.contentsGetter(), existingFileHelper));
    }
}
