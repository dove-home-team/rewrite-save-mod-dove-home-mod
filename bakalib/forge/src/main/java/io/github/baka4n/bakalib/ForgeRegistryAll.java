package io.github.baka4n.bakalib;

import io.github.baka4n.bakalib.registry.RegistryAll;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ForgeRegistryAll extends RegistryAll {

    public ForgeRegistryAll(String modid) {
        super(modid);
    }

    @Override
    public void register(@NotNull Object o) {
        RegisterEvent event = (RegisterEvent) o;
        register(event, Registry.BLOCK_REGISTRY, blocks);
        register(event, Registry.BLOCK_ENTITY_TYPE_REGISTRY, blockEntitys);
        register(event, Registry.ITEM_REGISTRY, items);
        register(event, Registry.CONFIGURED_FEATURE_REGISTRY, configuredFeatures);
        register(event, Registry.DIMENSION_TYPE_REGISTRY, dimensionTypes);
        register(event, Registry.RECIPE_TYPE_REGISTRY, recipeTypes);
        register(event, Registry.RECIPE_SERIALIZER_REGISTRY, recipeSerializers);
        blockEntitys = clearAndSetNull(blockEntitys);
        blocks = clearAndSetNull(blocks);
        items = clearAndSetNull(items);
        configuredFeatures = clearAndSetNull(configuredFeatures);
        dimensionTypes = clearAndSetNull(dimensionTypes);
        recipeTypes = clearAndSetNull(recipeTypes);
        recipeSerializers = clearAndSetNull(recipeSerializers);
        System.gc();// gc map

    }

    private <T> void register(@NotNull RegisterEvent event, ResourceKey<Registry<T>> registry, Map<String, T> map) {
        for (Map.Entry<String, T> entry : map.entrySet()) {
            event.register(registry, new ResourceLocation(modid, entry.getKey()), entry::getValue);
        }
    }
}
