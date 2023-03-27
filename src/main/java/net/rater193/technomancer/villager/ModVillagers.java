package net.rater193.technomancer.villager;

import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.datafix.fixes.VillagerFollowRangeFix;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rater193.technomancer.Technomancer;
import net.rater193.technomancer.block.ModBlocks;

import java.lang.reflect.InvocationTargetException;
import java.rmi.registry.Registry;

public class ModVillagers {

    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, Technomancer.MOD_ID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, Technomancer.MOD_ID);

    public static RegistryObject<PoiType> THERMAL_MACHINE_POI = POI_TYPES.register("thermal_machine_poi",
            ()-> new PoiType(ImmutableSet.copyOf(ModBlocks.MACHINE_BLOCK.get().getStateDefinition().getPossibleStates()),
                    1,1));

    public static RegistryObject<VillagerProfession> MACHINE_MASTER = VILLAGER_PROFESSIONS.register("machine_master",
            () -> new VillagerProfession("machine_master",
                    x->x.get() == THERMAL_MACHINE_POI.get(),
                    x->x.get() == THERMAL_MACHINE_POI.get(),
                    ImmutableSet.of(),
                    ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_ARMORER
                    ));

    public static void registerPOIs() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates", PoiType.class)
                    .invoke(null, THERMAL_MACHINE_POI.get());
        }catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
