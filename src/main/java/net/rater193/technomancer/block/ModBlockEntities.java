package net.rater193.technomancer.block;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rater193.technomancer.Technomancer;
import net.rater193.technomancer.block.entity.BlockEntityGemInfusionStation;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Technomancer.MOD_ID);

    public static final RegistryObject<BlockEntityType<BlockEntityGemInfusionStation>> GEM_INFUSION_STATION =
            BLOCK_ENTITIES.register("gem_infusion_station", () -> BlockEntityType.Builder.of(BlockEntityGemInfusionStation::new,
                    ModBlocks.GEM_INFUSION_STATION.get()).build(null));

    public static void register(IEventBus event) {
        BLOCK_ENTITIES.register(event);
    }
}
