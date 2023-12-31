package io.github.dovehome.bakalib.datagen.recipe;

import io.github.dovehome.bakalib.datagen.Key;
import io.github.dovehome.bakalib.datagen.Util;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ShapedKey implements Consumer<ShapedRecipeBuilder> {
    @Nullable
    protected final TagKey<Item> itemTagKey;

    @org.jetbrains.annotations.Nullable
    protected final ItemLike like;
    @org.jetbrains.annotations.Nullable
    protected final Ingredient ingredient;

    public boolean has;

    public ShapedKey(@NotNull TagKey<Item> item) {
        itemTagKey = item;
        like = null;
        ingredient = null;
    }

    public ShapedKey(@NotNull ItemLike item) {
        itemTagKey = null;
        like = item;
        ingredient = null;
    }

    public ShapedKey(@NotNull Ingredient item) {
        itemTagKey = null;
        like = null;
        ingredient = item;
    }

    public ShapedKey has() {
        has = true;
        return this;
    }

    @Override
    public void accept(ShapedRecipeBuilder builder) {

    }

    @SuppressWarnings("deprecation")
    public void accept(ShapedRecipeBuilder builder, Key key) {
        char name = key.name().charAt(0);
        if (itemTagKey != null) {
            builder.define(name, itemTagKey);
            if (has) {
                builder.unlockedBy("has_" + itemTagKey.location().getPath(), Util.has(itemTagKey));
            }
        } else if (ingredient != null) {
            builder.define(name, ingredient);
        } else if (like != null) {
            builder.define(name, like);
            if (has) {
                builder.unlockedBy("has_" + Registry.ITEM.getKey(like.asItem()).getPath(), Util.has(like));
            }
        }
    }
}
