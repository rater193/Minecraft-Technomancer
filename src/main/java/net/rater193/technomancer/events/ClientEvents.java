package net.rater193.technomancer.events;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rater193.technomancer.Technomancer;
import net.rater193.technomancer.client.TechnomancerHudOverlay;
import net.rater193.technomancer.networking.ModMessages;
import net.rater193.technomancer.networking.packets.client.PacketC2SDefragRam;
import net.rater193.technomancer.networking.packets.client.PacketC2STestInheritence;
import net.rater193.technomancer.networking.packets.client.PacketC2STestMessage;
import net.rater193.technomancer.utility.KeyBinding;

public class ClientEvents {

    @Mod.EventBusSubscriber(modid = Technomancer.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBinding.DEFRAGMENT_RAM_KEY.consumeClick()) {
                //Minecraft.getInstance().player.sendSystemMessage(Component.literal("[Client Message] You defragmented <ERROR 404, NOT FOUND> ram!"));
                //ModMessages.sendToServer(new PacketC2SDefragRam());
                ModMessages.sendToServer(new PacketC2STestInheritence());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = Technomancer.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.DEFRAGMENT_RAM_KEY);
        }

        @SubscribeEvent
        public static void registerGUIOverlay(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("technomancy", TechnomancerHudOverlay.HUD_TECHNOMANCER);
        }
    }

}
