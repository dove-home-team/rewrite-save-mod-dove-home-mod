package io.github.dovehome.dovehomemod.forge;

import io.github.dovehome.dovehomemod.Dovehomemod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Dovehomemod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DovehomemodDatagen {
    @SubscribeEvent
    public static void datagen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
    }
}
