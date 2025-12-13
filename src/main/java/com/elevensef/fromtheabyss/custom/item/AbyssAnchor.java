package com.elevensef.fromtheabyss.custom.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class AbyssAnchor extends Item {
    public AbyssAnchor(Properties pProperties) {
        super(pProperties);
    }

    //tooltip
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("fromtheabyss.tooltip.abyss_anchor-1").withStyle(ChatFormatting.WHITE));
            pTooltipComponents.add(Component.translatable("fromtheabyss.tooltip.abyss_anchor-2").withStyle(ChatFormatting.WHITE));
            pTooltipComponents.add(Component.literal("\n").withStyle(ChatFormatting.WHITE));
            pTooltipComponents.add(Component.translatable("fromtheabyss.tooltip.abyss_anchor-3").withStyle(ChatFormatting.WHITE));
            pTooltipComponents.add(Component.translatable("fromtheabyss.tooltip.abyss_anchor-4").withStyle(ChatFormatting.WHITE));
            pTooltipComponents.add(Component.literal("\n").withStyle(ChatFormatting.WHITE));
            pTooltipComponents.add(Component.translatable("fromtheabyss.tooltip.abyss_anchor-5").withStyle(ChatFormatting.WHITE));
            pTooltipComponents.add(Component.literal("\n").withStyle(ChatFormatting.WHITE));
            pTooltipComponents.add(Component.translatable("fromtheabyss.tooltip.abyss_anchor-6").withStyle(ChatFormatting.WHITE));
            pTooltipComponents.add(Component.literal("\n").withStyle(ChatFormatting.WHITE));
            pTooltipComponents.add(Component.translatable("fromtheabyss.tooltip.abyss_anchor-7").withStyle(ChatFormatting.WHITE));
        }else{
            pTooltipComponents.add(Component.translatable("fromtheabyss.global.tooltip_shift").withStyle(ChatFormatting.WHITE));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
