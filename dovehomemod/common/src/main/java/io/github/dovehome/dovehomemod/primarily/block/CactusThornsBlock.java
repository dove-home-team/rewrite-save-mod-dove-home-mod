package io.github.dovehome.dovehomemod.primarily.block;

import io.github.baka4n.bakalib.block.HorizontalDirectionalEntitySaveNbtBlock;
import io.github.dovehome.dovehomemod.config.SettingsConfig;
import io.github.dovehome.dovehomemod.primarily.tile.CactusThornsBlockEntity;
import io.github.dovehome.dovehomemod.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CactusThornsBlock extends HorizontalDirectionalEntitySaveNbtBlock {
    public CactusThornsBlock(Properties properties) {
        super(properties.noOcclusion().noCollission());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void entityInside(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Entity entity) {
        super.entityInside(state, level, pos, entity);
        entity.hurt(DamageSource.CACTUS, 1F);
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof CactusThornsBlockEntity block) {
            if (block.getBloodCount() >= SettingsConfig.cactusThornsMaxBlood) {
                return;
            }
            block.addBloodCount(1);
        }
    }

    @Override
    public BlockEntityType<?> getTickerImpl() {
        return ModBlockEntities.cactus_thorns.get();
    }

    @Override
    public <T extends BlockEntity> void dove$tick(Level world, BlockPos pos, BlockState state, T blockEntity) {

    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return getTickerImpl().create(pos, state);
    }
}
