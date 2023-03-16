package net.rater193.technomancer.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.rater193.technomancer.item.ModItems;

public class ItemThermalPasteTube extends Item {

    public ItemThermalPasteTube(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && hand==InteractionHand.MAIN_HAND) {
            //Spits out thermal paste, swaps this item with an empty tube

            //outputNumber(player);
            player.setItemInHand(hand, new ItemStack(ModItems.THERMALPASTE_TUBE.get()));
            Vec3 pos = player.getEyePosition().add(player.getLookAngle().multiply(new Vec3(1d,1d,1d)));

            new ItemEntity(level,pos.x(), pos.y(), pos.z(), new ItemStack(ModItems.THERMALPASTE.get())).spawnAtLocation(ModItems.THERMALPASTE.get());
        }

        return super.use(level, player, hand);
    }

    private void outputNumber(Player player) {
        player.sendSystemMessage(Component.literal("Your number is " + getRandomNumber()));
    }

    private int getRandomNumber() {
        return RandomSource.createNewThreadLocalInstance().nextInt(10);
    }
}
