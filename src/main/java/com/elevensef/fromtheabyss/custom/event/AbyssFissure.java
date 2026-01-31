package com.elevensef.fromtheabyss.custom.event;

import com.elevensef.fromtheabyss.FromTheAbyss;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = FromTheAbyss.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AbyssFissure extends Block {
    public AbyssFissure(Properties pProperties) {
        super(pProperties);
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        double posX = pos.getX();
        double posY = pos.getY();
        double posZ = pos.getZ();
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        ItemStack item = player.getItemInHand(hand);
        BlockState state = level.getBlockState(pos);

        if (!level.isClientSide){
            if (state.is(Blocks.BEDROCK)){
                if (hand == InteractionHand.MAIN_HAND){
                    if (item.is(Items.AIR)){
                        List<Entity> entitiesOn = level.getEntities((Entity)null, new AABB(posX - (double)0.5F, posY + (double)0.5F, posZ - (double)0.5F, posX + (double)0.5F, posY + (double)1.5F, posZ + (double)0.5F), EntitySelector.CONTAINER_ENTITY_SELECTOR);

                        player.sendSystemMessage(Component.literal("\n\naa"));
                        player.sendSystemMessage(Component.literal(entitiesOn.get(0).toString()));

                    }
                }
            }
        }
    }
}
