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
import net.rater193.technomancer.utility.WorldUtil;

public class ItemThermalPasteTube extends ItemTooltipHelper {

    public ItemThermalPasteTube(Properties properties) {
        super(properties, "This item lets you cool down \nelectronics, keeping them \nfrom overheating.");
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && hand==InteractionHand.MAIN_HAND) {
            //Spits out thermal paste, swaps this item with an empty tube
            //outputNumber(player);
            if(!player.isCreative()) {
                //Only remove the item from the players hand if they are not in creative
                player.setItemInHand(hand, new ItemStack(ModItems.THERMALPASTE_TUBE.get()));
            }

            //Spawning thermalpaste in the world
            Vec3 pos = player.getEyePosition().add(player.getLookAngle().multiply(
                    new Vec3(1d,1d,1d)
            ));

            //Cleaned up the reference code, and moved it to a custom clas to keep the clutter away from code
            WorldUtil.SpawnInWorld(level, pos, ModItems.THERMALPASTE.get());
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
