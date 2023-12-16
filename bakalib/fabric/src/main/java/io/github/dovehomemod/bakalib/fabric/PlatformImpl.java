package io.github.dovehomemod.bakalib.fabric;

import io.github.baka4n.bakalib.FabricRegistryAll;
import io.github.baka4n.bakalib.registry.RegistryAll;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Locale;

public class PlatformImpl {

    public static RegistryAll registry(String modid) {
        return new FabricRegistryAll(modid);
    }

    public static RegistryAll registry(RegistryAll all) {
        all.register(null);
        return all;
    }
}
