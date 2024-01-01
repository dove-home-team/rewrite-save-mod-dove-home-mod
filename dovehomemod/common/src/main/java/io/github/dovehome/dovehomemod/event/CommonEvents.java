package io.github.dovehome.dovehomemod.event;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.BlockEvent;
import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.utils.value.IntValue;
import io.github.dovehome.dovehomemod.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;
import java.util.Random;

public class CommonEvents {

    public static void register() {
        BlockEvent.BREAK.register(CommonEvents::stone_producing_silverfish);
        InteractionEvent.RIGHT_CLICK_BLOCK.register(CommonEvents::cactus_bloody);
        InteractionEvent.RIGHT_CLICK_BLOCK.register(CommonEvents::right_client_block_advancement);
    }

    public static EventResult right_client_block_advancement(Player player, InteractionHand hand, BlockPos pos, Direction face) {
        return EventResult.pass();
    }

    public static EventResult cactus_bloody(Player player, InteractionHand hand, BlockPos pos, Direction face) {
        final Level level = player.getLevel();

        if (player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
            BlockState blockState = level.getBlockState(pos);
            if (blockState.is(Blocks.CACTUS) && player.isShiftKeyDown()) {
                if (Math.random() <= 0.1) {
                    player.addItem(new ItemStack(ModItems.cactus_thorns.get()));
                } else {
                    player.hurt(DamageSource.CACTUS, 0.3F);
                    player.hurtTime = 1;
                }
            }
        }
        return EventResult.pass();
    }

    public static EventResult stone_producing_silverfish(Level level,
                                                         BlockPos pos,
                                                         BlockState state,
                                                         ServerPlayer player,
                                                         IntValue xp) {
        if (level instanceof ServerLevel serverLevel) {
            if (level.getBlockState(pos).is(Blocks.STONE)) {
                if (new Random().nextDouble(player.experienceLevel * 0.1 + 0.01)>= 0.05) {
                    serverLevel.addFreshEntityWithPassengers(Objects.requireNonNull(EntityType.SILVERFISH.create(serverLevel, null, null, player, pos, MobSpawnType.SPAWNER, true, false)));
                }
            }
        }

        return EventResult.pass();
    }
}
