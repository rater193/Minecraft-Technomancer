package net.rater193.technomancer.world.feature;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.rater193.technomancer.Technomancer;

public class ModConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Technomancer.MOD_ID);


    public static void register(IEventBus event) {
        CONFIGURED_FEATURE.register(event);
    }

}
