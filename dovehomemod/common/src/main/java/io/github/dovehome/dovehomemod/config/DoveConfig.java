package io.github.dovehome.dovehomemod.config;

import com.teamresourceful.resourcefulconfig.common.annotations.Config;
import com.teamresourceful.resourcefulconfig.common.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.common.config.EntryType;
import com.teamresourceful.resourcefulconfig.web.annotations.Gradient;
import com.teamresourceful.resourcefulconfig.web.annotations.Link;
import com.teamresourceful.resourcefulconfig.web.annotations.WebInfo;
import io.github.dovehome.dovehomemod.Dovehomemod;

import java.util.Arrays;

@Config(Dovehomemod.MOD_ID)
@WebInfo(
        title = Dovehomemod.MOD_ID,
        description = "Dove home mod config.",
        icon = "box",
        gradient = @Gradient(value = "45deg", first = "#c2e59c", second = "#64b3f4"),
        links = {
                @Link(value = "https://github.com/dove-home-team", icon = "github", title = "Home Page"),
                @Link(value = "https://github.com/dove-home-team/dove_home_mod", icon = "github", title = "Mod Github Page")
        }
)
public final class DoveConfig {

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
