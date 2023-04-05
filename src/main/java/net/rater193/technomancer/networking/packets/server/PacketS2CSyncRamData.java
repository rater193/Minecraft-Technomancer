package net.rater193.technomancer.networking.packets.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.rater193.technomancer.client.ClientData;
import net.rater193.technomancer.playerdata.ram.PlayerRam;

import java.util.function.Supplier;

public class PacketS2CSyncRamData {
    public int RAM;
    public int RAM_FRAGMENTED;
    public int MAX_RAM;

    public PacketS2CSyncRamData() {
    }

    public PacketS2CSyncRamData(PlayerRam ram) {
        RAM = ram.getRam();
        RAM_FRAGMENTED = ram.getFragmentedRam();
        MAX_RAM = ram.getMaxRam();
    }

    public PacketS2CSyncRamData(FriendlyByteBuf buf) {
        super();
        RAM = buf.readInt();
        RAM_FRAGMENTED = buf.readInt();
        MAX_RAM = buf.readInt();

        ClientData.RAM = RAM;
        ClientData.RAM_FRAGMENTED = RAM_FRAGMENTED;
        ClientData.MAX_RAM = MAX_RAM;
    }

    public static void toBytes(PacketS2CSyncRamData t, FriendlyByteBuf buf) {
        buf.writeInt(t.RAM);
        buf.writeInt(t.RAM_FRAGMENTED);
        buf.writeInt(t.MAX_RAM);
    }

    public static boolean handle(PacketS2CSyncRamData t, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->  {
        });
        return true;
    }
}
