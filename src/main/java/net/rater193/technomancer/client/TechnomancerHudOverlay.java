package net.rater193.technomancer.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.rater193.technomancer.utility.ModResourceLocations;

public class TechnomancerHudOverlay {

    public static final IGuiOverlay HUD_TECHNOMANCER = (ForgeGui gui, PoseStack poseStack, float partialTick, int width, int height) -> {
        int x = width/2;
        int y = height;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, ModResourceLocations.RAM_EMPTY);
        for(int i = 0; i < 10; i++) {
            GuiComponent.blit(poseStack,
                    x-94+(i*8), y-54, // Position
                    0, 0, // offset
                    8, 16, // size
                    8, 16); // position
        }

        RenderSystem.setShaderTexture(0, ModResourceLocations.RAM_FULL);

        float mult = 0.5f;
        for(int i = 0; i < 5; i++) {
            if((float)i<=10*mult) {
                GuiComponent.blit(poseStack,
                        x - 94 + (i * 8), y - 54, // Position
                        0, 0, // tex offset
                        4, 16, // tex size
                        8, 16); // screen size
            }
        }
    };
}
