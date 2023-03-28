package net.rater193.technomancer.networking.packets.server;

import net.minecraft.network.FriendlyByteBuf;
import net.rater193.technomancer.networking.packets.shared.PacketShared;

public class PacketServer extends PacketShared {
    public PacketServer() {
        super();
    }

    public PacketServer(FriendlyByteBuf buf) {
        super(buf);
    }
}
