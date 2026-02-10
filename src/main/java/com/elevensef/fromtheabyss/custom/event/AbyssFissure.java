package com.elevensef.fromtheabyss.custom.event;

import com.elevensef.fromtheabyss.FromTheAbyss;
import com.elevensef.fromtheabyss.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FromTheAbyss.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AbyssFissure extends Block {
    public AbyssFissure(Properties pProperties) {
        super(pProperties);
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        ItemStack item = player.getItemInHand(hand);
        BlockState state = level.getBlockState(pos);

        if (!level.isClientSide){
            if (level.dimension() == Level.OVERWORLD){
                if (pos.getY() != -64){
                    return;
                }
            }

            if (state.is(Blocks.BEDROCK)){
                if (hand == InteractionHand.MAIN_HAND){
                    if (item.is(Items.AIR)){
                        Utils.runCommandForLevelAndPos("execute positioned ~ ~0.5 ~ if entity @e[type=minecraft:item,distance=..1,nbt={Item:{id:\"minecraft:amethyst_shard\"}},limit=1] run function fromtheabyss:abyss_fissure.json",level,pos);
                    }
                }
            }
        }
    }
}
