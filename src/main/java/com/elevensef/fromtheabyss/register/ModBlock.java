package com.elevensef.fromtheabyss.register;

import com.elevensef.fromtheabyss.FromTheAbyss;
import com.elevensef.fromtheabyss.custom.block.AbyssIntegrationerBlock;
import com.elevensef.fromtheabyss.custom.blockitem.AbyssIntegrationer;
import com.elevensef.fromtheabyss.custom.blockitem.AbyssStorage;
import com.elevensef.fromtheabyss.custom.blockitem.AbyssStorageOutput;
import com.elevensef.fromtheabyss.custom.item.AbyssFissure;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlock {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FromTheAbyss.MOD_ID);


    // block with blockitem

    public static final RegistryObject<Block> ABYSS_ALLOY_BLOCK = registerBlock("abyss_alloy_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(1.2f,3.2f)
                    .requiresCorrectToolForDrops()
            ));




    //another register

    public static final RegistryObject<Block> ABYSS_INTEGRATIONER_B = BLOCKS.register("abyss_integrationer",
            () -> new AbyssIntegrationerBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(1.2f, 3.2f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()));

    public static final RegistryObject<Item> ABYSS_INTEGRATIONER = ModItem.ITEMS.register("abyss_integrationer",
            () -> new AbyssIntegrationer(ABYSS_INTEGRATIONER_B.get(), new Item.Properties()));



    public static final RegistryObject<Block> ABYSS_FISSURE_B = BLOCKS.register("abyss_fissure",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BEDROCK)
            ));

    public static final RegistryObject<Item> ABYSS_FISSURE = ModItem.ITEMS.register("abyss_fissure",
            () -> new AbyssFissure(ABYSS_FISSURE_B.get(), new Item.Properties()));



    public static final RegistryObject<Block> ABYSS_STORAGE_B = BLOCKS.register("abyss_storage",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(1.2f, 3.2f)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Item> ABYSS_STORAGE = ModItem.ITEMS.register("abyss_storage",
            () -> new AbyssStorage(ABYSS_STORAGE_B.get(), new Item.Properties()));



    public static final RegistryObject<Block> ABYSS_STORAGE_OUTPUT_B = BLOCKS.register("abyss_storage_output",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(1.2f, 3.2f)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Item> ABYSS_STORAGE_OUTPUT = ModItem.ITEMS.register("abyss_storage_output",
            () -> new AbyssStorageOutput(ABYSS_STORAGE_OUTPUT_B.get(), new Item.Properties()));


    //another

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItem.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
