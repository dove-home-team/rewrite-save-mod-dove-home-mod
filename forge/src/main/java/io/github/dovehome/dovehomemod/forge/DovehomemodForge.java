package io.github.dovehome.dovehomemod.forge;

import dev.architectury.platform.forge.EventBuses;
import io.github.dovehome.dovehomemod.Dovehomemod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Dovehomemod.MOD_ID)
public class DovehomemodForge {
    public DovehomemodForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(Dovehomemod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        Dovehomemod.init();
    }
}