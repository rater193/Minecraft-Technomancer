package net.rater193.technomancer.networking.packets.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.rater193.technomancer.client.ClientRamData;

import java.util.function.Supplier;

public class PacketS2CSyncRamData {
    private final int RAM;
    //private final int MAX_RAM;

    public PacketS2CSyncRamData(int ram) {
        RAM = ram;
        //MAX_RAM = maxRam;
    }

    public PacketS2CSyncRamData(FriendlyByteBuf buf) {
        RAM = buf.readInt();
        //MAX_RAM = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(RAM);
        //buf.writeInt(MAX_RAM);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->  {
            //HERE WE ARE ON THE CLIENT
            ClientRamData.set(RAM);
        });
        return true;
    }
}
