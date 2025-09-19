package cristinamastellaro;

import com.github.javafaker.Faker;
import cristinamastellaro.entities.*;

import java.util.Random;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Random rdm = new Random();
        Faker createFakeNames = new Faker();
        Scanner s = new Scanner(System.in);

        System.out.println("Ciao! Vuoi creare la tua lista giocatori o ti va bene una lista precompilata? (y/n)");
        String firstResponse = s.nextLine();
        Collezione myGames = new Collezione();
        if (!firstResponse.equalsIgnoreCase("y")) {
            while (true) {
                System.out.println("Scrivi quanti giochi vuoi nella tua lista");
                try {
                    int numGames = Integer.parseInt(s.nextLine());
                    myGames = new Collezione(numGames);
                    break;
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        System.out.println(myGames);

        myLoop:
        while (true) {
            System.out.println("Cosa vuoi fare con questa lista di giochi?");
            System.out.println("1 - Aggiungere un gioco");
            System.out.println("2 - Ricerca un gioco attraverso il suo id");
            System.out.println("3 - Ricercare la lista di giochi con un prezzo inferiore a un numero dato");
            System.out.println("4 - Ricercare dei giochi per numero di giocatori (se non inserisci il numero, vedrai tutti i giochi divisi per numero di giocatore)");
            System.out.println("5 - Rimuovere un elemento dando l'id");
            System.out.println("6 - Modificare un elemento esistente dando l'id");
            System.out.println("7 - Vedere le statistiche della collezione");
            System.out.println("Per uscire, digita 'quit'");

            try {
                String choice = s.nextLine();
                if (choice.equalsIgnoreCase("quit")) break myLoop;
                int chosenNum = Integer.parseInt(choice);
                switch (chosenNum) {
                    case 1:
                        while (true) {
                            System.out.println("Per salvare un videogioco, digita 1; per salvare un gioco da tavolo, digita 2");
                            try {
                                int typeOfGame = Integer.parseInt(s.nextLine());
                                switch (typeOfGame) {
                                    case 1:
                                        System.out.println("Titolo:");
                                        String title = s.nextLine();
                                        System.out.println("Anno di pubblicazione:");
                                        int year = s.nextInt();
                                        System.out.println("Prezzo:");
                                        double price = s.nextDouble();
                                        System.out.println("Piattaforma su cui si può giocare:");
                                        String emptyString = s.nextLine();
                                        String platform = s.nextLine();
                                        System.out.println("Quanto dura, in genere, in ore? Il numero dev'essere intero");
                                        int duration = s.nextInt();
                                        System.out.println("A quale genere appartiene?");
                                        System.out.println("Digita un numero tra: ");
                                        System.out.println("1 - RPG");
                                        System.out.println("2 - Azione");
                                        System.out.println("3 - Fantasy");
                                        System.out.println("4 - Trivia");
                                        System.out.println("5 - Platform");
                                        System.out.println("6 - Rhythm");
                                        int genereDaModificare = s.nextInt();
                                        Genere genre = switch (genereDaModificare) {
                                            case 1 -> Genere.RPG;
                                            case 2 -> Genere.Action;
                                            case 3 -> Genere.Fantasy;
                                            case 4 -> Genere.Trivia;
                                            case 5 -> Genere.Platform;
                                            case 6 -> Genere.Rhythm;
                                            default -> Genere.RPG;
                                        };
                                        Gioco game = new Videogioco(title, year, price, platform, duration, genre);
                                        myGames.addGame(game);
                                        break;

                                    case 2:
                                        System.out.println("Titolo:");
                                        String secondTitle = s.nextLine();
                                        System.out.println("Anno di pubblicazione:");
                                        int secondYear = s.nextInt();
                                        System.out.println("Prezzo:");
                                        double secondPrice = s.nextDouble();
                                        System.out.println("Numero di giocatori:");
                                        int numPlayer = s.nextInt();
                                        System.out.println("Quanto dura, in genere, in minuti?");
                                        int secondDuration = s.nextInt();
                                        Gioco secondGame = new GiocoDaTavolo(secondTitle, secondYear, secondPrice, numPlayer, secondDuration);
                                        myGames.addGame(secondGame);
                                        break;
                                    default:
                                        throw new Exception("Numero selezionato non valido");
                                }
                                break;
                            } catch (Exception e) {
                                System.err.println(e.getMessage());
                                System.out.println("Dovrai ridigitare tutto");
                            }
                            break;
                        }
                    case 2:
                    default:
                        System.out.println("Numero selezionato non valido, riprova");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
