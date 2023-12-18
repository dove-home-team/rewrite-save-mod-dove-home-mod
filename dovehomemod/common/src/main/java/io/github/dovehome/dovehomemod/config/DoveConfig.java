package io.github.dovehome.dovehomemod.config;

import com.teamresourceful.resourcefulconfig.common.annotations.Config;
import com.teamresourceful.resourcefulconfig.common.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.common.config.EntryType;
import com.teamresourceful.resourcefulconfig.web.annotations.WebInfo;
import io.github.dovehome.dovehomemod.Dovehomemod;

import java.util.Arrays;

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
    public static String deleteAllRecipe = Arrays.toString(new String[] {
            Dovehomemod.MOD_ID,
            "kubejs",
            "crafttweaker"
    });
    @ConfigEntry(
            id = "noInfiniteWater",
            type = EntryType.BOOLEAN,
            translation = "dove.home.mod.no.infinite.write"
    )
    public static boolean noInfiniteWater = true;
}
