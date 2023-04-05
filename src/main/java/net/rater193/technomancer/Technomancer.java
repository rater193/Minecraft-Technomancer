package net.rater193.technomancer;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.rater193.technomancer.block.ModBlockEntities;
import net.rater193.technomancer.block.ModBlocks;
import net.rater193.technomancer.fluid.ModFluidTypes;
import net.rater193.technomancer.fluid.ModFluids;
import net.rater193.technomancer.item.ModItems;
import net.rater193.technomancer.networking.ModMessages;
import net.rater193.technomancer.painting.ModPaintings;
import net.rater193.technomancer.screen.ModMenuTypes;
import net.rater193.technomancer.screen.ScreenGemInfusionStation;
import net.rater193.technomancer.villager.ModVillagers;
import net.rater193.technomancer.world.feature.ModConfiguredFeatures;
import net.rater193.technomancer.world.feature.ModPlacedFeatures;

// The value here should match an entry in the META-INF/mods.toml file
// CTRL + F6 LETS YOU SAFELY RENAME FILES, VARIABLES, AND REFERENCES
@Mod(Technomancer.MOD_ID)
public class Technomancer
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "technomancer";
    public static final String MOD_VERSION = "1.0";

    public Technomancer()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Registering our items/blocks
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        // Registering fluids
        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);

        //Registering villagers
        ModVillagers.register(modEventBus);

        //Registering paintings
        ModPaintings.register(modEventBus);

        //Registering terrain features
        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        //Menus
        ModMenuTypes.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        event.enqueueWork(() -> {
            ModMessages.register();
            ModVillagers.registerPOIs();
        });
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            //test
            //ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLOCKCROPCRYSTALSHARD.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_LQCRYSTAL.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_LQCRYSTAL.get(), RenderType.translucent());

            MenuScreens.register(ModMenuTypes.MENU_GEM_INFUSION_STATION.get(), ScreenGemInfusionStation::new);
        }
    }
}
