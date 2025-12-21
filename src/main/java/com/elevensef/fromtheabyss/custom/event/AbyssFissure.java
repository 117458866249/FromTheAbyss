package com.elevensef.fromtheabyss.custom.event;

import com.elevensef.fromtheabyss.FromTheAbyss;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FromTheAbyss.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AbyssFissure extends Block {
    public AbyssFissure(Properties pProperties) {
        super(pProperties);
    }
}
