package com.elevensef.fromtheabyss.custom.blockentity.abyssintegrationer;

import com.elevensef.fromtheabyss.register.ModBlockEntity;
import com.elevensef.fromtheabyss.register.ModItem;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.SampledFloat;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class AbyssIntegrationerEntity extends BlockEntity {
    private static final Logger log = LogManager.getLogger(AbyssIntegrationerEntity.class);
    private final ItemStackHandler itemHandler = new ItemStackHandler(2){
        @Override
        protected void onContentsChanged(int slot){
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;

    private int progress = 0;

    private int maxProgess = 25;

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

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap,side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory",itemHandler.serializeNBT());
        nbt.putInt("abyss_integrationer.progess",this.progress);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("abyss_integrationer.progess");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0;i<itemHandler.getSlots();i++){
            inventory.setItem(i,itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level,this.worldPosition,inventory);
    }

    public static void tick(Level level,BlockPos pos,BlockState state,AbyssIntegrationerEntity pEntity) {
        if (level.isClientSide()){
            return;
        }
        if (hasRecipe(pEntity)) {
            pEntity.progress++;
            setChanged(level,pos,state);
            if (pEntity.progress >= pEntity.maxProgess) {
                craftItem(pEntity);
            }
        } else {
            pEntity.resetProgress();
            setChanged(level,pos,state);
        }
        if (pEntity.progress%5==1){
            level.playSound(
                    null,
                    pos.getX() + 0.5,
                    pos.getY() + 1.0,
                    pos.getZ() + 0.5,
                    SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("fromtheabyss","music.machine_working")),
                    SoundSource.VOICE,
                    3,
                    1
            );
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(AbyssIntegrationerEntity pEntity) {

        SimpleContainer dropItem = new SimpleContainer(new ItemStack(ModItem.ABYSS_ALLOY.get()));

        if (hasRecipe(pEntity)) {
            pEntity.itemHandler.extractItem(0,1,false);
            pEntity.itemHandler.extractItem(1,1,false);
            Containers.dropContents(pEntity.level,dropPos(pEntity),dropItem);
            pEntity.resetProgress();
        }
    }

    private static BlockPos dropPos(AbyssIntegrationerEntity pEntity){
        return new BlockPos(pEntity.worldPosition.getX(),pEntity.worldPosition.getY() - 1,pEntity.worldPosition.getZ());
    }

    private static boolean hasRecipe(AbyssIntegrationerEntity entity) {
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }
        boolean hasItemA = entity.itemHandler.getStackInSlot(0).getItem() == Items.LAPIS_LAZULI;
        boolean hasItemB = entity.itemHandler.getStackInSlot(1).getItem() == Items.DEEPSLATE;
        boolean hasItemAAnti = entity.itemHandler.getStackInSlot(1).getItem() == Items.LAPIS_LAZULI;
        boolean hasItemBAnti = entity.itemHandler.getStackInSlot(0).getItem() == Items.DEEPSLATE;

        return (hasItemA && hasItemB)||(hasItemAAnti && hasItemBAnti);
    }
}