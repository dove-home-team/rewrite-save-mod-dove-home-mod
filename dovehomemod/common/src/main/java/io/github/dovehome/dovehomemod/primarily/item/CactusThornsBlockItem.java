package io.github.dovehome.dovehomemod.primarily.item;

import io.github.dovehome.dovehomemod.config.SettingsConfig;
import io.github.dovehome.dovehomemod.registry.ModBlocks;
import io.github.dovehome.dovehomemod.registry.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class CactusThornsBlockItem extends BlockItem {

    public static final MutableComponent bloody = Component.translatable("cactus.bloody").setStyle(Style.EMPTY.withColor(ChatFormatting.GOLD));
    public static final MutableComponent bloodCount = Component.translatable("blood.count");
    public static final Random ran = new Random();

    public CactusThornsBlockItem(CreativeModeTab tab) {
        super(ModBlocks.cactus_thorns.get(), new Properties().tab(tab));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack,
                                @Nullable Level level,
                                @NotNull List<Component> tooltipComponents,
                                @NotNull TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        CompoundTag blockEntityData = getBlockEntityData(stack);
        if (blockEntityData != null) {
            tooltipComponents.add(Component.empty().append(bloodCount).append(String.valueOf(blockEntityData.getInt("blood_count"))));
        }

    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if (entity instanceof Player player) {
            if (player.isCreative() || player.isSpectator()) {
                return;
            }
            Inventory inventory = player.getInventory();
            if ((slotId < 9 && slotId >= 0) || slotId == inventory.getContainerSize() - 1) {
                if (stack.is(ModItems.cactus_thorns.get())) {
                    if (Math.random() <= 0.04) {
                        tagEdit(stack, player);
                    }
                }
            }
            if (player.isSleeping() && Math.random() <= 0.02) {
                if (stack.is(ModItems.cactus_thorns.get())) {
                    tagEdit(stack, player);
                    player.stopSleeping();
                }
            }
        } else {
            tagEntity(stack, entity);
        }
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {

        CompoundTag blockEntityData = getBlockEntityData(stack);
        MutableComponent empty = Component.empty();
        if (blockEntityData != null) {
            if (blockEntityData.getInt("blood_count") >= 20) {
                empty.append(bloody);
            }
        }
        return empty.append(Component.translatable(getDescriptionId()));
    }

    public void tagEdit(@NotNull ItemStack stack, Player player) {
        CompoundTag blockEntityData = Objects.requireNonNullElse(getBlockEntityData(stack), new CompoundTag());



        player.hurt(DamageSource.CACTUS, 1);
        player.hurtTime = 1;
        int bloodCount = blockEntityData.getInt("blood_count");

        if (bloodCount >= SettingsConfig.cactusThornsMaxBlood) {
            return;
        }
        blockEntityData.putInt("blood_count", 1 + bloodCount);
        stack.addTagElement("BlockEntityTag", blockEntityData);

    }

    private static void tagEntity(@NotNull ItemStack stack, @NotNull Entity entity) {
        CompoundTag blockEntityData = Objects.requireNonNullElse(getBlockEntityData(stack), new CompoundTag());
        entity.hurt(DamageSource.CACTUS, 1F);
        int bloodCount = blockEntityData.getInt("blood_count");

        if (bloodCount >= SettingsConfig.cactusThornsMaxBlood) {
            return;
        }
        blockEntityData.putInt("blood_count", 1 + bloodCount);
        stack.addTagElement("BlockEntityTag", blockEntityData);
    }//可以通过生物代替获取血量
}
