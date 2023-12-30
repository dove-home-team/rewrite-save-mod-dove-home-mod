package io.github.dovehome.dovehomemod.config;

import com.teamresourceful.resourcefulconfig.common.annotations.Config;
import com.teamresourceful.resourcefulconfig.common.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.common.config.EntryType;
import com.teamresourceful.resourcefulconfig.web.annotations.Gradient;
import com.teamresourceful.resourcefulconfig.web.annotations.WebInfo;
import io.github.dovehome.dovehomemod.Dovehomemod;

@Config(Dovehomemod.MOD_ID + "_settings_config")
@WebInfo(
        title = Dovehomemod.MOD_ID + "_settings_config",
        description = "This is block or item settings config",
        icon = "box",
        gradient = @Gradient(value = "45deg", first = "#c2e59c", second = "#64b3f4")
)
public class SettingsConfig {
    @ConfigEntry(
            id = "cactus_thorns_max_blood",
            type = EntryType.INTEGER,
            translation = "dove.home.mod.cactus.thorns.max.blood"
    )
    public static int cactusThornsMaxBlood = Integer.MAX_VALUE / 2;
}
