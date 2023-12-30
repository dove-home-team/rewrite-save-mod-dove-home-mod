package io.github.dovehome.dovehomemod.fabric;

import dev.felnull.specialmodelloader.api.event.SpecialModelLoaderEvents;
import io.github.dovehome.dovehomemod.Dovehomemod;
import net.fabricmc.api.ModInitializer;

public class DovehomemodFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Dovehomemod.init();
    }
}