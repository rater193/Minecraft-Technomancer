package net.rater193.technomancer.world.feature;

import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.rater193.technomancer.Technomancer;
import net.rater193.technomancer.block.ModBlocks;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Technomancer.MOD_ID);

    //The configured list of features to modify for spawning in our ore
    //Overworld
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_THERMALPASTE_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.THERMAL_PASTE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.THERMAL_PASTE_DEEPSLATE_ORE.get().defaultBlockState())
    ));

    //End
    public static final Supplier<List<OreConfiguration.TargetBlockState>> END_THERMALPASTE_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), ModBlocks.THERMAL_PASTE_ORE.get().defaultBlockState())
    ));

    //Nether
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_THERMALPASTE_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.THERMAL_PASTE_ORE.get().defaultBlockState())
    ));


    //Registering the features to spawn in the overworld
    public static final RegistryObject<ConfiguredFeature<?, ?>> THERMALPASTE_ORE = CONFIGURED_FEATURE.register("thermalpaste_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_THERMALPASTE_ORES.get(), 7/*VEIN SIZE*/)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> END_THERMALPASTE_ORE = CONFIGURED_FEATURE.register("end_thermalpaste_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(END_THERMALPASTE_ORES.get(), 9/*VEIN SIZE*/)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> NETHER_THERMALPASTE_ORE = CONFIGURED_FEATURE.register("nether_thermalpaste_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_THERMALPASTE_ORES.get(), 12/*VEIN SIZE*/)));

    public static void register(IEventBus event) {
        CONFIGURED_FEATURE.register(event);
    }

}
