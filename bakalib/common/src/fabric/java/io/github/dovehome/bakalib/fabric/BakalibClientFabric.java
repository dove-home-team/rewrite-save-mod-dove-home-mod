package io.github.dovehome.bakalib.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
@Environment(EnvType.CLIENT)
public class BakalibClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
//        ModelLoadingRegistry.INSTANCE.registerResourceProvider();
    }
}
