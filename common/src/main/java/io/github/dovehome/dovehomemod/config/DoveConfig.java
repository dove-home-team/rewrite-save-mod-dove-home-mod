package io.github.dovehome.dovehomemod.config;

import com.teamresourceful.resourcefulconfig.common.annotations.Config;
import com.teamresourceful.resourcefulconfig.common.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.common.config.EntryType;
import com.teamresourceful.resourcefulconfig.web.annotations.WebInfo;
import io.github.dovehome.dovehomemod.Dovehomemod;

@Config(Dovehomemod.MOD_ID)
@WebInfo(
        title = Dovehomemod.MOD_ID,
        description = "Dove home mod config."
)
public class DoveConfig {

    @ConfigEntry(
            id = "deleteAllRecipe",
            type = EntryType.STRING,
            translation = "dove.home.mod.delete.all.recipe"
    )
    public static String deleteAllRecipe = "%s,kubejs,crafttweaker".formatted(Dovehomemod.MOD_ID);
    @ConfigEntry(
            id = "noInfiniteWater",
            type = EntryType.BOOLEAN,
            translation = "dove.home.mod.no.infinite.write"
    )
    public static boolean noInfiniteWater = true;
}
