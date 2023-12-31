package io.github.dovehome.dovehomemod.fabric;

import io.github.dovehome.dovehomemod.DovehomemodClient;
import net.fabricmc.api.ClientModInitializer;

public class DovehomemodClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DovehomemodClient.clientInit();
    }
}
