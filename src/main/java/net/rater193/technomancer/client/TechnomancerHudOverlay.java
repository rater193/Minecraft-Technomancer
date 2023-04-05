package net.rater193.technomancer.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
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
        for(int i = 0; i < 20; i++) {
            GuiComponent.blit(poseStack,
                    x-180+(i*4), y-20, // Position
                    0, 0, // tex offset
                    4, 8, // tex size
                    4, 8); // screen size
        }

        RenderSystem.setShaderTexture(0, ModResourceLocations.RAM_FULL);

        float cap = (((float)ClientData.RAM/(float)ClientData.MAX_RAM)*20f);
        for(int i = 0; i < 20; i++) {
            if((float)i<=(int)(cap+1)) {
                GuiComponent.blit(poseStack,
                        x - 180 + (i * 4), y - 20, // Position
                        0, 0, // tex offset
                        Math.min(4,(int)((cap-i)*4)), 8, // tex size
                        4, 8); // screen size
            }else{
                break;
            }
        }

        RenderSystem.setShaderTexture(0, ModResourceLocations.RAM_FRAGMENTED);
        cap = (((float)ClientData.RAM_FRAGMENTED/(float)ClientData.MAX_RAM)*20f);
        for(int i = 0; i < 20; i++) {
            if((float)i<=(int)(cap+1)) {
                GuiComponent.blit(poseStack,
                        x - 180 + (i * 4), y - 20, // Position
                        0, 0, // tex offset
                        Math.min(4,(int)((cap-i)*4)), 8, // tex size
                        4, 8); // screen size
            }else{
                break;
            }
        }

        String displayText = "Ram: " + (ClientData.RAM - ClientData.RAM_FRAGMENTED) + " / " + (ClientData.MAX_RAM - ClientData.RAM_FRAGMENTED);
        //Shadow
        Minecraft.getInstance().font.draw(poseStack, displayText, x - 180 + 1, y - 20 - 8 + 1, 0);
        //Text
        Minecraft.getInstance().font.draw(poseStack, displayText, x - 180, y - 20 - 8, 0xffffffff);

        if(ClientData.RAM_FRAGMENTED>0) {
            displayText = "(" + ClientData.RAM_FRAGMENTED + ")";
            //Shadow
            Minecraft.getInstance().font.draw(poseStack, displayText, x - 180 + 1, y - 20 - 8 - 8 + 1, 0);
            //Text
            Minecraft.getInstance().font.draw(poseStack, displayText, x - 180, y - 20 - 8 - 8, 0xff994444);
        }
    };
}
