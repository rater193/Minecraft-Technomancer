package net.rater193.technomancer.events;


import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.rater193.technomancer.Technomancer;
import net.rater193.technomancer.item.ModItems;
import net.rater193.technomancer.playerdata.ram.PlayerRam;
import net.rater193.technomancer.playerdata.ram.PlayerRamProvider;
import net.rater193.technomancer.villager.ModVillagers;

import java.util.List;

@Mod.EventBusSubscriber(modid = Technomancer.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType()== ModVillagers.MACHINE_MASTER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades  = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.THERMALPASTE.get(), 12);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    stack, 10, 8, 0.02f
            ));
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player player) {
            if(!player.getCapability(PlayerRamProvider.PLAYER_RAM).isPresent()) {
                event.addCapability(new ResourceLocation(Technomancer.MOD_ID, "properties"), new PlayerRamProvider());
            }
        }
    }
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerRamProvider.PLAYER_RAM).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerRamProvider.PLAYER_RAM).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerRam.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {
            event.player.getCapability(PlayerRamProvider.PLAYER_RAM).ifPresent(ram -> {
                if(ram.getRam()<ram.getMaxRam() && ram.lastTick <= 0) {
                    ram.addRam(1);
                    ram.lastTick = 20;
                    event.player.sendSystemMessage(Component.literal("Added 1 ram: " + ram.getRam()));
                }else{
                    ram.lastTick -= 1;
                }
            });
        }
    }
}