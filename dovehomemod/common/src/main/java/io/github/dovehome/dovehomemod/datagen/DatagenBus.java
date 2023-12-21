package io.github.dovehome.dovehomemod.datagen;

import io.github.dovehome.dovehomemod.LanguageImpl;
import io.github.dovehome.dovehomemod.registry.ModItems;

public class DatagenBus {
    public static final LanguageImpl en_us = new LanguageImpl();
    static {
        en_us.add(ModItems.test.get(), "test");
    }
}
