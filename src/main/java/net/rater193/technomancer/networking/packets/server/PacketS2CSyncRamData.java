package net.rater193.technomancer.networking.packets.server;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
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


    public static boolean rawHandle(PacketS2CSyncRamData t, Supplier<NetworkEvent.Context> supplier) {
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
            t.onSharedInvoke();
            t.onSharedInvoke(sender2, level2, supplier);
        });
        return true;
    }

    public static void rawToBytes(PacketS2CSyncRamData t, FriendlyByteBuf buf) {
        buf.writeInt(t.RAM);
    }

    @Override
    public <T extends PacketShared> void toBytes(T t, FriendlyByteBuf buf) {
        super.toBytes(t, buf);
        buf.writeInt(RAM);
    }

    @Override
    public void onClientReceive() {
        super.onClientReceive();
        System.out.println("[rater193] RAM MESSAGE RECEIVED");
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("RAM UPDATED"));
        ClientRamData.set(RAM);
    }
}
