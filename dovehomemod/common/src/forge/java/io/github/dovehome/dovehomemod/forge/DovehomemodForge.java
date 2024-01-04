package io.github.dovehome.dovehomemod.forge;

import dev.architectury.platform.forge.EventBuses;
import io.github.dovehome.dovehomemod.Dovehomemod;
import io.github.dovehome.dovehomemod.DovehomemodClient;
import io.github.dovehome.dovehomemod.forge.config.DoveConfigForge;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Dovehomemod.MOD_ID)
public class DovehomemodForge {
    public DovehomemodForge() {
		Dovehomemod.preInit();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> DoveConfigForge::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> DovehomemodClient::clientInit);
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(Dovehomemod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        Dovehomemod.init();

    }
}