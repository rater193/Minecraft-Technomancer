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
                            .stacksTo(64)
            ));
    public static final RegistryObject<Item> RAWTHERMALPASTE = ITEMS.register("thermalpaste_raw",
            () -> new ItemTooltipHelper(
                    new Item.Properties().tab(ModCreativeModeTab.CREATIVE_MODE_TAB)
                            .stacksTo(64)
            ));
    public static final RegistryObject<Item> THERMALPASTE_TUBE = ITEMS.register("thermalpaste_tube",
            () -> new ItemTooltipHelper(
                    new Item.Properties().tab(ModCreativeModeTab.CREATIVE_MODE_TAB)
                            .stacksTo(16)
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
