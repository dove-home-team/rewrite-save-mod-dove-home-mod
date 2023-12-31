package io.github.dovehome.dovehomemod.mixin.forge;

import com.google.gson.JsonElement;
import io.github.dovehome.dovehomemod.Dovehomemod;
import io.github.dovehome.dovehomemod.config.DoveConfig;
import io.github.dovehome.dovehomemod.config.VanillaEditConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.RecipeManager;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(RecipeManager.class)
@Debug(export = true)
public class RemoveAllRecipe {

    @Unique
    private static boolean dove_home_mod$remove(Map.Entry<ResourceLocation, JsonElement> entry, String... modids) {
        boolean b = true;
        for (String modid : modids) {
            b &= !entry.getKey().getNamespace().equals(modid);
        }
        return b;
    }

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At("HEAD"))
    private void apply(Map<ResourceLocation, JsonElement> object,
                       ResourceManager resourceManager,
                       ProfilerFiller profiler,
                       CallbackInfo ci) {

        String[] split = VanillaEditConfig.deleteAllRecipe.replace("[", "").replace("]", "").split(",");
        object.entrySet().removeIf(entry ->
                dove_home_mod$remove(entry, split));
    }
}
