package io.github.dovehome.dovehomemod;

import com.teamresourceful.resourcefulconfig.client.ConfigScreen;
import com.teamresourceful.resourcefulconfig.common.config.Configurator;
import com.teamresourceful.resourcefulconfig.common.config.ResourcefulConfig;
import dev.architectury.registry.registries.DeferredRegister;
import io.github.dovehome.dovehomemod.config.DoveConfig;
import io.github.dovehome.dovehomemod.event.CommonEvents;
import io.github.dovehome.dovehomemod.registry.ModBlockEntities;
import io.github.dovehome.dovehomemod.registry.ModBlocks;
import io.github.dovehome.dovehomemod.registry.ModCreativeTabs;
import io.github.dovehome.dovehomemod.registry.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class Dovehomemod
{
	public static final String MOD_ID = "dovehomemod";

	public static final DeferredRegister<Item> items = DeferredRegister.create(Dovehomemod.MOD_ID, Registry.ITEM_REGISTRY);
	public static final DeferredRegister<Block> blocks = DeferredRegister.create(Dovehomemod.MOD_ID, Registry.BLOCK_REGISTRY);
	public static final DeferredRegister<BlockEntityType<?>> blockEntities = DeferredRegister.create(Dovehomemod.MOD_ID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);

	public static ResourceLocation id(String path) {
		return new ResourceLocation(MOD_ID, path);
	}

	public static final Configurator configs = new Configurator();

	public static void preInit() {
		configs.registerConfig(DoveConfig.class);
	}

	public static void init() {
		ModCreativeTabs.init();
		for (ModBlockEntities value : ModBlockEntities.values()) {
			value.register(blockEntities);
		}
		blockEntities.register();
		for (ModBlocks value : ModBlocks.values()) {
			value.register(blocks);
		}
		blocks.register();
		for (ModItems value : ModItems.values()) {
			value.register(items);
		}
		items.register();
		CommonEvents.register();
	}

	public static ConfigScreen registerConfig(Screen parent) {
		ResourcefulConfig config = Dovehomemod.configs.getConfig(DoveConfig.class);
		if (config == null) {
			return null;
		}
        return new ConfigScreen(null, config);
	}
}
