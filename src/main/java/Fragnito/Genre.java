package Fragnito;

import java.util.Random;

public enum Genre {
    DRAMA, COMEDY, HISTORY, SELF_HELP, FANTASY, SCI_FI, HORROR;

    private static final Random rand = new Random();

    public static Genre randomGenre() {
        Genre[] genres = values();
        return genres[rand.nextInt(genres.length)];
    }
}
