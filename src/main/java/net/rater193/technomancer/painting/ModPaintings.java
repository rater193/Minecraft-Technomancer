package net.rater193.technomancer.painting;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rater193.technomancer.Technomancer;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Technomancer.MOD_ID);

    public static final RegistryObject<PaintingVariant> VAR_BLOB = PAINTING_VARIANTS.register("technomancer_blob",
            () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> VAR_CRANE = PAINTING_VARIANTS.register("technomancer_crane",
            () -> new PaintingVariant(16, 32));
    public static final RegistryObject<PaintingVariant> VAR_SHIP = PAINTING_VARIANTS.register("technomancer_ship",
            () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> VAR_SPACESHIP = PAINTING_VARIANTS.register("technomancer_spaceship",
            () -> new PaintingVariant(32, 16));
    public static final RegistryObject<PaintingVariant> VAR_PROCESSOR = PAINTING_VARIANTS.register("technomancer_processor",
            () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> VAR_RATER193 = PAINTING_VARIANTS.register("technomancer_rater193",
            () -> new PaintingVariant(48, 48));

    public static void register(IEventBus event) {
        PAINTING_VARIANTS.register(event);
    }
}
