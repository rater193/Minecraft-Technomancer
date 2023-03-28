package net.rater193.technomancer.networking.packets.shared;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;
import net.rater193.technomancer.networking.ModMessages;
import net.rater193.technomancer.networking.packets.server.PacketS2CSyncRamData;
import net.rater193.technomancer.playerdata.ram.PlayerRamProvider;
import net.rater193.technomancer.utility.ModLangs;

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
        onSharedSend(context, supplier);

        context.enqueueWork(() ->  {
            ServerPlayer sender = context.getSender();
            ServerLevel level = sender.getLevel();

            //Method execution to help the API work better
            //Executed on the target
            onSharedInvoke(sender, level, supplier);
        });
        return true;
    }

    ////////////////////////////////////////////////////////////////////
    //                       VIRTUAL/OVERRIDES                        //
    ////////////////////////////////////////////////////////////////////

    public void onSharedSend(NetworkEvent.Context context, Supplier<NetworkEvent.Context> supplier) { }

    public void onSharedInvoke(ServerPlayer sender, ServerLevel level, Supplier<NetworkEvent.Context> supplier) { }

}
