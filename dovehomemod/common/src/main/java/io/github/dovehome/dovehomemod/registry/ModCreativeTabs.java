package io.github.dovehome.dovehomemod.registry;

import dev.architectury.registry.CreativeTabRegistry;
import io.github.dovehome.dovehomemod.Dovehomemod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.Locale;
import java.util.function.Supplier;

public enum ModCreativeTabs implements Supplier<CreativeModeTab> {
    step1(() -> new ItemStack(ModItems.cactus_thorns.get())),
    end(() -> new ItemStack(ModItems.ultra_zone_sword.get())),
    ;
    private final CreativeModeTab tab;
    ModCreativeTabs(Supplier<ItemStack> stacks) {
        tab = CreativeTabRegistry.create(Dovehomemod.id(name().toLowerCase(Locale.ROOT)), stacks);
    }

    @Override
    public CreativeModeTab get() {
        return tab;
    }

    public static void init() {}
}
