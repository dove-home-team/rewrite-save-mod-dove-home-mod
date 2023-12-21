package io.github.dovehome.dovehomemod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class LanguageImpl {
    public final Map<String, Item> itemMap = new HashMap<>();
    public final Map<String, String> vkMap = new HashMap<>();
    public final Map<String, Block> blockMap = new HashMap<>();
    public final Map<String, CreativeModeTab> groupMap = new HashMap<>();
    public final Map<String, EntityType<?>> entityTypeMap = new HashMap<>();
    public final Map<String, Enchantment> enchantmentMap = new HashMap<>();
    public final Map<String, Attribute> attributeMap = new HashMap<>();
    public final Map<String, StatType<?>> statTypeMap = new HashMap<>();
    public final Map<String, MobEffect> mobEffectMap = new HashMap<>();
    public final Map<String, ResourceLocation> rlMap = new HashMap<>();
    public final Map<String, ItemStack> stackMap = new HashMap<>();

    public void add(Item item, String translate) {
        itemMap.put(translate, item);
    }

    public void add(String key, String translate) {
        vkMap.put(translate, key);
    }

    public void add(Block block, String translate) {
        blockMap.put(translate, block);
    }

    public void add(CreativeModeTab tab, String translate) {
        groupMap.put(translate, tab);
    }

    public void add(EntityType<?> entityType, String translate) {
        entityTypeMap.put(translate, entityType);
    }

    public void add(Enchantment enchantment, String translate) {
        enchantmentMap.put(translate, enchantment);
    }

    public void add(Attribute attribute, String translate) {
        attributeMap.put(translate, attribute);
    }

    public void add(StatType<?> statType, String translate) {
        statTypeMap.put(translate, statType);
    }

    public void add(MobEffect mobEffect, String translate) {
        mobEffectMap.put(translate, mobEffect);
    }

    public void add(ResourceLocation id, String translate) {
        rlMap.put(translate, id);
    }

    public void add(ItemStack stack, String translate) {
        stackMap.put(translate, stack);
    }

}
