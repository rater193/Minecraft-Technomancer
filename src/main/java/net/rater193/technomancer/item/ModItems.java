package net.rater193.technomancer.item;

import net.minecraft.world.item.Rarity;
import net.rater193.technomancer.Technomancer;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rater193.technomancer.item.custom.ItemThermalPasteTube;
import net.rater193.technomancer.item.custom.ItemTooltipHelper;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Technomancer.MOD_ID);

    //Creating our items
    public static final RegistryObject<Item> THERMALPASTE = ITEMS.register("thermalpaste",
            () -> new ItemTooltipHelper(
                    new Item.Properties().tab(ModCreativeModeTab.CREATIVE_MODE_TAB)
                            .stacksTo(64),
                    "Craft with an empty tube to \ncreate a full tube of thermal \npaste."
            ));
    public static final RegistryObject<Item> RAWTHERMALPASTE = ITEMS.register("thermalpaste_raw",
            () -> new ItemTooltipHelper(
                    new Item.Properties().tab(ModCreativeModeTab.CREATIVE_MODE_TAB)
                            .stacksTo(64),
                    "Smelt the raw thermal paste \nin order to get thermal paste."
            ));
    public static final RegistryObject<Item> THERMALPASTE_TUBE = ITEMS.register("thermalpaste_tube",
            () -> new ItemTooltipHelper(
                    new Item.Properties().tab(ModCreativeModeTab.CREATIVE_MODE_TAB)
                            .stacksTo(16),
                    "Allthough empty, this was once \nused to make machines cool down. \nCraft it with thermal paste to \nmake a full tube."
            ));
    public static final RegistryObject<Item> THERMALPASTE_TUBE_FULL = ITEMS.register("thermalpaste_tube_full",
            () -> new ItemThermalPasteTube(
                    new Item.Properties().tab(ModCreativeModeTab.CREATIVE_MODE_TAB)
                            .stacksTo(1)
            ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
