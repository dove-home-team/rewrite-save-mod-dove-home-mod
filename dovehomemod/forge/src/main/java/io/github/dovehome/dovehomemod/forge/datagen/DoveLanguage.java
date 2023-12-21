package io.github.dovehome.dovehomemod.forge.datagen;

import io.github.dovehome.dovehomemod.Dovehomemod;
import io.github.dovehome.dovehomemod.LanguageImpl;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class DoveLanguage extends LanguageProvider {
    final LanguageImpl impl;
    public DoveLanguage(DataGenerator gen, String locale, LanguageImpl impl) {
        super(gen, Dovehomemod.MOD_ID, locale);
        this.impl = impl;
    }

    @Override
    protected void addTranslations() {
        impl.vkMap.forEach((trnaslate, key) -> add(key, trnaslate));
        impl.itemMap.forEach((trnaslate, key) -> add(key, trnaslate));
        impl.blockMap.forEach((trnaslate, key) -> add(key, trnaslate));
        impl.groupMap.forEach((trnaslate, key) -> add("itemGroup." + key.getRecipeFolderName(), trnaslate));
        impl.entityTypeMap.forEach((trnaslate, key) -> add(key, trnaslate));
        impl.enchantmentMap.forEach((trnaslate, key) -> add(key, trnaslate));
        impl.attributeMap.forEach((trnaslate, key) -> add(key.getDescriptionId(), trnaslate));
        impl.statTypeMap.forEach((trnaslate, key) -> add(key.getTranslationKey(), trnaslate));
        impl.mobEffectMap.forEach((trnaslate, key) -> add(key, trnaslate));
        impl.rlMap.forEach((trnaslate, key) -> add(key.toLanguageKey(), trnaslate));
        impl.stackMap.forEach((trnaslate, key) -> add(key, trnaslate));
    }
}
