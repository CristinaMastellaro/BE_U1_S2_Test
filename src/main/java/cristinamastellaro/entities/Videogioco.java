package cristinamastellaro.entities;

public class Videogioco extends Gioco {
    private String platform;
    private int duration;
    private Genere genre;

    public Videogioco(String title, int publishedYear, double price, String platform, int duration, Genere genre) {
        super(title, publishedYear, price);
        if (!platform.isEmpty()) this.platform = platform;
        else {
            System.err.println("Piattaforma non valida");
            this.platform = "PS5";
        }
        if (duration > 0) this.duration = duration;
        else {
            System.err.println("Durata non valida");
            this.duration = rdm.nextInt(5, 100);
        }
        this.genre = genre;
    }

    public Videogioco(String title, int publishedYear, double price, String platform, int duration, Genere genre, long id) {
        this(title, publishedYear, price, platform, duration, genre);
        this.id = id;
    }

    // Getters and setters


    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        if (!platform.isEmpty()) this.platform = platform;
        else {
            System.err.println("Piattaforma non valida");
            this.platform = "PS5";
        }
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if (duration > 0) this.duration = duration;
        else {
            System.err.println("Durata non valida");
            this.duration = rdm.nextInt(5, 100);
        }
    }

    public Genere getGenre() {
        return genre;
    }

    public void setGenre(Genere genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Videogioco dal titolo: " + title +
                ", id: " + id +
                ", appartenente al genere " + genre + ", giocabile su: " + platform +
                ", possibile durata: " + duration +
                " ore, pubblicato nel " + publishedYear +
                ", prezzo: " + price +
                "€\n";
    }
}
