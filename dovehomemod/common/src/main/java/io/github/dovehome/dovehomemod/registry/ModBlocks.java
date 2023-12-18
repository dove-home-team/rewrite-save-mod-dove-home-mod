package io.github.dovehome.dovehomemod.registry;

import dev.architectury.registry.registries.DeferredRegister;
import io.github.baka4n.bakalib.RegistryT;
import io.github.dovehome.dovehomemod.Dovehomemod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

public enum ModBlocks implements RegistryT<Block> {
    test_block(Block::new, Material.STONE);
    private final Supplier<Block> block;
    private Block registryBlock;

    ModBlocks(Function<BlockBehaviour.Properties, Block> function, Material material) {
        block = () -> function.apply(BlockBehaviour.Properties.of(material));
    }

    @Override
    public void register(DeferredRegister<Block> register) {
        registryBlock = register.register(Dovehomemod.id(name().toLowerCase(Locale.ROOT)), block).get();
    }

    @Override
    public Block get() {
        return registryBlock;
    }
}
