package com.elevensef.fromtheabyss.custom.blockentity;

import com.elevensef.fromtheabyss.register.ModBlockEntity;
import com.elevensef.fromtheabyss.utils.CommandsUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AbyssAnchorTempEntity extends BlockEntity {
    private int progress = 0;

    private int maxProgess = 10;

    public AbyssAnchorTempEntity(BlockPos pos, BlockState state){
        super(ModBlockEntity.ABYSS_ANCHOR_TEMP.get(),pos,state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, AbyssAnchorTempEntity pEntity) {
        if (level.isClientSide()){
            return;
        }
        if (pEntity.progress >= pEntity.maxProgess){
            pEntity.resetProgress();
            CommandsUtil.runCommandForBE("function fromtheabyss:abyss_anchor",pEntity);
            CommandsUtil.runCommandForBE("setblock ~ ~ ~ minecraft:air",pEntity);
        } else {
            pEntity.progress++;
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }
}
