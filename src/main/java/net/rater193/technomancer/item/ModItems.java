package net.rater193.technomancer.item;

import net.minecraft.world.item.Rarity;
import net.rater193.technomancer.Technomancer;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Technomancer.MOD_ID);

    //Creating our items
    public static final RegistryObject<Item> THERMALPASTE = ITEMS.register("thermalpaste",
            () -> new Item(
                    new Item.Properties().tab(ModCreativeModeTab.CREATIVE_MODE_TAB)
                            .stacksTo(64)
            ));
    public static final RegistryObject<Item> RAWTHERMALPASTE = ITEMS.register("thermalpaste_raw",
            () -> new Item(
                    new Item.Properties().tab(ModCreativeModeTab.CREATIVE_MODE_TAB)
                            .stacksTo(64)
            ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
