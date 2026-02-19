package com.elevensef.fromtheabyss;

import com.elevensef.fromtheabyss.register.ModBlock;
import com.elevensef.fromtheabyss.register.ModBlockEntity;
import com.elevensef.fromtheabyss.register.ModCreativeTab;
import com.elevensef.fromtheabyss.register.ModItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FromTheAbyss.MOD_ID)
public class FromTheAbyss
{
    // mod id
    public static final String MOD_ID = "fromtheabyss";

    public FromTheAbyss(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);


        ModBlockEntity.register(modEventBus);
        ModBlock.register(modEventBus);
        ModItem.register(modEventBus);
        ModCreativeTab.register(modEventBus);
    }
}
