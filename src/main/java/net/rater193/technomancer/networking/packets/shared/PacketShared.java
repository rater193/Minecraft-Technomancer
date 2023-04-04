package net.rater193.technomancer.networking.packets.shared;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketShared {
    ////////////////////////////////////////////////////////////////////
    //                              CORE                              //
    ////////////////////////////////////////////////////////////////////

    //**********CONSTRUCTORS**********//
    public PacketShared() {

    }

    public PacketShared(FriendlyByteBuf buf) {

    }

    //************METHODS*************//
    /**
     * This method is what you can override in order to send messages to/from the server.
     *
     * @param  buf  The buffer message that we will write our data to
     * @return      void
     * @see         FriendlyByteBuf
     */
    public <T extends PacketShared> void toBytes(T t, FriendlyByteBuf buf) { }

    /**
     * Handling the supplier, in order to send messages to the server.
     *
     * @param  supplier  The buffer message that we will write our data to
     * @return      boolean - Weather or not we sent the message to the server successfully.
     * @see         NetworkEvent.Context
     * @see         Supplier
     */

    public <T extends PacketShared> boolean handle(T t, Supplier<NetworkEvent.Context> supplier) {
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

    ////////////////////////////////////////////////////////////////////
    //                       VIRTUAL/OVERRIDES                        //
    ////////////////////////////////////////////////////////////////////

    public void onSharedSend() { }

    public void onSharedSend(ServerPlayer sender, ServerLevel level, NetworkEvent.Context context, Supplier<NetworkEvent.Context> supplier) { }

    public void onSharedInvoke(ServerPlayer sender, ServerLevel level, Supplier<NetworkEvent.Context> supplier) { }

    public void onSharedInvoke() { }

}
