package io.github.dovehome.dovehomemod.fabric.datagen;

import io.github.dovehome.dovehomemod.LanguageImpl;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class DoveLanguage extends FabricLanguageProvider {
    final LanguageImpl impl;
    public DoveLanguage(FabricDataGenerator generator, String languageCode, LanguageImpl impl) {
        super(generator, languageCode);
        this.impl = impl;
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        impl.vkMap.forEach((trnaslate, key) -> builder.add(key, trnaslate));
        impl.itemMap.forEach((trnaslate, key) -> builder.add(key, trnaslate));
        impl.blockMap.forEach((trnaslate, key) -> builder.add(key, trnaslate));
        impl.groupMap.forEach((trnaslate, key) -> builder.add(key, trnaslate));
        impl.entityTypeMap.forEach((trnaslate, key) -> builder.add(key, trnaslate));
        impl.enchantmentMap.forEach((trnaslate, key) -> builder.add(key, trnaslate));
        impl.attributeMap.forEach((trnaslate, key) -> builder.add(key, trnaslate));
        impl.statTypeMap.forEach((trnaslate, key) -> builder.add(key, trnaslate));
        impl.mobEffectMap.forEach((trnaslate, key) -> builder.add(key, trnaslate));
        impl.rlMap.forEach((trnaslate, key) -> builder.add(key, trnaslate));
        impl.stackMap.forEach((trnaslate, key) -> builder.add(key.getDescriptionId(), trnaslate));
    }
}
