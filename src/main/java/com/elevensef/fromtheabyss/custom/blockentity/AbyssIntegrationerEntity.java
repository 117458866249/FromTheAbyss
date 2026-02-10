package com.elevensef.fromtheabyss.custom.blockentity;

import com.elevensef.fromtheabyss.Utils;
import com.elevensef.fromtheabyss.register.ModBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AbyssIntegrationerEntity extends BlockEntity {
    private static final Logger log = LogManager.getLogger(AbyssIntegrationerEntity.class);
    private final ItemStackHandler itemHandler = new ItemStackHandler(0){
        @Override
        protected void onContentsChanged(int slot){
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;

    private int progress = 0;

    private int maxProgess = 1000;

    public AbyssIntegrationerEntity(BlockPos pos, BlockState state){
        super(ModBlockEntity.ABYSS_INTEGRATIONER.get(),pos,state);
        this.data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i){
                    case 0 -> AbyssIntegrationerEntity.this.progress;
                    case 1 -> AbyssIntegrationerEntity.this.maxProgess;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch (i){
                    case 0 -> AbyssIntegrationerEntity.this.progress = i1;
                    case 1 -> AbyssIntegrationerEntity.this.maxProgess = i1;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public static void tick(Level level,BlockPos pos,BlockState state,AbyssIntegrationerEntity pEntity) {
        if (level.isClientSide()){
            return;
        }
        if (pEntity.progress >= pEntity.maxProgess){
            pEntity.resetProgress();
        } else {
            pEntity.progress++;
        }
        if (pEntity.progress%30 == 4){
            Utils.runCommandForBE("particle minecraft:dripping_water ~ ~ ~ 0.6 0 0.4 0.06 2 force",pEntity);
        }
        Utils.runCommandForBE("execute positioned ~ ~-0.5 ~ if entity @e[type=minecraft:item,distance=..1,nbt={Item:{id:\"minecraft:deepslate\"}},limit=1] run execute if entity @e[type=minecraft:item,distance=..1,nbt={Item:{id:\"minecraft:lapis_lazuli\"}},limit=1] run function fromtheabyss:abyss_integrationer",pEntity);
    }

    private void resetProgress() {
        this.progress = 0;
    }
}
