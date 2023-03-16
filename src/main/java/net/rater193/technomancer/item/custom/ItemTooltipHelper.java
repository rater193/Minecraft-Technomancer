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
    private String tooltipMessage = "UNSET";
    public ItemTooltipHelper(Properties properties, String tooltip) {
        super(properties);
        tooltipMessage = tooltip;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        components.add(Component.literal("Technomancer").withStyle(ChatFormatting.YELLOW));
        if(Screen.hasShiftDown()) {
            components.add(Component.literal(tooltipMessage).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.AQUA));
        }else{
            components.add(Component.literal("[Press shift for more info]").withStyle(ChatFormatting.ITALIC));
        }

        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
