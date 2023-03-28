package net.rater193.technomancer.world.feature;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.rater193.technomancer.Technomancer;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Technomancer.MOD_ID);

    public static final RegistryObject<PlacedFeature> THERMALPASTE_ORE_PLACED = PLACED_FEATURES.register("thermalpaste_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.THERMALPASTE_ORE.getHolder().get(),
                    oreCommonPlacement(42,//Veins per chunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
    public static final RegistryObject<PlacedFeature> END_THERMALPASTE_ORE_PLACED = PLACED_FEATURES.register("end_thermalpaste_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.END_THERMALPASTE_ORE.getHolder().get(),
                    oreCommonPlacement(12,//Veins per chunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
    public static final RegistryObject<PlacedFeature> NETHER_THERMALPASTE_ORE_PLACED = PLACED_FEATURES.register("nether_thermalpaste_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.NETHER_THERMALPASTE_ORE.getHolder().get(),
                    oreCommonPlacement(9,//Veins per chunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    public static List<PlacementModifier> orePlacement(PlacementModifier modifierPerChunk, PlacementModifier modifierDistribution) {
        return List.of(modifierPerChunk, InSquarePlacement.spread(), modifierDistribution, BiomeFilter.biome());
    }
    public static List<PlacementModifier> oreCommonPlacement(int count, PlacementModifier modifier) {
        return orePlacement(CountPlacement.of(count), modifier);
    }
    public static List<PlacementModifier> oreRarePlacement(int count, PlacementModifier modifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(count), modifier);
    }

    public static void register(IEventBus event) {
        PLACED_FEATURES.register(event);
    }
}
