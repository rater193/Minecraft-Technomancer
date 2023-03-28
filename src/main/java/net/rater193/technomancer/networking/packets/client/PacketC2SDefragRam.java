package net.rater193.technomancer.networking.packets.client;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.network.NetworkEvent;
import net.rater193.technomancer.block.ModBlocks;
import net.rater193.technomancer.networking.ModMessages;
import net.rater193.technomancer.playerdata.ram.PlayerRam;
import net.rater193.technomancer.playerdata.ram.PlayerRamProvider;
import net.rater193.technomancer.utility.ModLangs;

import java.util.function.Supplier;

import static net.rater193.technomancer.utility.ModLangs.*;
public class PacketC2SDefragRam {
    public PacketC2SDefragRam() {

    }

    public PacketC2SDefragRam(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->  {
            ServerPlayer sender = context.getSender();
            ServerLevel level = sender.getLevel();

            //This logic is handled on the server
            if(playerHasMachineBlockAroundThem(sender, level, 2f)) {

                //Only defragment ram if the player is near a machine block

                //First we want to check how much ram the player has to defragment

                //Then we want to pause the player from being able to run code for 20 ticks after receiving this message on the server

                //Then we want to remove 20 fragmented ram from the player

                //Notify the player how much fragmented ram was removed
                sender.sendSystemMessage(Component.translatable(MESSAGES.MESSAGE_DEFRAG_RAM).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA));
                level.playSound(null, sender.getOnPos(), SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS,
                        0.5f, level.random.nextFloat() * 0.1f + 0.9f);

            }else{
                //Notify the player that there is no water near
                sender.sendSystemMessage(Component.translatable(MESSAGES.MESSAGE_NO_MACHINE_BLOCK_NEAR).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.RED));
                sender.sendSystemMessage(Component.literal("Checking for ram..."));
                sender.getCapability(PlayerRamProvider.PLAYER_RAM).ifPresent(ram -> {
                    sender.sendSystemMessage(Component.literal("You have the ram!" + ram.getMaxRam()));
                });
                //PlayerRam ram = sender.getCapability(PlayerRamProvider.PLAYER_RAM).resolve().get();
                //sender.sendSystemMessage(Component.literal("ram: " + ram.getRam() + " / " + ram.getMaxRam()));
            }
        });
        return true;
    }

    private boolean playerHasMachineBlockAroundThem(ServerPlayer player, ServerLevel level, float size) {
        return level.getBlockStates(player.getBoundingBox().inflate(size))
                .filter( state -> state.is(ModBlocks.MACHINE_BLOCK.get())).toArray().length > 0;
    }
}
