package com.elevensef.fromtheabyss.register;

import com.elevensef.fromtheabyss.FromTheAbyss;
import com.elevensef.fromtheabyss.custom.blockentity.AbyssIntegrationerEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FromTheAbyss.MOD_ID);

    public static final RegistryObject<BlockEntityType<AbyssIntegrationerEntity>> ABYSS_INTEGRATIONER =
            BLOCK_ENTITIES.register("abyss_integrationer",()->
                    BlockEntityType.Builder.of(AbyssIntegrationerEntity::new,
                            ModBlock.ABYSS_INTEGRATIONER_B.get()).build(null));

    public static void register(IEventBus eventBus) { BLOCK_ENTITIES.register(eventBus); }
}
