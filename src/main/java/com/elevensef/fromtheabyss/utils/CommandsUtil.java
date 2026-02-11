package com.elevensef.fromtheabyss.utils;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import java.util.Objects;

public class CommandsUtil {
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
                        " in "
                        +
                        entity.getLevel().dimensionType().effectsLocation()
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
                        " in "
                        +
                        level.dimensionType().effectsLocation()
                        +
                        " run "
                        +
                        command
        );
    }
    public static void runCommandForLevelAndPos(String command, LevelAccessor level, BlockPos pos){
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
                        " in "
                        +
                        level.dimensionType().effectsLocation()
                        +
                        " run "
                        +
                        command
        );
    }
    public static void runCommandForLevel(String command, Level level){
        CommandSourceStack src = Objects.requireNonNull(
                level.getServer()
        ).createCommandSourceStack().withSuppressedOutput();

        level.getServer().getCommands().performPrefixedCommand(
                src,
                command
        );
    }
    public static void runCommandForLevel(String command, LevelAccessor level){
        CommandSourceStack src = Objects.requireNonNull(
                level.getServer()
        ).createCommandSourceStack().withSuppressedOutput();

        level.getServer().getCommands().performPrefixedCommand(
                src,
                command
        );
    }
}
