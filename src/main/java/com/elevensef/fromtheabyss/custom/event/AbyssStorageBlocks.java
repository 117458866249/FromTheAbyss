package com.elevensef.fromtheabyss.custom.event;

import com.elevensef.fromtheabyss.FromTheAbyss;
import com.elevensef.fromtheabyss.register.ModBlock;
import com.elevensef.fromtheabyss.utils.CommandsUtil;
import com.elevensef.fromtheabyss.utils.PosUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FromTheAbyss.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AbyssStorageBlocks extends Block {
    public AbyssStorageBlocks(Properties pProperties) {
        super(pProperties);
    }

    @SubscribeEvent
    public static void onPlaced(BlockEvent.EntityPlaceEvent event) {
        LevelAccessor level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        if (!level.isClientSide()){
            if (state.is(ModBlock.ABYSS_STORAGE_B.get())){
                if (!level.getBlockState(PosUtil.getRelativePos(pos,0,-1,0)).is(ModBlock.ABYSS_FISSURE_B.get())) {
                    event.setCanceled(true);
                    CommandsUtil.runCommandForLevelAndPos("summon minecraft:item ~ ~0.1 ~ {Item:{id:\"fromtheabyss:abyss_storage\",Count:1b}}",level,pos);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlacedO(BlockEvent.EntityPlaceEvent event) {
        boolean needCancel = true;
        LevelAccessor level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        if (!level.isClientSide()){
            if (state.is(ModBlock.ABYSS_STORAGE_OUTPUT_B.get())){
                for (int i = level.dimensionType().minY();i <= level.dimensionType().height();i++){
                    if (level.getBlockState(new BlockPos(pos.getX(),i,pos.getZ())).is(ModBlock.ABYSS_FISSURE_B.get())){
                        needCancel = false;
                    }
                }
                if (needCancel) {
                    event.setCanceled(true);
                    CommandsUtil.runCommandForLevelAndPos("summon minecraft:item ~ ~0.1 ~ {Item:{id:\"fromtheabyss:abyss_storage_output\",Count:1b}}", level, pos);
                }
            }
        }
    }
}
