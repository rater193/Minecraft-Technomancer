package net.rater193.technomancer.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rater193.technomancer.Technomancer;
import net.rater193.technomancer.block.custom.BlockClickLight;
import net.rater193.technomancer.block.custom.BlockCropCrystalShard;
import net.rater193.technomancer.item.ModCreativeModeTab;
import net.rater193.technomancer.item.ModItems;
import net.rater193.technomancer.item.custom.BlockItemTooltipHelper;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Technomancer.MOD_ID);

    ///////////////////////////////////////////////////////////////////////////////
    //                                  BLOCKS                                   //
    ///////////////////////////////////////////////////////////////////////////////

    public static final RegistryObject<Block> THERMAL_PASTE_ORE = registerBasicBlock("thermalpaste_ore", Material.STONE);
    public static final RegistryObject<Block> THERMAL_PASTE_DEEPSLATE_ORE = registerBasicBlock("thermalpaste_deepslate_ore", Material.STONE);
    public static final RegistryObject<Block> THERMAL_PASTE_RAW_BLOCK = registerBasicBlock("thermalpaste_raw_block", Material.CLAY);
    public static final RegistryObject<Block> THERMAL_PASTE_BLOCK = registerBasicBlock("thermalpaste_block", Material.CLAY);
    public static final RegistryObject<Block> MACHINE_BLOCK = registerBasicBlock("machineblock", Material.HEAVY_METAL);
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
    public static final RegistryObject<Block> BLOCKCROPCRYSTALSHARD = BLOCKS.register("crop_crystal_shard",
            () -> new BlockCropCrystalShard(BlockBehaviour.Properties.copy(Blocks.WHEAT)));


    ///////////////////////////////////////////////////////////////////////////////
    //                                  METHODS                                  //
    ///////////////////////////////////////////////////////////////////////////////

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> returnBlock = BLOCKS.register(name, block);
        registerBlockItem(name, returnBlock, tab);

        return returnBlock;
    }

    private static RegistryObject<Block> registerBasicBlock(String name, Material material) {
        Supplier<Block> block = () -> new Block(
                BlockBehaviour.Properties.of(material)
                        .strength(6f)
                        .requiresCorrectToolForDrops()
        );

        return registerBlock(name,
                block,
                ModCreativeModeTab.CREATIVE_MODE_TAB
        );
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItemTooltipHelper(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
