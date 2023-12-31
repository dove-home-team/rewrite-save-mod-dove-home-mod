package io.github.dovehome.dovehomemod.config;

import com.teamresourceful.resourcefulconfig.common.annotations.Category;
import com.teamresourceful.resourcefulconfig.common.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.common.config.EntryType;
import com.teamresourceful.resourcefulconfig.web.annotations.WebInfo;
import io.github.dovehome.dovehomemod.Dovehomemod;

import java.util.Arrays;

@Category(id = Dovehomemod.MOD_ID + "vanilla_edit_config", translation = "dove.home.mod.vanilla.edit.category")
@WebInfo(
        icon = "flame"
)
public final class VanillaEditConfig {
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
            translation = "dove.home.mod.no.infinite.water"
    )
    public static boolean noInfiniteWater = true;

    @ConfigEntry(
            id = "isVoidNether",
            type = EntryType.BOOLEAN,
            translation = "dove.home.mod.is.void.nether"
    )
    public static boolean isVoidNether = true;
}
