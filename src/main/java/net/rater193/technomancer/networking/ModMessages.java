package net.rater193.technomancer.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.rater193.technomancer.Technomancer;
import net.rater193.technomancer.networking.packets.client.PacketC2SDefragRam;
import net.rater193.technomancer.networking.packets.client.PacketC2STestMessage;
import net.rater193.technomancer.networking.packets.server.PacketS2CSyncRamData;

public class ModMessages {

    private static SimpleChannel INSTANCE;

    private static int PACKET_ID = 0;
    private static int id() {
        return PACKET_ID++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Technomancer.MOD_ID, "messages"))
                .networkProtocolVersion(() -> Technomancer.MOD_VERSION)
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(PacketC2STestMessage.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PacketC2STestMessage::new)
                .encoder(PacketC2STestMessage::toBytes)
                .consumerMainThread(PacketC2STestMessage::handle)
                .add();

        net.messageBuilder(PacketC2SDefragRam.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PacketC2SDefragRam::new)
                .encoder(PacketC2SDefragRam::toBytes)
                .consumerMainThread(PacketC2SDefragRam::handle)
                .add();

        net.messageBuilder(PacketS2CSyncRamData.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PacketS2CSyncRamData::new)
                .encoder(PacketS2CSyncRamData::toBytes)
                .consumerMainThread(PacketS2CSyncRamData::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

}
