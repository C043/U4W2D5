package Fragnito;

import java.util.Random;

public enum Periodicita {
    MENSILE, SETTIMANALE, SEMESTRALE, ANNUALE;

    private static Random rand = new Random();

    public static Periodicita randomPeriodicita() {
        Periodicita[] periodicitas = values();
        return periodicitas[rand.nextInt(periodicitas.length)];
    }
}
