package net.rater193.technomancer.networking.packets.client;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

public class PacketC2STestInheritence extends PacketClient {

    public PacketC2STestInheritence(SimpleChannel channel, int messageID) {
        super(channel, messageID);
    }
    public PacketC2STestInheritence() {
        super();
    }

    @Override
    public void onClientSend() {
        super.onClientSend();
        System.out.println("[rater193] CLIENT SENT");
    }

    @Override
    public void onServerReceive(ServerPlayer sender, ServerLevel level, Supplier<NetworkEvent.Context> supplier) {
        super.onServerReceive(sender, level, supplier);
        System.out.println("[rater193] SERVER RECEIVED");
    }
}
