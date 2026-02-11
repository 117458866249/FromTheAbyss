package com.elevensef.fromtheabyss.custom.blockentity;

import com.elevensef.fromtheabyss.utils.CommandsUtil;
import com.elevensef.fromtheabyss.register.ModBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AbyssIntegrationerEntity extends BlockEntity {
    private int progress = 0;

    private int maxProgess = 1000;

    public AbyssIntegrationerEntity(BlockPos pos, BlockState state){
        super(ModBlockEntity.ABYSS_INTEGRATIONER.get(),pos,state);
    }

    public static void tick(Level level,BlockPos pos,BlockState state,AbyssIntegrationerEntity pEntity) {
        if (level.isClientSide()){
            return;
        }
        if (pEntity.progress >= pEntity.maxProgess){
            pEntity.resetProgress();
        } else {
            pEntity.progress++;
        }
        if (pEntity.progress%30 == 4){
            CommandsUtil.runCommandForBE("particle minecraft:dripping_water ~ ~ ~ 0.6 0 0.4 0.06 2 force",pEntity);
        }
        CommandsUtil.runCommandForBE("execute positioned ~ ~-0.5 ~ if entity @e[type=minecraft:item,distance=..1,nbt={Item:{id:\"minecraft:deepslate\"}},limit=1] run execute if entity @e[type=minecraft:item,distance=..1,nbt={Item:{id:\"minecraft:lapis_lazuli\"}},limit=1] run function fromtheabyss:abyss_integrationer",pEntity);
    }

    private void resetProgress() {
        this.progress = 0;
    }
}
