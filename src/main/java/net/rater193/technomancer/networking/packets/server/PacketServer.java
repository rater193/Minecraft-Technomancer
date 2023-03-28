package net.rater193.technomancer.networking.packets.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.simple.SimpleChannel;
import net.rater193.technomancer.networking.packets.shared.PacketShared;

import java.lang.reflect.Constructor;
import java.util.function.Supplier;

public class PacketServer extends PacketShared {

    public PacketServer(SimpleChannel channel, int messageID) {
        registerPacket(channel, messageID, this.getClass());
    }

    public PacketServer() {
        super();
    }

    public PacketServer(FriendlyByteBuf buf) {
    }

    private <T extends PacketServer> void registerPacket(SimpleChannel channel, int messageID, Class<T> packetClass) {
        try {
            channel.messageBuilder(packetClass, messageID, NetworkDirection.PLAY_TO_CLIENT)
                    .decoder(buf -> createNewInstance(packetClass))
                    .encoder(this::toBytes)
                    .consumerMainThread(this::handle)
                    .add();
        } catch (Exception e) {
            // handle exceptions
        }
    }



    private <T extends PacketServer> T createNewInstance(Class<T> packetClass) {
        try {
            Constructor<T> constructor = packetClass.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onSharedSend(ServerPlayer sender, ServerLevel level, NetworkEvent.Context context, Supplier<NetworkEvent.Context> supplier) {
        super.onSharedSend(sender, level, context, supplier);
        onServerSend(sender, level, context);
    }

    @Override
    public void onSharedInvoke() {
        super.onSharedInvoke();
    }

    @Override
    public void onSharedInvoke(ServerPlayer sender, ServerLevel level, Supplier<NetworkEvent.Context> supplier) {
        super.onSharedInvoke(sender, level, supplier);
        onClientReceive();
    }

    public void onServerSend(ServerPlayer sender, ServerLevel level, NetworkEvent.Context context) {

    }

    public void onClientReceive() {

    }
}
