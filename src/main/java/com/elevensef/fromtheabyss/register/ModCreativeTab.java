package com.elevensef.fromtheabyss.register;

import com.elevensef.fromtheabyss.FromTheAbyss;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTab {
    public static final String CR_TAB = "creativetab.fromtheabyss_tab";
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FromTheAbyss.MOD_ID);
    public static final RegistryObject<CreativeModeTab> FROMTHEABYSS_TAB = CREATIVE_MODE_TABS.register("fromtheabyss_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItem.ABYSS_ALLOY.get()))
                    .title(Component.translatable(CR_TAB))
                    .displayItems((pParameters, pOutput) -> {

                        //   m/38
                        pOutput.accept(ModItem.ABYSS_ALLOY.get());
                        pOutput.accept(ModItem.ABYSS_CRYSTAL.get());

                        //   m/40 L
                        pOutput.accept(ModItem.ABYSS_ANCHOR.get());
                        pOutput.accept(ModItem.BEDROCK_BREAKER.get());


                        //   m/38
                        pOutput.accept(ModBlock.ABYSS_INTEGRATIONER.get());
                        pOutput.accept(ModBlock.ABYSS_ALLOY_BLOCK.get());

                        //   m/40 L
                        pOutput.accept(ModBlock.ABYSS_FISSURE.get());
                        pOutput.accept(ModBlock.ABYSS_ANCHOR_TEMP.get());

                        //   m 41 I
                        pOutput.accept(ModBlock.ABYSS_STORAGE.get());
                        pOutput.accept(ModBlock.ABYSS_STORAGE_OUTPUT.get());
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
