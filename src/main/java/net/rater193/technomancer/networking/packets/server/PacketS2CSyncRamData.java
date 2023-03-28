package net.rater193.technomancer.networking.packets.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.simple.SimpleChannel;
import net.rater193.technomancer.client.ClientRamData;
import net.rater193.technomancer.networking.packets.shared.PacketShared;

import java.util.function.Supplier;

public class PacketS2CSyncRamData extends PacketServer {
    private int RAM;

    public PacketS2CSyncRamData(SimpleChannel channel, int messageID) {
        super(channel, messageID);
    }

    public PacketS2CSyncRamData() {
        super();
    }

    public PacketS2CSyncRamData(int ram) {
        RAM = ram;
        //MAX_RAM = maxRam;
    }

    public PacketS2CSyncRamData(FriendlyByteBuf buf) {
        super();
        RAM = buf.readInt();
        //MAX_RAM = buf.readInt();
    }

    @Override
    public <T extends PacketShared> void toBytes(T t, FriendlyByteBuf buf) {
        super.toBytes(t, buf);
        System.out.println("[rater193] RAM MESSAGE RECEIVED");
        buf.writeInt(RAM);
    }

    @Override
    public void onClientReceive() {
        super.onClientReceive();
        ClientRamData.set(RAM);
    }
}
