package io.github.dovehome.dovehomemod.forge;

import io.github.dovehome.dovehomemod.Dovehomemod;
import io.github.dovehome.dovehomemod.datagen.DoveLangProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Dovehomemod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DovehomemodDatagen {
    @SubscribeEvent
    public static void datagen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeClient(), new DoveLangProvider(generator));
        generator.addProvider(event.includeClient(), new DoveLangProvider(generator, "en_us") {
            @Override
            protected void addTranslations() {
                add(delete_recipe, "Exclude recipes");
                add(no_infinite_water, "no inf-water");
            }
        });
    }
}
