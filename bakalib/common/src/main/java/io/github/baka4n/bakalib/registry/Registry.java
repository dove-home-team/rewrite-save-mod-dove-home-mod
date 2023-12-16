package io.github.baka4n.bakalib.registry;

import dev.architectury.registry.registries.DeferredRegister;
import io.github.baka4n.bakalib.faces.BlockItem;
import io.github.baka4n.bakalib.faces.Bucket;
import io.github.baka4n.bakalib.faces.FluidBlock;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;

import java.lang.reflect.Field;
import java.util.*;

public class Registry {
    private final LinkedHashSet<Class<?>> classes = new LinkedHashSet<>();

    private final String modid;
    public final DeferredRegister<Item> items;
    public final DeferredRegister<Block> blocks;
    public final DeferredRegister<BlockEntityType<?>> block_entity_types;
    public final DeferredRegister<ConfiguredFeature<?, ?>> configured_features;
    public final DeferredRegister<DimensionType> dimension_types;
    public final DeferredRegister<RecipeType<?>> recipe_types;
    public final DeferredRegister<RecipeSerializer<?>> recipe_serializers;
    public final DeferredRegister<MenuType<?>> menu_types;
    public final DeferredRegister<SoundEvent> sound_events;
    public final DeferredRegister<Fluid> fluids;
    public final DeferredRegister<MobEffect> mob_effects;
    public final DeferredRegister<Enchantment> enchantments;


    public Registry(String modid) {
        this.modid = modid;
        blocks = DeferredRegister.create(modid, net.minecraft.core.Registry.BLOCK_REGISTRY);
        block_entity_types = DeferredRegister.create(modid, net.minecraft.core.Registry.BLOCK_ENTITY_TYPE_REGISTRY);
        items = DeferredRegister.create(modid, net.minecraft.core.Registry.ITEM_REGISTRY);
        configured_features = DeferredRegister.create(modid, net.minecraft.core.Registry.CONFIGURED_FEATURE_REGISTRY);
        dimension_types = DeferredRegister.create(modid, net.minecraft.core.Registry.DIMENSION_TYPE_REGISTRY);
        recipe_types = DeferredRegister.create(modid, net.minecraft.core.Registry.RECIPE_TYPE_REGISTRY);
        recipe_serializers = DeferredRegister.create(modid, net.minecraft.core.Registry.RECIPE_SERIALIZER_REGISTRY);
        menu_types = DeferredRegister.create(modid, net.minecraft.core.Registry.MENU_REGISTRY);
        sound_events = DeferredRegister.create(modid, net.minecraft.core.Registry.SOUND_EVENT_REGISTRY);
        fluids = DeferredRegister.create(modid, net.minecraft.core.Registry.FLUID_REGISTRY);
        mob_effects = DeferredRegister.create(modid, net.minecraft.core.Registry.MOB_EFFECT_REGISTRY);
        enchantments = DeferredRegister.create(modid, net.minecraft.core.Registry.ENCHANTMENT_REGISTRY);
    }



    public Registry add(Class<?>... clazz) {
        classes.addAll(List.of(clazz));
        return this;
    }

    public void register() {
        register(true);
    }

    public void register(boolean bool) {
        classes.forEach(this::registry);
//        for (Class<?> aClass : classes) {
//            registry(aClass);
//        }
        if (bool) {
            fluids.register();
            blocks.register();
            block_entity_types.register();
            items.register();
            configured_features.register();
            dimension_types.register();
            recipe_types.register();
            recipe_serializers.register();
            menu_types.register();
            sound_events.register();
            mob_effects.register();
            enchantments.register();
        }
    }

    private void registry(Class<?> aClass) {
        Field[] declaredFields = aClass.getDeclaredFields();
        Arrays.stream(aClass.getDeclaredClasses()).forEach(this::registry);
//        for (Class<?> declaredClass : aClass.getDeclaredClasses()) {
//            registry(declaredClass);
//        }
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Object o = null;
            try {
                o = declaredField.get(null);
            } catch (IllegalAccessException ignored) {}
            String lowerCase = declaredField.getName().toLowerCase(Locale.ROOT);
            registry(declaredField, o, lowerCase);
        }
    }

    private void registry(Field declaredField, Object o, String lowerCase) {
        if (o instanceof Block b) {
            blocks.register(lowerCase, () -> b);
            if (declaredField.isAnnotationPresent(BlockItem.class)) {
                BlockItem annotation = declaredField.getAnnotation(BlockItem.class);
                net.minecraft.world.item.BlockItem blockItem = null;
                try {
                    blockItem = new net.minecraft.world.item.BlockItem(b, (Item.Properties) annotation.value().getField(annotation.fieldName()).get(null));
                } catch (IllegalAccessException | NoSuchFieldException ignored) {}
                if (blockItem != null) {
                    net.minecraft.world.item.BlockItem finalBlockItem = blockItem;
                    items.register(lowerCase, () -> finalBlockItem);
                }
            }
        }
        else if (o instanceof BlockEntityType<?> t) {
            block_entity_types.register(lowerCase, () -> t);
        }
        else if (o instanceof Item i) {
            items.register(lowerCase, () -> i);
        }
        else if (o instanceof ConfiguredFeature<?,?> c) {
            configured_features.register(lowerCase, () -> c);
        }
        else if (o instanceof DimensionType d) {
            dimension_types.register(lowerCase, () -> d);
        }
        else if (o instanceof RecipeType<?> r) {
            recipe_types.register(lowerCase, () -> r);
        }
        else if (o instanceof RecipeSerializer<?> r) {
            recipe_serializers.register(lowerCase, () -> r);
        }
        else if (o instanceof MenuType<?> m) {
            menu_types.register(lowerCase, () -> m);
        }
        else if (o instanceof SoundEvent s) {
            sound_events.register(lowerCase, () -> s);
        }
        else if (o instanceof FlowingFluid f) {
            fluids.register(lowerCase, () -> f);
            if (declaredField.isAnnotationPresent(Bucket.class)) {
                Bucket annotation = declaredField.getAnnotation(Bucket.class);
                BucketItem bucketItem = null;
                try {
                    bucketItem = new BucketItem(f, (Item.Properties) annotation.value().getField(annotation.fieldName()).get(null));
                } catch (IllegalAccessException | NoSuchFieldException ignored) {
                }
                if (bucketItem != null) {
                    BucketItem finalBlockItem = bucketItem;
                    items.register(lowerCase, () -> finalBlockItem);
                }
            }
            else if (declaredField.isAnnotationPresent(FluidBlock.class)) {
                FluidBlock fluidBlock = declaredField.getAnnotation(FluidBlock.class);
                LiquidBlock liquidBlock = null;
                try {
                    liquidBlock = new LiquidBlock(f, (BlockBehaviour.Properties) fluidBlock.value().getField(fluidBlock.fieldName()).get(null));
                } catch (IllegalAccessException | NoSuchFieldException ignored) {
                }
                if (liquidBlock != null) {
                    LiquidBlock finalLiquidBlock = liquidBlock;
                    blocks.register(lowerCase, () -> finalLiquidBlock);
                }
            }
        }
        else if (o instanceof MobEffect m) {
            mob_effects.register(lowerCase, () -> m);
        }
        else if (o instanceof Enchantment e) {
            enchantments.register(lowerCase, () -> e);
        }
    }

}
