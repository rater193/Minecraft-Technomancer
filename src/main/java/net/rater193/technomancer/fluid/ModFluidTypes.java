package net.rater193.technomancer.fluid;

import com.mojang.math.Vector3f;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rater193.technomancer.Technomancer;

public class ModFluidTypes {
    public static final ResourceLocation LQCRYSTAL_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation LQCRYSTAL_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation LQCRYSTAL_OVERLAY_RL = new ResourceLocation("misc/in_soap_water");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Technomancer.MOD_ID);

    public static final RegistryObject<FluidType> LQCRYSTAL_FLUID_TYPE = register("liquidcrystal_fluid",
            FluidType.Properties.create()
                    .canDrown(true)
                    .temperature(100)
                    .canConvertToSource(false)
                    .canExtinguish(false)
                    .canSwim(true)
                    .canHydrate(false)
                    .viscosity(5)
                    .lightLevel(2)
                    .density(15)
                    .sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK)
    );

    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(
                LQCRYSTAL_STILL_RL, LQCRYSTAL_FLOWING_RL, LQCRYSTAL_OVERLAY_RL,
                0xA1E038D0, new Vector3f(244f/255f, 56f/255f, 208f/255f),
                properties
                ));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
