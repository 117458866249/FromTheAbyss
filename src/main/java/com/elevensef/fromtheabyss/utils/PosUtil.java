package com.elevensef.fromtheabyss.utils;

import net.minecraft.core.BlockPos;

public class PosUtil {
    public static BlockPos getRelativePos(BlockPos pos, int x, int y, int z){
        return new BlockPos(
                pos.getX() + x,
                pos.getY() + y,
                pos.getZ() + z
        );
    }
}
