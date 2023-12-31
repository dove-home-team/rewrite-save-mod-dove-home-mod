package io.github.dovehome.dovehomemod.config;

import com.teamresourceful.resourcefulconfig.common.annotations.Category;
import com.teamresourceful.resourcefulconfig.common.annotations.Config;
import com.teamresourceful.resourcefulconfig.common.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.common.config.EntryType;
import com.teamresourceful.resourcefulconfig.web.annotations.Gradient;
import com.teamresourceful.resourcefulconfig.web.annotations.WebInfo;
import io.github.dovehome.dovehomemod.Dovehomemod;

@Category(id = Dovehomemod.MOD_ID + "_settings_config", translation = "dove.home.mod.settings.category")
@WebInfo(
        icon = "flame"
)
public final class SettingsConfig {
    @ConfigEntry(
            id = "cactus_thorns_max_blood",
            type = EntryType.INTEGER,
            translation = "dove.home.mod.cactus.thorns.max.blood"
    )
    public static int cactusThornsMaxBlood = Integer.MAX_VALUE / 2;
}
