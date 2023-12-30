package io.github.dovehome.dovehomemod.forge;

import io.github.dovehome.dovehomemod.Dovehomemod;
import io.github.dovehome.dovehomemod.datagen.DoveLangProvider;
import io.github.dovehome.dovehomemod.primarily.item.CactusThornsBlockItem;
import io.github.dovehome.dovehomemod.registry.ModBlocks;
import io.github.dovehome.dovehomemod.registry.ModCreativeTabs;
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
                add(CactusThornsBlockItem.bloodCount, "blood staining amount is ");
                add(CactusThornsBlockItem.bloody, "bloody ");
                add(ModCreativeTabs.step1.get().getDisplayName(), "step1");
                add(ModBlocks.cactus_thorns.get(), "cactus thorns");
            }
        });
    }
}
