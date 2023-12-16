package io.github.baka4n.bakalib;

import io.github.baka4n.bakalib.registry.RegistryAll;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class FabricRegistryAll extends RegistryAll {
    public FabricRegistryAll(String modid) {
        super(modid);
    }

    @Override
    public void register(@Nullable Object event) {
        registry(blockEntitys, Registry.BLOCK_ENTITY_TYPE);
        registry(blocks, Registry.BLOCK);
        registry(items, Registry.ITEM);

        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            RegistryAccess.Frozen frozen = server.registryAccess();
            registry(configuredFeatures, frozen.registryOrThrow(Registry.CONFIGURED_FEATURE_REGISTRY));
            registry(dimensionTypes, frozen.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY));
        });
        registry(recipeTypes, Registry.RECIPE_TYPE);
        registry(recipeSerializers, Registry.RECIPE_SERIALIZER);
        blockEntitys = clearAndSetNull(blockEntitys);
        blocks = clearAndSetNull(blocks);
        items = clearAndSetNull(items);
        recipeTypes = clearAndSetNull(recipeTypes);
        recipeSerializers = clearAndSetNull(recipeSerializers);
        System.gc();
    }

    private <T> void registry(Map<String, T> map, Registry<T> registry) {
        map.forEach((s, t) -> Registry.register(registry, new ResourceLocation(modid, s), t));
    }
}
