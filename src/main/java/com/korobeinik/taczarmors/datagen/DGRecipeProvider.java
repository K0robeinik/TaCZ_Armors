package com.korobeinik.taczarmors.datagen;

import com.korobeinik.taczarmors.init.BlockInit;
import com.korobeinik.taczarmors.init.ItemInit;
import com.korobeinik.taczarmors.init.TagsInit;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class DGRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public DGRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, BlockInit.FUEL_GENERATOR.get().asItem())
                .define('i', Tags.Items.INGOTS_IRON)
                .define('c', Tags.Items.INGOTS_COPPER)
                .pattern("ici")
                .pattern("c c")
                .pattern("ici")
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Tags.Items.INGOTS_IRON));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BlockInit.STEEL_BLOCK.get().asItem())
                .requires(Ingredient.of(TagsInit.Items.INGOTS_STEEL), 9)
                .unlockedBy(getHasName(ItemInit.STEEL_INGOT.get()), has(TagsInit.Items.INGOTS_STEEL));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemInit.STEEL_INGOT.get())
                .requires(BlockInit.STEEL_BLOCK.get())
                .unlockedBy(getHasName(BlockInit.STEEL_BLOCK.get().asItem()), has(TagsInit.Items.STORAGE_BLOCKS_STEEL));
    }
}
