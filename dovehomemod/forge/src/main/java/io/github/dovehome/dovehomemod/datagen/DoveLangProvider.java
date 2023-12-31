package io.github.dovehome.dovehomemod.datagen;

import io.github.dovehome.dovehomemod.Dovehomemod;
import io.github.dovehome.dovehomemod.primarily.item.CactusThornsBlockItem;
import io.github.dovehome.dovehomemod.registry.ModBlocks;
import io.github.dovehome.dovehomemod.registry.ModCreativeTabs;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.CreativeModeTab;
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
    public final String setting_category = "dove.home.mod.settings.category";
    public final String cactus_thorns_max_blood = "dove.home.mod.cactus.thorns.max.blood";
    public final String vanilla_edit_category = "dove.home.mod.vanilla.edit.category";
    @Override
    protected void addTranslations() {
        add(vanilla_edit_category, "原版修改类");
        add(delete_recipe, "移除非列表配方");
        add(no_infinite_water, "禁用无限水");
        add(setting_category, "设置类");
        add(cactus_thorns_max_blood, "仙人掌刺最大含血量");

        add(CactusThornsBlockItem.bloodCount, "染血量：");
        add(CactusThornsBlockItem.bloody, "染血的：");
        add(ModCreativeTabs.step1.get().getDisplayName(), "一阶段");
        add(ModBlocks.cactus_thorns.get(), "仙人掌刺");

    }

    protected void add(CreativeModeTab tab, String translate) {
        add(tab.getDisplayName(), translate);
    }

    protected void add(Component component, String translate) {
        if (component.getContents() instanceof TranslatableContents key) {
            add(key.getKey(), translate);
        }
    }
}
