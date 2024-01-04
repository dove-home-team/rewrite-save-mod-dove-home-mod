package io.github.dovehome.bakalib.fabric;

import io.github.dovehome.bakalib.BakaLib;
import net.fabricmc.api.ModInitializer;

public class BakaLibFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BakaLib.init();

    }
}