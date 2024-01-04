package io.github.dovehome.bakalib.forge;

import dev.architectury.platform.forge.EventBuses;
import io.github.dovehome.bakalib.BakaLib;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BakaLib.modid)
public class BakaLibForge {
    public BakaLibForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(BakaLib.modid, FMLJavaModLoadingContext.get().getModEventBus());
            BakaLib.init();


    }
}