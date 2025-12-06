package com.elevensef.fromtheabyss.register;

import com.elevensef.fromtheabyss.FromTheAbyss;
import com.elevensef.fromtheabyss.custom.item.AbyssAnchor;
import com.elevensef.fromtheabyss.custom.item.BedrockBreaker;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItem {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FromTheAbyss.MOD_ID);


    public static final RegistryObject<Item> ABYSS_ALLOY = ITEMS.register("abyss_alloy",
            ()->new Item(new Item.Properties()
            ));

    public static final RegistryObject<Item> ABYSS_ANCHOR = ITEMS.register("abyss_anchor",
            ()->new AbyssAnchor(new Item.Properties()
            ));

    public static final RegistryObject<Item> ABYSS_CRYSTAL = ITEMS.register("abyss_crystal",
            ()->new Item(new Item.Properties()
            ));

    public static final RegistryObject<Item> BEDROCK_BREAKER = ITEMS.register("bedrock_breaker",
            ()->new BedrockBreaker(new Item.Properties()
                    .stacksTo(1)
            ));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
