package net.rater193.technomancer.utility;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.rater193.technomancer.item.ModItems;

public class WorldUtil {
    public static void SpawnInWorld(Level world, Vec3 location, ItemLike item) {
        new ItemEntity(world,location.x(), location.y(), location.z(), new ItemStack(item)).spawnAtLocation(item);
    }
}
