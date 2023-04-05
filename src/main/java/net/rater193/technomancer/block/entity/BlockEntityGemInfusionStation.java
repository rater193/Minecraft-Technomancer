package net.rater193.technomancer.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.rater193.technomancer.block.ModBlockEntities;
import net.rater193.technomancer.item.ModItems;
import net.rater193.technomancer.screen.MenuGemInfusionStation;
import net.rater193.technomancer.utility.ModLangs;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockEntityGemInfusionStation extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 200;
    private int storedFluid = 2500;
    private int maxFluid = 5000;

    public BlockEntityGemInfusionStation(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GEM_INFUSION_STATION.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch(index) {
                    case 0 -> BlockEntityGemInfusionStation.this.progress;
                    case 1 -> BlockEntityGemInfusionStation.this.maxProgress;
                    case 3 -> BlockEntityGemInfusionStation.this.storedFluid;
                    case 4 -> BlockEntityGemInfusionStation.this.maxFluid;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch(index) {
                    case 0 -> BlockEntityGemInfusionStation.this.progress = value;
                    case 1 -> BlockEntityGemInfusionStation.this.maxProgress = value;
                    case 2 -> BlockEntityGemInfusionStation.this.storedFluid = value;
                    case 3 -> BlockEntityGemInfusionStation.this.maxFluid = value;
                };
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(ModLangs.BLOCKENTITIES.GemInfusionStation);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new MenuGemInfusionStation(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
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
        //Saves the inventory
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("progress", progress);
        nbt.putInt("fluid", storedFluid);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        //Loads the inventory
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("progress");
        storedFluid = nbt.getInt("fluid");
    }

    public void drops() {
        //Drop the tile entity contents in the world
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(level, worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, BlockEntityGemInfusionStation entity) {
        if(level.isClientSide()) return;

        if(hasRecipe(entity)) {
            entity.progress++;
            entity.storedFluid -= 1;

            if(entity.progress >= entity.maxProgress) {
                entity.progress = 0;
                craftItem(entity);
            }

            setChanged(level, blockPos, blockState);
        }else{
            if(entity.progress!=0) {
                entity.progress = 0;
                setChanged(level, blockPos, blockState);
            }
        }
    }

    private static void craftItem(BlockEntityGemInfusionStation entity) {
        if(hasRecipe(entity)) {
            ItemStackHandler handler = entity.itemHandler;
            handler.extractItem(0, 1, false);
            if(handler.getStackInSlot(2).isEmpty()) {
                //Adding new
                handler.setStackInSlot(2, new ItemStack(handler.getStackInSlot(1).getItem()));
            }else{
                //Incrementing
                handler.getStackInSlot(2).setCount(handler.getStackInSlot(2).getCount()+1);
            }
            entity.progress = 0;
        }
    }

    private static boolean hasRecipe(BlockEntityGemInfusionStation entity) {
        SimpleContainer container = new SimpleContainer(entity.itemHandler.getSlots());
        for(int i = 0; i < entity.itemHandler.getSlots(); i++) {
            container.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        boolean hasInputCatalyst = false;
        boolean hasCrystal = false;
        boolean hasWater = false;

        //catalyst
        if(entity.itemHandler.getStackInSlot(0).getItem() == ModItems.CROP_CRYSTAL_SHARD.get()) {
            hasInputCatalyst = true;
        }

        //shards
        if(entity.itemHandler.getStackInSlot(1).getItem() == Items.AMETHYST_SHARD) {
            hasCrystal = true;
        }

        if(entity.itemHandler.getStackInSlot(1).getItem() == Items.ECHO_SHARD ) {
            hasCrystal = true;
        }

        if(entity.itemHandler.getStackInSlot(1).getItem() == Items.PRISMARINE_SHARD) {
            hasCrystal = true;
        }

        //water
        if(entity.storedFluid >= 0) {
            hasWater = true;
        }



        return hasInputCatalyst && hasCrystal && hasWater &&
                canInsertAmountIntoOutputSlot(container, entity, 1) &&
                canInsertItemIntoOutputSlot(container, entity, new ItemStack(entity.itemHandler.getStackInSlot(1).getItem(), 1));
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer container, BlockEntityGemInfusionStation entity, ItemStack itemStack) {

        if(container.getItem(2).isEmpty()) return true;

        if(container.getItem(2).getItem() == itemStack.getItem()) {
            return true;
        }
        return false;
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer container, BlockEntityGemInfusionStation entity, int count) {
        ItemStack slot = container.getItem(2);
        if(slot.isEmpty()) return true;

        if(slot.getCount()+count <= slot.getMaxStackSize()) {
            return true;
        }
        return false;
    }
}
