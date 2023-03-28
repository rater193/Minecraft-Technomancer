package net.rater193.technomancer.networking.packets.client;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketC2STestMessage {
    public PacketC2STestMessage() {

    }

    public PacketC2STestMessage(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->  {
            //This logic is handled on the server
            ServerPlayer sender = context.getSender();
            ServerLevel level = sender.getLevel();

            EntityType.COW.spawn(level, null, null, sender.blockPosition(), MobSpawnType.COMMAND, true, false);

        });
        return true;
    }
}
