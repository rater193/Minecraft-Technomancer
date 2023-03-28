package net.rater193.technomancer.playerdata.ram;

import net.minecraft.nbt.CompoundTag;

public class PlayerRam {
    private int ram = 0;
    public int lastTick = 0;
    private final int MIN_RAM = 0;
    private final int MAX_RAM = 10000;

    public int getRam() {
        return ram;
    }

    public int getMaxRam() {
        return MAX_RAM;
    }

    public int getMinRam() {
        return MAX_RAM;
    }

    public PlayerRam addRam(int amount) {
        ram = Math.min(MAX_RAM, ram+amount);
        return this;
    };

    public PlayerRam removeRam(int amount) {
        ram = Math.max(MIN_RAM, ram-amount);
        return this;
    };

    public PlayerRam copyFrom(PlayerRam source) {
        ram = source.getRam();
        return this;
    };

    public PlayerRam saveNBTData(CompoundTag nbt) {
        nbt.putInt("ram", ram);
        return this;
    }

    public void loadNBTData(CompoundTag nbt) {
        ram = nbt.getInt("ram");
    }
}
