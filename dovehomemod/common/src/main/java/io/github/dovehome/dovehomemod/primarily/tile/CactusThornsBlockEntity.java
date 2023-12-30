package io.github.dovehome.dovehomemod.primarily.tile;

import io.github.dovehome.dovehomemod.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class CactusThornsBlockEntity extends BlockEntity {
    private int blood_count;
    public CactusThornsBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.cactus_thorns.get(), pos, blockState);
    }

    public int getBloodCount() {
        return blood_count;
    }
    public void addBloodCount(int blood_count) {
        this.blood_count += blood_count;
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        blood_count = tag.getInt("blood_count");
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putFloat("blood_count", blood_count);
    }
}
