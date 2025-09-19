package cristinamastellaro.entities;

public abstract class Collezione {
    protected final long id;
    protected String title;
    protected int publishedYear;
    protected double price;
    protected static int numGamesCreated = 0;

    public Collezione(String title, int publishedYear, double price) {
        numGamesCreated++;
        this.id = 1000000000L + numGamesCreated;
        try {
            this.title = title;
            if (publishedYear > 0) this.publishedYear = publishedYear;
            else System.err.println("Anno non valido");
            if (price > 0) this.price = price;
            else throw new Exception("Prezzo non valido");
        }
    }

    // Getters and setters

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static int getNumGamesCreated() {
        return numGamesCreated;
    }

    public static void setNumGamesCreated(int numGamesCreated) {
        Collezione.numGamesCreated = numGamesCreated;
    }
}

