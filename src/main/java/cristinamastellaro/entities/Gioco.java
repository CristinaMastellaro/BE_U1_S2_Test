package cristinamastellaro.entities;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public abstract class Gioco {
    protected static int numGamesCreated = 0;
    public Scanner scanner = new Scanner(System.in);
    public Random rdm = new Random();
    public Faker createFakeNames = new Faker();
    protected long id;
    protected String title;
    protected int publishedYear;
    protected double price;

    public Gioco(String title, int publishedYear, double price) {
        numGamesCreated++;
        this.id = 1000000000L + numGamesCreated;
        try {
            if (!title.isEmpty()) this.title = title;
            else {
                System.err.println("Titolo non valido!");
                this.title = createFakeNames.name().title();
            }
            if (publishedYear > 0 && publishedYear < LocalDate.now().getYear()) this.publishedYear = publishedYear;
            else {
                System.err.println("Anno non valido");
                this.publishedYear = rdm.nextInt(1800, LocalDate.now().getYear());
            }
            ;
            if (price > 0) this.price = price;
            else {
                System.err.println("Prezzo non valido");
                this.price = rdm.nextDouble(5, 200);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // Getters and setters

    public static int getNumGamesCreated() {
        return numGamesCreated;
    }

    public static void setNumGamesCreated(int numGamesCreated) {
        Gioco.numGamesCreated = numGamesCreated;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.isEmpty()) this.title = title;
        else {
            System.err.println("Titolo non valido!");
            this.title = createFakeNames.name().title();
        }
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        if (publishedYear > 0 && publishedYear < LocalDate.now().getYear()) this.publishedYear = publishedYear;
        else {
            System.err.println("Anno non valido");
            this.publishedYear = rdm.nextInt(1800, 2025);
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0) this.price = price;
        else {
            System.err.println("Prezzo non valido");
            this.price = rdm.nextDouble(5, 200);
        }
    }
}

