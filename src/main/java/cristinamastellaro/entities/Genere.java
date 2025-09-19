package cristinamastellaro.entities;

import java.util.Random;

public enum Genere {
    RPG, Action, Fantasy, Trivia, Platform, Rhythm;

    private static final Random rdm = new Random();

    public static Genere randomGenre() {
        Genere[] genres = values();
        return genres[rdm.nextInt(genres.length)];
    }
}
