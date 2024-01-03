package io.github.dovehome.dovehomemod.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.baka4n.bakalib.RegistryT;
import io.github.dovehome.dovehomemod.Dovehomemod;
import io.github.dovehome.dovehomemod.primarily.item.CactusThornsBlockItem;
import net.minecraft.core.Registry;
import net.minecraft.world.item.*;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

public enum ModItems implements RegistryT<Item> {
    cactus_thorns(properties -> new CactusThornsBlockItem(ModCreativeTabs.step1.get())),
    rare_element_nugget(Item::new),
    rare_and_rare_element_nugget(Item::new),
    ultra_zone_nugget(Item::new),
    ultra_zone_ingot(Item::new),//极境锭 <<天黑了，零家改灭亡了>>
    ultra_zone_sword(properties -> new SwordItem(Tiers.valueOf("ULTRA"), 3, -2.4F, new Item.Properties().tab(ModCreativeTabs.end.get()))),
    rare_element_ingot(Item::new),
    rare_and_rare_element_ingot(Item::new),
    rare_element_block(properties -> new BlockItem(ModBlocks.rare_element_block.get(), properties)),
    rare_and_rare_element_block(properties -> new BlockItem(ModBlocks.rare_and_rare_element_block.get(), properties)),
    ;
    private final Supplier<Item> item;
    private RegistrySupplier<Item> registryItem;

    ModItems(Function<Item.Properties, Item> function) {
        this.item = () -> function.apply(new Item.Properties());
    }

    @Override
    public void register(DeferredRegister<Item> register) {
        registryItem = register.register(Dovehomemod.id(name().toLowerCase(Locale.ROOT)), item);
    }

    @Override
    public Item get() {
        return registryItem.get();
    }
}
