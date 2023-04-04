package net.rater193.technomancer.networking.packets.client;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.simple.SimpleChannel;
import net.rater193.technomancer.networking.packets.shared.PacketShared;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;
import java.util.function.Supplier;

public class PacketClient extends PacketShared {

    public PacketClient(SimpleChannel channel, int messageID) {
        registerPacket(channel, messageID, this.getClass());
    }

    public PacketClient() {
        super();
    }

    private <T extends PacketClient> void registerPacket(SimpleChannel channel, int messageID, Class<T> packetClass) {
        try {
            System.out.println("[rater193] CLASS: " + this.getClass().getName() + "\nCOMING FROM: " + packetClass.getName());
            channel.messageBuilder(packetClass, messageID, NetworkDirection.PLAY_TO_SERVER)
                    .decoder(buf -> createNewInstance(packetClass))
                    .encoder(this::toBytes)
                    .consumerMainThread(this::handle)
                    .add();
        } catch (Exception e) {
            // handle exceptions
        }
    }



    private <T extends PacketClient> T createNewInstance(Class<T> packetClass) {
        try {
            Constructor<T> constructor = packetClass.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onSharedSend() {
        super.onSharedSend();
        onClientSend();
    }

    @Override
    public void onSharedInvoke(ServerPlayer sender, ServerLevel level, Supplier<NetworkEvent.Context> supplier) {
        super.onSharedInvoke(sender, level, supplier);
        onServerReceive(sender, level, supplier);
    }


    public void onClientSend() {

    }

    public void onServerReceive(ServerPlayer sender, ServerLevel level, Supplier<NetworkEvent.Context> supplier) {

    }
}
