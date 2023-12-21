package io.github.dovehome.dovehomemod.fabric.datagen.language;

import io.github.dovehome.dovehomemod.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class Enus extends FabricLanguageProvider {
    public Enus(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        builder.add(ModItems.test.get(), "test");
    }
}
