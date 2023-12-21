package io.github.dovehome.dovehomemod.forge;

import io.github.dovehome.dovehomemod.Dovehomemod;
import io.github.dovehome.dovehomemod.datagen.DatagenBus;
import io.github.dovehome.dovehomemod.forge.datagen.DoveLanguage;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Dovehomemod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DatagenForge {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeClient(), new DoveLanguage(generator, "en_us", DatagenBus.en_us));

    }
}
