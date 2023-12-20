package io.github.dovehome.dovehomemod;

import com.teamresourceful.resourcefulconfig.client.ConfigScreen;
import com.teamresourceful.resourcefulconfig.common.config.Configurator;
import com.teamresourceful.resourcefulconfig.common.config.ResourcefulConfig;
import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.registry.registries.DeferredRegister;
import io.github.dovehome.dovehomemod.config.DoveConfig;
import io.github.dovehome.dovehomemod.registry.ModBlocks;
import io.github.dovehome.dovehomemod.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.BiFunction;

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
		blocks.register();
		for (ModItems value : ModItems.values()) {
			value.register(items);
		}
		items.register();
	}

	public static ConfigScreen registerConfig(Screen parent) {
		ResourcefulConfig config = Dovehomemod.configs.getConfig(DoveConfig.class);
		if (config == null) {
			return null;
		}
		return new ConfigScreen(null, config);
	}
}
