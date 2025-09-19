package cristinamastellaro.entities;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

public class CollezioneDiGiochi {
    public Random rdm = new Random();
    public Faker createFakeNames = new Faker();
    private List<Gioco> games = new ArrayList<>();
    private int numVideogames = 0;
    private int numTableGames = 0;

    public CollezioneDiGiochi() {
        games = new ArrayList<>();
    }

    public CollezioneDiGiochi(int n) {
        List<String> platforms = Arrays.asList("PS5", "Switch", "XBOX", "PC", "Wii", "NintendoDS", "Nintendo3DS");
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                games.add(new Videogioco(
                        createFakeNames.book().title(),
                        rdm.nextInt(1980, 2025),
                        Math.floor(rdm.nextDouble(5, 300) * 100) / 100,
                        platforms.get(rdm.nextInt(7)),
                        rdm.nextInt(1, 100),
                        Genere.randomGenre()
                ));
                numVideogames++;
            } else {
                games.add(new GiocoDaTavolo(
                        createFakeNames.book().title(),
                        rdm.nextInt(1980, 2025),
                        Math.floor(rdm.nextDouble(5, 300) * 100) / 100,
                        rdm.nextInt(2, 11),
                        rdm.nextInt(5, 180)
                ));
                numTableGames++;
            }
        }
    }

    // Getters and setters
    public List<Gioco> getGames() {
        return games;
    }

    public void addGame(Gioco game) {
        if (games.stream().anyMatch(alreadyPresentGame -> alreadyPresentGame.id == game.id)) {
            System.err.println("Non è possibile aggiungere il gioco con id " + game.id + ", perché nella lista è già presente un altro con lo stesso id\n");
        } else {
            games.add(game);
            System.out.println(game.getTitle() + " aggiunto alla lista!\n");
        }
    }

    public void addListGame(List<Gioco> list) {
        for (Gioco gioco : list) {
            addGame(gioco);
        }
    }

    // Altri metodi
    public Gioco searchById(int id) {
        // Dato che dovrebbe esserci un unico gioco con quell'id, andrà benissimo prendere il primo elemento della lista
        try {
            Gioco searchedGame = games.stream().filter(game -> game.getId() == id).toList().getFirst();
            System.out.println("Il gioco cercato è: " + searchedGame + "\n");
            return searchedGame;
        } catch (Exception e) {
            System.err.println("Nessuno gioco corrisponde all'id fornito\n");
            return null;
        }
    }

    public List<Gioco> searchByPrice(double maxPrice) {
        List<Gioco> searchedGames = games.stream().filter(game -> game.getPrice() < maxPrice).toList();
        if (!searchedGames.isEmpty()) {
            System.out.println("Ecco i giochi con il prezzo inferiore a " + maxPrice + "€");
            System.out.println(searchedGames + "\n");
            return searchedGames;
        } else {
            System.out.println("Nessun gioco costa meno di " + maxPrice + "€\n");
            return null;
        }
    }

    public Map<Integer, List<Gioco>> searchByNumPlayer() {
        try {
            if (games.isEmpty()) throw new Exception("Non ci sono giochi nella tua collezione");
            Map<Integer, List<Gioco>> gameMap = games.stream().filter(game -> game instanceof GiocoDaTavolo).collect(Collectors.groupingBy(game -> ((GiocoDaTavolo) game).getNumPlayers()));
            System.out.println("Ecco la lista di giochi diviso in base al numero di giocatori");
            System.out.println(gameMap + "\n");
            return gameMap;
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\n");
            return null;
        }
    }

    public List<Gioco> searchByNumPlayers(int howManyPlayers) {
        try {
            if (howManyPlayers < 2 || howManyPlayers > 10)
                throw new Exception("Numero di giocatori inserito non valido");
            List<Gioco> searchedGames = games.stream().filter(game -> game instanceof GiocoDaTavolo).filter(game -> ((GiocoDaTavolo) game).getNumPlayers() == howManyPlayers).toList();
            if (!searchedGames.isEmpty()) {
                System.out.println("Ecco i giochi con " + howManyPlayers + " giocatori:");
                searchedGames.forEach(System.out::println);
                System.out.println("\n");
                return searchedGames;
            } else {
                System.out.println("Nessun gioco accetta " + howManyPlayers + " giocatori\n");
                return null;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\n");
            return null;
        }
    }

    public void removeById(int id) {
        if (games.removeIf(game -> game.getId() == id)) System.out.println("Gioco cancellato!\n");
        else System.out.println("Gioco non trovato\n");
    }

    public void updateGameById(int id, Gioco updatedGame) {
        Gioco searchedGame = searchById(id);
        try {
            int index = games.indexOf(searchedGame);
            games.set(index, updatedGame);
            System.out.println("Gioco aggiornato!\n");
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\n");
        }
    }

    public Gioco searchMostExpensiveGame() {
        List<Gioco> searchedGames = games.stream().sorted(Comparator.comparingDouble(Gioco::getPrice).reversed()).limit(1).toList();
        if (!searchedGames.isEmpty()) return searchedGames.getFirst();
        else {
            System.out.println("La lista è vuota");
            return null;
        }
    }

    public double averageSpent() {
        try {
            if (games.isEmpty()) throw new Exception("La collezione di giochi è vuota");
            return games.stream().collect(Collectors.averagingDouble(Gioco::getPrice));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    public void collectionsStatistics() {
        System.out.println("Nella tua collezione di giochi hai un totale di " + (numTableGames + numVideogames) + " giochi, di cui " + numTableGames + " sono da tavolo e " + numVideogames + " sono videogiochi.");
        System.out.println("Il gioco con il prezzo più alto è: " + searchMostExpensiveGame().getTitle() + ", con un prezzo di " + searchMostExpensiveGame().getPrice() + "€");
        System.out.println("La media di soldi spesi in giochi è di " + Math.floor(averageSpent() * 100) / 100 + "€\n");
    }

    @Override
    public String toString() {
        List<String> myGames = new ArrayList<>();
        for (Gioco game : games) {
            myGames.add(game.toString());
        }
        return "La tua collezione di giochi include:\n" + String.join("", myGames);
    }
}
