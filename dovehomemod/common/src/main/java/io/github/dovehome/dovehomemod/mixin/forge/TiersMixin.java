package io.github.dovehome.dovehomemod.mixin.forge;

import io.github.dovehome.dovehomemod.registry.ModItems;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.*;

import java.util.Arrays;
import java.util.function.Supplier;

@Mixin(Tiers.class)
public class TiersMixin {

    TiersMixin(String name, int ordinal, int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        throw new AssertionError();
    }

    @Final
    @Shadow
    @SuppressWarnings({"target", "mapping"})
    @Mutable
    private static  Tiers[] $VALUES;

    //添加挖掘等级设定
    static {
        dovehomemod$add("ULTRA", 21474/*max / 1000*/, Integer.MAX_VALUE, 1000, 10.0F, 30, () -> Ingredient.of(ModItems.ultra_zone_ingot.get()));
    }

    @Unique
    private static void dovehomemod$add(String name ,int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        int ordinal = $VALUES.length;
        $VALUES = Arrays.copyOf($VALUES, ordinal + 1);
        $VALUES[ordinal] = (Tiers) (Object) new TiersMixin(name, ordinal, level, uses, speed, damage, enchantmentValue, repairIngredient);
    }
}
