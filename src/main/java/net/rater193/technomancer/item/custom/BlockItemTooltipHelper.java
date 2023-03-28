package net.rater193.technomancer.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.rater193.technomancer.utility.ModLangs;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockItemTooltipHelper extends BlockItem {

    public BlockItemTooltipHelper(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        components.add(Component.literal("Technomancer").withStyle(ChatFormatting.YELLOW));
        if(Screen.hasShiftDown()) {
            String blockModName = getDescriptionId();
            String[] blockNameSubs = blockModName.split("\\.");
            components.add(Component.translatable(ModLangs.ITEMS.ToolTipBase+blockNameSubs[blockNameSubs.length-1]).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.AQUA));
        }else{
            components.add(Component.translatable(ModLangs.ITEMS.ToolTipKey).withStyle(ChatFormatting.ITALIC));
        }

        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
