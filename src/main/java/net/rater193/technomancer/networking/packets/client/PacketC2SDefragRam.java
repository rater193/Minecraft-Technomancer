package net.rater193.technomancer.networking.packets.client;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.simple.SimpleChannel;
import net.rater193.technomancer.block.ModBlocks;
import net.rater193.technomancer.client.ClientData;
import net.rater193.technomancer.networking.ModMessages;
import net.rater193.technomancer.networking.packets.server.PacketS2CSyncRamData;
import net.rater193.technomancer.playerdata.ram.PlayerRamProvider;

import java.util.function.Supplier;

import static net.rater193.technomancer.utility.ModLangs.*;
public class PacketC2SDefragRam {

    public static boolean playerHasMachineBlockAroundThem(ServerPlayer player, ServerLevel level, float size) {
        return level.getBlockStates(player.getBoundingBox().inflate(size))
                .filter( state -> state.is(ModBlocks.MACHINE_BLOCK.get())).toArray().length > 0;
    }

    public PacketC2SDefragRam() {
    }

    public PacketC2SDefragRam(int ram) {
        //MAX_RAM = maxRam;
    }

    public PacketC2SDefragRam(FriendlyByteBuf buf) {
        super();
    }

    public static void toBytes(PacketC2SDefragRam t, FriendlyByteBuf buf) {
    }

    public static boolean handle(PacketC2SDefragRam t, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        ServerPlayer sender = context.getSender();
        ServerLevel level = sender.getLevel();

        context.enqueueWork(() ->  {


            //This logic is handled on the server
            if(playerHasMachineBlockAroundThem(sender, level, 2f)) {

                //Only defragment ram if the player is near a machine block

                //First we want to check how much ram the player has to defragment

                //Then we want to pause the player from being able to run code for 20 ticks after receiving this message on the server

                //Then we want to remove 20 fragmented ram from the player

                //Notify the player how much fragmented ram was removed
                sender.sendSystemMessage(Component.translatable(MESSAGES.MESSAGE_DEFRAG_RAM).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_AQUA));
                sender.getCapability(PlayerRamProvider.PLAYER_RAM).ifPresent(ram -> {
                    //sender.sendSystemMessage(Component.literal("You have the ram!" + ram.getMaxRam()));
                    ModMessages.sendToPlayer(new PacketS2CSyncRamData(ram.getRam()), sender);
                });
                level.playSound(null, sender.getOnPos(), SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS,
                        0.5f, level.random.nextFloat() * 0.1f + 0.9f);

            }else{
                //Notify the player that there is no water near
                sender.sendSystemMessage(Component.translatable(MESSAGES.MESSAGE_NO_MACHINE_BLOCK_NEAR).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.RED));
                sender.sendSystemMessage(Component.literal("Checking for ram..."));
                sender.getCapability(PlayerRamProvider.PLAYER_RAM).ifPresent(ram -> {
                    //sender.sendSystemMessage(Component.literal("You have the ram!" + ram.getMaxRam()));
                    ModMessages.sendToPlayer(new PacketS2CSyncRamData(ram.getRam()), sender);
                    sender.sendSystemMessage(Component.literal("Sending ram to client: " + ram.getRam()));
                });
                sender.sendSystemMessage(Component.literal("Done checking for ram."));
                //PlayerRam ram = sender.getCapability(PlayerRamProvider.PLAYER_RAM).resolve().get();
                //sender.sendSystemMessage(Component.literal("ram: " + ram.getRam() + " / " + ram.getMaxRam()));
            }
        });
        return true;
    }
}
