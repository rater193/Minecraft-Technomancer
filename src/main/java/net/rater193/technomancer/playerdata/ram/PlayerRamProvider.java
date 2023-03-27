package net.rater193.technomancer.playerdata.ram;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerRamProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerRam> PLAYER_RAM = CapabilityManager.get(new CapabilityToken<PlayerRam>() { });

    private PlayerRam ram = null;
    private final LazyOptional<PlayerRam> optional = LazyOptional.of(this::createPlayerRam);

    private PlayerRam createPlayerRam() {
        if(ram==null) {
            ram = new PlayerRam();
        }

        return ram;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap==PLAYER_RAM) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerRam().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerRam().loadNBTData(nbt);
    }
}
