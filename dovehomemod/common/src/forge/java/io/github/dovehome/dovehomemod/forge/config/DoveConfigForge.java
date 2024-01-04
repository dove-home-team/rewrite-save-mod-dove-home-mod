package io.github.dovehome.dovehomemod.forge.config;

import com.teamresourceful.resourcefulconfig.client.ConfigScreen;
import com.teamresourceful.resourcefulconfig.common.config.ResourcefulConfig;
import io.github.dovehome.dovehomemod.Dovehomemod;
import io.github.dovehome.dovehomemod.config.DoveConfig;
import io.github.dovehome.dovehomemod.forge.DovehomemodForge;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


public class DoveConfigForge {

    public static void init() {
        DoveConfigForge.register();
    }

    public static void register() {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((__, parent) -> Dovehomemod.registerConfig(parent)));
    }
}
