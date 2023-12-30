package io.github.dovehome.bakalib;

import dev.architectury.event.events.client.ClientLifecycleEvent;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;

public class BakaLib {
    public static final String modid = "bakalib";



    public static void init() {
        ClientLifecycleEvent.CLIENT_SETUP.register(instance -> {
            ModelManager modelManager = instance.getModelManager();

        });
    }
}
