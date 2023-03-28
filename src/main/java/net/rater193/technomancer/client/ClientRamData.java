package net.rater193.technomancer.client;

public class ClientRamData {
    private static int ram;
    //private static int ramMax;

    public static void set(int newRam) {
        ram = newRam;
        //ramMax = maxRam;
    }

    public static int getRam() {
        return ram;
    }

    public static int getMaxRam() {
        return 1000;
    }
}
