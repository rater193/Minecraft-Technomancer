package net.rater193.technomancer.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab CREATIVE_MODE_TAB = new CreativeModeTab("technomancer_creative_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.THERMALPASTE.get());
        }
    };
}
