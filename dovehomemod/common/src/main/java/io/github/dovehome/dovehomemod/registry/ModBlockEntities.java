package io.github.dovehome.dovehomemod.registry;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.types.Type;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.baka4n.bakalib.RegistryT;
import io.github.dovehome.dovehomemod.primarily.tile.CactusThornsBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Locale;
import java.util.function.Supplier;

public enum ModBlockEntities implements RegistryT<BlockEntityType<?>> {
    cactus_thorns(CactusThornsBlockEntity::new);
    private final Supplier<BlockEntityType<?>> blockEntityType;
    private RegistrySupplier<BlockEntityType<?>> registryBlockEntityType;

    ModBlockEntities(BlockEntityType.BlockEntitySupplier<?> supplier, Type<?> dataType) {
        blockEntityType = () -> BlockEntityType.Builder.of(supplier).build(dataType);
    }

    ModBlockEntities(BlockEntityType.BlockEntitySupplier<?> supplier) {
        blockEntityType = () -> BlockEntityType.Builder.of(supplier).build(DSL.remainderType());
    }

    @Override
    public void register(DeferredRegister<BlockEntityType<?>> register) {
        registryBlockEntityType = register.register(name().toLowerCase(Locale.ROOT), blockEntityType);
    }

    @Override
    public BlockEntityType<?> get() {
        return registryBlockEntityType.get();
    }
}
