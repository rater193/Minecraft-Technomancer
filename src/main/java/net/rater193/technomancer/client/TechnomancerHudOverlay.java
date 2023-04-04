package net.rater193.technomancer.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
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
                    x-180+(i*8), y-20, // Position
                    0, 0, // offset
                    8, 16, // size
                    8, 16); // position
        }

        RenderSystem.setShaderTexture(0, ModResourceLocations.RAM_FULL);

        float cap = (((float)ClientData.RAM/(float)ClientData.MAX_RAM)*10f);
        for(int i = 0; i < 10; i++) {
            if((float)i<=(int)(cap+1)) {
                GuiComponent.blit(poseStack,
                        x - 180 + (i * 8), y - 20, // Position
                        0, 0, // tex offset
                        Math.min(8,(int)((cap-i)*8)), 16, // tex size
                        8, 16); // screen size
            }else{
                break;
            }
        }
        //RenderSystem.getF
        //GuiComponent.drawCenteredString(poseStack, GuiComponent.
        //        );
        //
    };
}
