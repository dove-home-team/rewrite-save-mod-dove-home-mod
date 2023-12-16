package io.github.dovehomemod.test;

import io.github.baka4n.bakalib.faces.RegistryBlock;
import io.github.baka4n.bakalib.faces.RegistryItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class TestReg {
    @RegistryBlock("test1")
    public static final Block test1 = new Block(BlockBehaviour.Properties.of(Material.AIR));
    @RegistryItem("test")
    public static final Item test = new Item(new Properties());
}
