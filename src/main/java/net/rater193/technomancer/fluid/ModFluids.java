package net.rater193.technomancer.fluid;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rater193.technomancer.Technomancer;
import net.rater193.technomancer.block.ModBlocks;
import net.rater193.technomancer.item.ModItems;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, Technomancer.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_LQCRYSTAL = FLUIDS.register("liquidcrystal_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.LQCRYSTAL_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_LQCRYSTAL = FLUIDS.register("flowing_liquidcrystal_fluid",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.LQCRYSTAL_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties LQCRYSTAL_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.LQCRYSTAL_FLUID_TYPE, SOURCE_LQCRYSTAL, FLOWING_LQCRYSTAL)
            .slopeFindDistance(2)
            .levelDecreasePerBlock(2)
            .block(ModBlocks.LQCYSTAL_BLOCK)
            .bucket(ModItems.LQCRYSTAL_BUCKET)
            ;

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
