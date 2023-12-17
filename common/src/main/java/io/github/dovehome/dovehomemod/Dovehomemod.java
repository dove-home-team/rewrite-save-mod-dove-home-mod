package io.github.dovehome.dovehomemod;

import com.teamresourceful.resourcefulconfig.common.config.Configurator;
import dev.architectury.registry.registries.DeferredRegister;
import io.github.dovehome.dovehomemod.config.DoveConfig;
import io.github.dovehome.dovehomemod.registry.ModBlocks;
import io.github.dovehome.dovehomemod.registry.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class Dovehomemod
{
	public static final String MOD_ID = "dovehomemod";

	public static final DeferredRegister<Item> items = DeferredRegister.create(Dovehomemod.MOD_ID, Registry.ITEM_REGISTRY);
	public static final DeferredRegister<Block> blocks = DeferredRegister.create(Dovehomemod.MOD_ID, Registry.BLOCK_REGISTRY);

	public static ResourceLocation id(String path) {
		return new ResourceLocation(MOD_ID, path);
	}

	public static final Configurator configs = new Configurator();

	public static void init() {
		configs.registerConfig(DoveConfig.class);
		for (ModBlocks value : ModBlocks.values()) {
			value.register(blocks);
		}
		for (ModItems value : ModItems.values()) {
			value.register(items);
		}
		items.register();
	}
}
