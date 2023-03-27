package net.rater193.technomancer.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;
import net.rater193.technomancer.Technomancer;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rater193.technomancer.block.ModBlocks;
import net.rater193.technomancer.item.custom.ItemThermalPasteTube;
import net.rater193.technomancer.item.custom.ItemTooltipHelper;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Technomancer.MOD_ID);

    //Creating our items
    public static final RegistryObject<Item> THERMALPASTE = registerItem("thermalpaste", 64);
    public static final RegistryObject<Item> RAWTHERMALPASTE = registerItem("thermalpaste_raw", 64);
    public static final RegistryObject<Item> PRODECK_BETA = registerItem("prodeck", 1);
    public static final RegistryObject<Item> THERMALPASTE_TUBE = registerItem("thermalpaste_tube", 16);
    public static final RegistryObject<Item> CROP_CRYSTAL_SHARD_SEEDS = ITEMS.register("crop_crystal_shard_seeds",
            () -> new ItemNameBlockItem(ModBlocks.BLOCKCROPCRYSTALSHARD.get(),
                    new Item.Properties().tab(ModCreativeModeTab.CREATIVE_MODE_TAB))
            );
    public static final RegistryObject<Item> CROP_CRYSTAL_SHARD = ITEMS.register("crop_crystal_shard",
            () -> new Item(
                    new Item.Properties()
                            .tab(ModCreativeModeTab.CREATIVE_MODE_TAB)
                            .food(
                                    new FoodProperties.Builder()
                                            .nutrition(10)
                                            .saturationMod(10f)
                                            .build()
                            )
            )
    );
    public static final RegistryObject<Item> THERMALPASTE_TUBE_FULL = ITEMS.register("thermalpaste_tube_full",
            () -> new ItemThermalPasteTube(
                    new Item.Properties().tab(ModCreativeModeTab.CREATIVE_MODE_TAB)
                            .stacksTo(1)
            ));

    public static RegistryObject<Item> registerItem(String itemName, int maxStackSize) {
        return ITEMS.register(itemName,
                () -> new ItemTooltipHelper(
                        new Item.Properties().tab(ModCreativeModeTab.CREATIVE_MODE_TAB)
                                .stacksTo(maxStackSize)
                ));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
