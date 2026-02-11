package com.elevensef.fromtheabyss.custom.event;

import com.elevensef.fromtheabyss.FromTheAbyss;
import com.elevensef.fromtheabyss.utils.CommandsUtil;
import com.elevensef.fromtheabyss.register.ModBlock;
import com.elevensef.fromtheabyss.register.ModItem;
import com.elevensef.fromtheabyss.utils.PosUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FromTheAbyss.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AbyssAnchor extends Block {
    public AbyssAnchor(Properties pProperty){ super(pProperty); }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockPos posAnti1 = PosUtil.getRelativePos(pos,0,-1,0);
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        ItemStack item = player.getItemInHand(hand);
        BlockState state = level.getBlockState(pos);

        if (!level.isClientSide){
            if (state.is(ModBlock.ABYSS_ALLOY_BLOCK_B.get())){
                if (level.getBlockState(posAnti1).is(ModBlock.ABYSS_FISSURE_B.get())) {
                    if (hand == InteractionHand.MAIN_HAND) {
                        if (item.is(ModItem.ABYSS_CRYSTAL.get())) {
                            item.shrink(1);
                            CommandsUtil.runCommandForLevelAndPos("fill ~ ~ ~ ~ ~-1 ~ minecraft:air", level, pos);
                            CommandsUtil.runCommandForLevelAndPos("summon minecraft:falling_block ~ ~ ~ {Motion: [0.0d, -0.42195522147979175d, 0.0d], FallHurtMax: 40, Invulnerable: 0b, Time: 12, Air: 300s, OnGround: 0b, PortalCooldown: 0, Rotation: [0.0f, 0.0f], DropItem: 1b, FallDistance: 2.902239f, HurtEntities: 0b, BlockState: {Name: \"fromtheabyss:abyss_alloy_block\"}, CanUpdate: 1b, CancelDrop: 1b, Fire: -1s, FallHurtAmount: 0.0f}", level, pos);
                            CommandsUtil.runCommandForLevelAndPos("setblock ~ ~ ~ fromtheabyss:abyss_anchor_temp", level, pos);
                        }
                    }
                }
            }
        }
    }
}
