package io.github.dovehome.dovehomemod.fabric;

import io.github.dovehome.dovehomemod.datagen.DatagenBus;
import io.github.dovehome.dovehomemod.fabric.datagen.DoveLanguage;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DovehomemodDatagenFabric implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider((FabricDataGenerator generator) -> new DoveLanguage(generator, "en_us", DatagenBus.en_us));
//        DatagenBus.initClient(fabricDataGenerator, false);
//        DatagenBus.initServer(fabricDataGenerator, false);
    }
}
