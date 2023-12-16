package io.github.dovehomemod.bakalib;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.github.baka4n.bakalib.registry.RegistryAll;

public class Platform {
    @ExpectPlatform
    public static RegistryAll registry(String modid) {
        throw new AssertionError();
    }
    @ExpectPlatform
    public static RegistryAll registry(RegistryAll all) {
        throw new AssertionError();
    }

}
