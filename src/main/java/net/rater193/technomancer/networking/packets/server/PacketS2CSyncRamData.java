package net.rater193.technomancer.networking.packets.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.rater193.technomancer.client.ClientData;

import java.util.function.Supplier;

public class PacketS2CSyncRamData {
    public int RAM;

    public PacketS2CSyncRamData() {
    }

    public PacketS2CSyncRamData(int ram) {
        RAM = ram;
        //MAX_RAM = maxRam;
    }

    public PacketS2CSyncRamData(FriendlyByteBuf buf) {
        super();
        RAM = buf.readInt();
        //MAX_RAM = buf.readInt();
        System.out.println("[rater193] TEST: READING RAM BUFFER: " + RAM);
        ClientData.RAM = RAM;
    }

    public static void toBytes(PacketS2CSyncRamData t, FriendlyByteBuf buf) {
        buf.writeInt(t.RAM);
        System.out.println("[rater193] TEST: WRITING RAM TO BUFFER");
    }

    public static boolean handle(PacketS2CSyncRamData t, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->  {
        });
        return true;
    }
}
