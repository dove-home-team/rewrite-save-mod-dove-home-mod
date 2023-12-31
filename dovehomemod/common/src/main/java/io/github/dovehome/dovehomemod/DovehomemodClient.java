package io.github.dovehome.dovehomemod;

import dev.architectury.event.events.client.ClientLifecycleEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class DovehomemodClient {
    public static void clientInit() {
        ClientLifecycleEvent.CLIENT_SETUP.register(instance -> {
            ObjFabricOnly.client();
        });
    }
}
