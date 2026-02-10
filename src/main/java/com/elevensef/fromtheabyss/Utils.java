package com.elevensef.fromtheabyss;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import java.util.Objects;

public class Utils {
    public static void runCommandForBE(String command, BlockEntity entity){
        CommandSourceStack src = Objects.requireNonNull(
                Objects.requireNonNull(
                        entity.getLevel()).getServer()
        ).createCommandSourceStack().withSuppressedOutput();

        entity.getLevel().getServer().getCommands().performPrefixedCommand(
                src,
                "execute positioned "
                        +
                        entity.getBlockPos().getX()
                        +
                        " "
                        +
                        entity.getBlockPos().getY()
                        +
                        " "
                        +
                        entity.getBlockPos().getZ()
                        +
                        " run "
                        +
                        command
        );
    }
    public static void runCommandForLevelAndPos(String command, Level level, BlockPos pos){
        CommandSourceStack src = Objects.requireNonNull(
                level.getServer()
        ).createCommandSourceStack().withSuppressedOutput();

        level.getServer().getCommands().performPrefixedCommand(
                src,
                "execute positioned "
                        +
                        pos.getX()
                        +
                        " "
                        +
                        pos.getY()
                        +
                        " "
                        +
                        pos.getZ()
                        +
                        " run "
                        +
                        command
        );
    }
}
