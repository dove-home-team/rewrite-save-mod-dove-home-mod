package io.github.dovehomemod.bakalib.forge;

import io.github.baka4n.bakalib.ForgeRegistryAll;
import io.github.baka4n.bakalib.registry.RegistryAll;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Locale;

import static net.minecraft.world.item.Item.*;

public class PlatformImpl {

    public static RegistryAll registry(String modid) {
        return new ForgeRegistryAll(modid);
    }

    public static RegistryAll registry(RegistryAll all) {
        FMLJavaModLoadingContext.get().getModEventBus().addListener((RegisterEvent event ) -> all.register(event));
        return all;
    }
}
