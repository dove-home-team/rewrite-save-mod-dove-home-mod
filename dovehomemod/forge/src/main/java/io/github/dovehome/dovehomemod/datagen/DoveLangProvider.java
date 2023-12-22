package io.github.dovehome.dovehomemod.datagen;

import io.github.dovehome.dovehomemod.Dovehomemod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class DoveLangProvider extends LanguageProvider {
    public DoveLangProvider(DataGenerator gen, String locale) {
        super(gen, Dovehomemod.MOD_ID, locale);
    }

    public DoveLangProvider(DataGenerator gen) {
        super(gen, Dovehomemod.MOD_ID, "zh_cn");
    }
    public final String delete_recipe = "dove.home.mod.delete.all.recipe";
    public final String no_infinite_water = "dove.home.mod.no.infinite.water";
    @Override
    protected void addTranslations() {
        add(delete_recipe, "移除非列表配方");
        add(no_infinite_water, "禁用无限水");
    }
}
