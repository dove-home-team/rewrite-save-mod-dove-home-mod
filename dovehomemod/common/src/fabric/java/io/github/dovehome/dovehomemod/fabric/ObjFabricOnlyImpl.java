package io.github.dovehome.dovehomemod.fabric;

import dev.felnull.specialmodelloader.api.event.SpecialModelLoaderEvents;
import io.github.dovehome.dovehomemod.Dovehomemod;

public class ObjFabricOnlyImpl {
    public static void client() {
        SpecialModelLoaderEvents.LOAD_SCOPE.register(resourceLocation -> resourceLocation.getNamespace().equals(Dovehomemod.MOD_ID));
    }
}
