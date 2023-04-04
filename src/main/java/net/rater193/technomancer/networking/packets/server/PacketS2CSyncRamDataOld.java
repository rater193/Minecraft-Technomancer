package net.rater193.technomancer.networking.packets.server;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.simple.SimpleChannel;
import net.rater193.technomancer.client.ClientRamData;

import java.util.function.Supplier;

public class PacketS2CSyncRamDataOld extends PacketServer {
    public int RAM;

    public PacketS2CSyncRamDataOld(SimpleChannel channel, int messageID) {
        super(channel, messageID);
    }

    public PacketS2CSyncRamDataOld() {
    }

    public PacketS2CSyncRamDataOld(int ram) {
        RAM = ram;
        //MAX_RAM = maxRam;
    }

    public PacketS2CSyncRamDataOld(FriendlyByteBuf buf) {
        super();
        RAM = buf.readInt();
        //MAX_RAM = buf.readInt();
    }


    public static boolean rawHandle(PacketS2CSyncRamDataOld t, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();

        //Method execution to help the API work better
        //Executed from the sender
        ServerPlayer sender1 = context.getSender();
        ServerLevel level1 = sender1.getLevel();

        t.onSharedSend();
        t.onSharedSend(sender1, level1, context, supplier);

        context.enqueueWork(() ->  {
            System.out.println("[rater193] RAM MESSAGE RECEIVED: " + t.getClass().getName());

            ServerPlayer sender2 = context.getSender();
            ServerLevel level2 = sender2.getLevel();
            //Method execution to help the API work better
            //Executed on the target
            System.out.println("[rater193] Processing client request. " + t.getClass().getName());
        });
        return true;
    }

    public static void rawToBytes(PacketS2CSyncRamDataOld t, FriendlyByteBuf buf) {
        buf.writeInt(t.RAM);
    }

    public static <T extends PacketS2CSyncRamDataOld> void toBytes(T t, FriendlyByteBuf buf) {
        buf.writeInt(t.RAM);
        System.out.println("[rater193] WRITING RAM TO BUFFER");
    }


    /*
    @Override
    public <T extends PacketShared> void toBytes(T t, FriendlyByteBuf buf) {
        super.toBytes(t, buf);
        buf.writeInt(RAM);
    }
     */

    @Override
    public void onClientReceive() {
        super.onClientReceive();
        System.out.println("[rater193] RAM MESSAGE RECEIVED");
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("RAM UPDATED"));
        ClientRamData.set(RAM);
    }
}
