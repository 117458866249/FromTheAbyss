package com.elevensef.fromtheabyss.custom.blockitem;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.List;

public class AbyssFissure extends BlockItem {
    public AbyssFissure(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    //tooltip
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("fromtheabyss.tooltip.abyss_fissure-1").withStyle(ChatFormatting.WHITE));
            pTooltipComponents.add(Component.translatable("fromtheabyss.tooltip.abyss_fissure-2").withStyle(ChatFormatting.WHITE));
            pTooltipComponents.add(Component.translatable("fromtheabyss.tooltip.abyss_fissure-3").withStyle(ChatFormatting.WHITE));
            pTooltipComponents.add(Component.translatable("fromtheabyss.tooltip.abyss_fissure-4").withStyle(ChatFormatting.WHITE));
            pTooltipComponents.add(Component.literal("\n").withStyle(ChatFormatting.WHITE));
            pTooltipComponents.add(Component.translatable("fromtheabyss.tooltip.abyss_fissure-5").withStyle(ChatFormatting.WHITE));
        }else{
            pTooltipComponents.add(Component.translatable("fromtheabyss.global.tooltip_shift").withStyle(ChatFormatting.WHITE));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
