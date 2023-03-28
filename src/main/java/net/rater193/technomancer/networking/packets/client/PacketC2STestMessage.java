package net.rater193.technomancer.networking.packets.client;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

public class PacketC2STestMessage extends PacketClient {
    public PacketC2STestMessage(SimpleChannel channel, int messageID) {
        super(channel, messageID);
    }
    public PacketC2STestMessage() {
        super();
    }

    @Override
    public void onServerReceive(ServerPlayer sender, ServerLevel level, Supplier<NetworkEvent.Context> supplier) {
        super.onServerReceive(sender, level, supplier);
        EntityType.COW.spawn(level, null, null, sender.blockPosition(), MobSpawnType.COMMAND, true, false);
    }
}
