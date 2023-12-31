package io.github.dovehome.dovehomemod;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.PlatformOnly;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class ObjFabricOnly {
    @ExpectPlatform
    public static void client() {

    }
}
