package io.github.dovehome.dovehomemod.datagen;

import io.github.dovehome.dovehomemod.Dovehomemod;
import io.github.dovehome.dovehomemod.primarily.item.CactusThornsBlockItem;
import io.github.dovehome.dovehomemod.registry.ModBlocks;
import io.github.dovehome.dovehomemod.registry.ModCreativeTabs;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = Dovehomemod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DovehomemodDatagen {
    @SubscribeEvent
    public static void datagen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeServer(), new DoveRecipeProvider(generator));
        generator.addProvider(event.includeClient(), new DoveLangProvider(generator));
        generator.addProvider(event.includeClient(), new DoveLangProvider(generator, "en_us") {
            @Override
            protected void addTranslations() {
                add(vanilla_edit_category, "Vanilla edit category");
                add(delete_recipe, "Exclude recipes");
                add(no_infinite_water, "No inf-water");
                add(setting_category, "Setting category");
                add(cactus_thorns_max_blood, "Cactus thorns max blood");

                add(CactusThornsBlockItem.bloodCount, "blood staining amount is ");
                add(CactusThornsBlockItem.bloody, "bloody ");
                add(ModCreativeTabs.step1.get().getDisplayName(), "step1");
                add(ModBlocks.cactus_thorns.get(), "cactus thorns");
            }
        });
    }
}
