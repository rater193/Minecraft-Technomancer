package net.rater193.technomancer.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rater193.technomancer.Technomancer;
import net.rater193.technomancer.block.custom.BlockClickLight;
import net.rater193.technomancer.item.ModCreativeModeTab;
import net.rater193.technomancer.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Technomancer.MOD_ID);

    ///////////////////////////////////////////////////////////////////////////////
    //                                  BLOCKS                                   //
    ///////////////////////////////////////////////////////////////////////////////

    public static final RegistryObject<Block> THERMAL_PASTE_ORE = registerBlock("thermalpaste_ore",
            () -> new DropExperienceBlock(
                    BlockBehaviour.Properties.of(Material.STONE)
                            .strength(2f)
                            .requiresCorrectToolForDrops()
                    , UniformInt.of(1,4)
            ),
            ModCreativeModeTab.CREATIVE_MODE_TAB
    );
    public static final RegistryObject<Block> THERMAL_PASTE_DEEPSLATE_ORE = registerBlock("thermalpaste_deepslate_ore",
            () -> new DropExperienceBlock(
                    BlockBehaviour.Properties.of(Material.STONE)
                            .strength(2f)
                            .requiresCorrectToolForDrops()
                    , UniformInt.of(1,4)
            ),
            ModCreativeModeTab.CREATIVE_MODE_TAB
    );
    public static final RegistryObject<Block> THERMAL_PASTE_RAW_BLOCK = registerBlock("thermalpaste_raw_block",
            () -> new Block(
                    BlockBehaviour.Properties.of(Material.CLAY)
                            .strength(6f).requiresCorrectToolForDrops()
            ),
            ModCreativeModeTab.CREATIVE_MODE_TAB
    );
    public static final RegistryObject<Block> THERMAL_PASTE_BLOCK = registerBlock("thermalpaste_block",
            () -> new Block(
                    BlockBehaviour.Properties.of(Material.CLAY)
                            .strength(6f).requiresCorrectToolForDrops()
            ),
            ModCreativeModeTab.CREATIVE_MODE_TAB
    );
    public static final RegistryObject<Block> CLICKLIGHT_LIGHT = registerBlock("clicklight_white",
            () -> new BlockClickLight(
                    BlockBehaviour.Properties.of(Material.CLAY)
                            .strength(6f).requiresCorrectToolForDrops()
                            .lightLevel(
                                    state ->
                                    (state.getValue(BlockClickLight.LIT) ? 15 : 0)

                            )
            ),
            ModCreativeModeTab.CREATIVE_MODE_TAB
    );


    ///////////////////////////////////////////////////////////////////////////////
    //                                  METHODS                                  //
    ///////////////////////////////////////////////////////////////////////////////

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> returnBlock = BLOCKS.register(name, block);
        registerBlockItem(name, returnBlock, tab);

        return returnBlock;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
