package io.github.dovehomemod.bakalib.fabric;

import io.github.dovehomemod.bakalib.BakaLib;
import net.fabricmc.api.ModInitializer;

public class BakaLibFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BakaLib.init();
    }
}