package net.rater193.technomancer.playerdata.ram;

import net.minecraft.nbt.CompoundTag;

public class PlayerRam {
    private int ram = 0;
    private int ramFragmented = 0;
    private int ramMax = 2048;
    public int lastTick = 0;
    public int lastDefragTick = 0;
    private final int MIN_RAM = 0;

    public int getRam() {
        return ram;
    }

    public int getFragmentedRam() {
        return ramFragmented;
    }

    public int getMaxRam() {
        return ramMax;
    }

    public int getMinRam() {
        return ramMax;
    }

    public PlayerRam addRam(int amount) {
        ram = Math.min(ramMax, ram+amount);
        return this;
    };

    public PlayerRam removeRam(int amount) {
        ram = Math.max(MIN_RAM, ram-amount);
        return this;
    };

    public PlayerRam addFragmentedRam(int amount) {
        ramFragmented += amount;
        return this;
    }

    public PlayerRam removeFragmentedRam(int amount) {
        ramFragmented = Math.max(0, ramFragmented-amount);
        return this;
    };

    public PlayerRam copyFrom(PlayerRam source) {
        ram = source.getRam();
        ramFragmented = source.getFragmentedRam();
        ramMax = source.getMaxRam();
        return this;
    };

    public PlayerRam saveNBTData(CompoundTag nbt) {
        nbt.putInt("ram", ram | 0);
        nbt.putInt("ramFragmented", ramFragmented | 0);
        nbt.putInt("ramMax", ramMax | 2048);
        return this;
    }

    public void loadNBTData(CompoundTag nbt) {
        ram = nbt.getInt("ram") | 0;
        ramFragmented = nbt.getInt("ramFragmented") | 0;
        ramMax = nbt.getInt("ramMax") | 2048;
    }
}
