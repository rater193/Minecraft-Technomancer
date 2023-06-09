package net.rater193.technomancer.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemTooltipHelper extends Item {
    public ItemTooltipHelper(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        components.add(Component.literal("Technomancer").withStyle(ChatFormatting.YELLOW));
        if(Screen.hasShiftDown()) {
            String itemModNames = getDescriptionId();
            String[] itemNameSubs = itemModNames.split("\\.");
            components.add(Component.translatable("tooltip.item."+itemNameSubs[itemNameSubs.length-1]).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.AQUA));
        }else{
            components.add(Component.translatable("tooltip.pressshiftformoreinfo").withStyle(ChatFormatting.ITALIC));
        }

        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
