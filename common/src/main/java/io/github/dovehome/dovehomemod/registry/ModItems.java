package io.github.dovehome.dovehomemod.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.baka4n.bakalib.RegistryT;
import io.github.dovehome.dovehomemod.Dovehomemod;
import net.minecraft.world.item.Item;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

public enum ModItems implements RegistryT<Item> {
    test(Item::new);
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
