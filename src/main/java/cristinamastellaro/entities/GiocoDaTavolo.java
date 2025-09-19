package cristinamastellaro.entities;

public class GiocoDaTavolo extends Gioco {
    private int numPlayers;
    private int duration;

    public GiocoDaTavolo(String title, int publishedYear, double price, int numPlayers, int duration) {
        super(title, publishedYear, price);
        if (numPlayers >= 2 && numPlayers <= 10) this.numPlayers = numPlayers;
        else {
            System.err.println("Numero di giocatori non valido");
            this.numPlayers = rdm.nextInt(2, 11);
        }
        if (duration > 0) this.duration = duration;
        else {
            System.err.println("Durata non valida!");
            this.duration = rdm.nextInt(5, 180);
        }
    }

    public GiocoDaTavolo(String title, int publishedYear, double price, int numPlayers, int duration, long id) {
        this(title, publishedYear, price, numPlayers, duration);
        this.id = id;
    }
    // Getters and setters

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        try {
            if (numPlayers >= 2 && numPlayers <= 10) this.numPlayers = numPlayers;
            else {
                throw new Exception("Numero di giocatori non valido");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Scrivi un numero accettabile di giocatori");
            setNumPlayers(scanner.nextInt());
        }
        ;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        try {
            if (duration > 0) this.duration = duration;
            else {
                throw new Exception("Durata non valido");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Scrivi un numero accettabile per la durata di una partita");
            setDuration(scanner.nextInt());
        }
    }

    @Override
    public String toString() {
        return "Gioco da tavolo dal titolo: " + title +
                ", id: " + id +
                ", numero di giocatori: " + numPlayers +
                ", possibile durata: " + duration +
                " min, pubblicato nel " + publishedYear +
                ", prezzo: " + price +
                "€\n";
    }
}
