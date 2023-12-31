package io.github.dovehome.dovehomemod.mixin.forge;

import io.github.dovehome.dovehomemod.config.DoveConfig;
import io.github.dovehome.dovehomemod.config.VanillaEditConfig;
import net.minecraft.world.level.material.WaterFluid;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WaterFluid.class)
@Debug(export = true)
public class NoInfiniteWater {// no infinite water
    @Inject(method = "canConvertToSource", at = @At("HEAD"), cancellable = true)
    private void canConvertToSource(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(!VanillaEditConfig.noInfiniteWater);
    }
}