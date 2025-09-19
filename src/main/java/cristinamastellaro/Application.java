package cristinamastellaro;

import cristinamastellaro.entities.*;

import java.util.List;
import java.util.Scanner;

public class Application {

    public static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Ciao! Vuoi creare la tua lista giocatori? (y/n)");
        String firstResponse = s.nextLine();
        CollezioneDiGiochi myGames = new CollezioneDiGiochi();
        if (!firstResponse.equalsIgnoreCase("y")) {
            while (true) {
                System.out.println("Scrivi quanti giochi vuoi che vengano aggiunti automaticamente nella tua lista");
                try {
                    String numGamesS = s.nextLine();
                    int numGames = Integer.parseInt(numGamesS);
                    myGames = new CollezioneDiGiochi(numGames);
                    break;
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        } else {
            while (true) {
                System.out.println("Per aggiungere un videogioco digita 1, per aggiungere un gioco da tavolo seleziona 2");
                String firstChoice = s.nextLine();
                try {
                    if (firstChoice.equals("1")) {
                        Gioco game = creaVideogioco(myGames);
                        myGames.addGame(game);
                    } else if (firstChoice.equals("2")) {
                        Gioco game = creaGiocoDaTavolo(myGames);
                        myGames.addGame(game);
                    } else throw new Exception("Numero digitato non valido, riprovare");
                    break;
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        System.out.println(myGames);

        while (true) {
            try {
                System.out.println("Cosa vuoi fare con questa lista di giochi?");
                System.out.println("1 - Aggiungere un gioco");
                System.out.println("2 - Aggiungere giochi casuali");
                System.out.println("3 - Ricercare un gioco attraverso il suo id");
                System.out.println("4 - Ricercare la lista di giochi con un prezzo inferiore a un numero dato");
                System.out.println("5 - Ricercare dei giochi per numero di giocatori (se non inserisci il numero, vedrai tutti i giochi divisi per numero di giocatore)");
                System.out.println("6 - Rimuovere un elemento dando l'id");
                System.out.println("7 - Modificare un elemento esistente dando l'id");
                System.out.println("8 - Vedere le statistiche della collezione");
                System.out.println("9 - Guardare la tua collezione");
                System.out.println("Per uscire, digita 'quit'");

                String choice = s.nextLine();
                if (choice.equalsIgnoreCase("quit")) break;
                int chosenNum = Integer.parseInt(choice);
                switch (chosenNum) {
                    case 1:
                        while (true) {
                            System.out.println("Per salvare un videogioco, digita 1; per salvare un gioco da tavolo, digita 2");
                            try {
                                int typeOfGame = Integer.parseInt(s.nextLine());
                                switch (typeOfGame) {
                                    case 1:
                                        Gioco game = creaVideogioco(myGames);
                                        myGames.addGame(game);
                                        break;

                                    case 2:
                                        Gioco secondGame = creaGiocoDaTavolo(myGames);
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
                        }
                        break;
                    case 2:
                        while (true) {
                            try {
                                System.out.println("Quanti giochi vuoi aggiungere?");
                                String numS = s.nextLine();
                                int num = Integer.parseInt(numS);
                                CollezioneDiGiochi newGames = new CollezioneDiGiochi(num);
                                List<Gioco> newGamesToAdd = newGames.getGames();
                                myGames.addListGame(newGamesToAdd);
                                System.out.println(myGames);
                                break;
                            } catch (Exception e) {
                                System.err.println(e.getMessage());
                            }
                        }
                        break;
                    case 3:
                        while (true) {
                            try {
                                System.out.println("Scrivi l'id del prodotto che vuoi trovare");
                                String emptyS = s.nextLine();
                                int id = Integer.parseInt(emptyS);
                                myGames.searchById(id);
                                break;
                            } catch (Exception e) {
                                System.err.println(e.getMessage());
                            }
                        }
                        break;
                    case 4:
                        while (true) {
                            try {
                                System.out.println("Inserisci il prezzo massimo");
                                String maxPriceS = s.nextLine();
                                int maxPrice = Integer.parseInt(maxPriceS);
                                myGames.searchByPrice(maxPrice);
                                break;
                            } catch (Exception e) {
                                System.err.println(e.getMessage());
                            }
                        }
                        break;
                    case 5:
                        while (true) {
                            try {
                                System.out.println("Inserisci il numero di giocatori");
                                String numPlayersString = s.nextLine();
                                if (numPlayersString.equalsIgnoreCase("")) myGames.searchByNumPlayer();
                                else {
                                    int numPlayers = Integer.parseInt(numPlayersString);
                                    myGames.searchByNumPlayers(numPlayers);
                                }
                                break;
                            } catch (Exception e) {
                                System.err.println(e.getMessage());
                            }
                        }
                        break;
                    case 6:
                        while (true) {
                            try {
                                System.out.println("Indica l'id del gioco da eliminare");
                                String idS = s.nextLine();
                                int id = Integer.parseInt(idS);
                                myGames.removeById(id);
                                break;
                            } catch (Exception e) {
                                System.err.println(e.getMessage());
                            }
                        }
                        break;
                    case 7:
                        while (true) {
                            try {
                                System.out.println("Indica l'id del gioco da modificare");
                                String idS = s.nextLine();
                                int id = Integer.parseInt(idS);
                                Gioco game = myGames.searchById(id);
                                if (game != null) {
                                    System.out.println("Lasciare vuoto se non si vuole cambiare il parametro");
                                    if (game instanceof GiocoDaTavolo) {
                                        System.out.println("Titolo:");
                                        String secondTitle = s.nextLine();
                                        if (secondTitle.trim().isEmpty()) secondTitle = game.getTitle();

                                        System.out.println("Anno di pubblicazione:");
                                        String yearS = s.nextLine();
                                        int secondYear;
                                        if (yearS.trim().isEmpty()) secondYear = game.getPublishedYear();
                                        else secondYear = Integer.parseInt(yearS);

                                        System.out.println("Prezzo:");
                                        String priceS = s.nextLine();
                                        double secondPrice;
                                        if (priceS.trim().isEmpty()) secondPrice = game.getPrice();
                                        secondPrice = Double.parseDouble(priceS);

                                        System.out.println("Numero di giocatori:");
                                        String numPlayerS = s.nextLine();
                                        int secondNumPlayer;
                                        if (numPlayerS.trim().isEmpty())
                                            secondNumPlayer = ((GiocoDaTavolo) game).getNumPlayers();
                                        else secondNumPlayer = Integer.parseInt(numPlayerS);

                                        System.out.println("Quanto dura, in genere, in minuti?");
                                        String secondDurationS = s.nextLine();
                                        int secondDuration;
                                        if (secondDurationS.trim().isEmpty())
                                            secondDuration = ((GiocoDaTavolo) game).getDuration();
                                        else secondDuration = Integer.parseInt(secondDurationS);

                                        Gioco secondGame = new GiocoDaTavolo(secondTitle, secondYear, secondPrice, secondNumPlayer, secondDuration, id);
                                        myGames.updateGameById(id, secondGame);
                                    } else {
                                        System.out.println("Titolo:");
                                        String title = s.nextLine();
                                        if (title.trim().isEmpty()) title = game.getTitle();

                                        System.out.println("Anno di pubblicazione:");
                                        String yearS = s.nextLine();
                                        int year;
                                        if (yearS.trim().isEmpty()) year = game.getPublishedYear();
                                        else year = Integer.parseInt(yearS);

                                        System.out.println("Prezzo:");
                                        String priceS = s.nextLine();
                                        double price;
                                        if (priceS.trim().isEmpty()) price = game.getPrice();
                                        else price = Double.parseDouble(priceS);

                                        System.out.println("Piattaforma su cui si può giocare:");
                                        String platform = s.nextLine();
                                        if (platform.trim().isEmpty()) platform = ((Videogioco) game).getPlatform();

                                        System.out.println("Quanto dura, in genere, in ore? Il numero dev'essere intero");
                                        String durationS = s.nextLine();
                                        int duration;
                                        if (durationS.trim().isEmpty()) duration = ((Videogioco) game).getDuration();
                                        else duration = Integer.parseInt(durationS);

                                        System.out.println("A quale genere appartiene?");
                                        System.out.println("Digita un numero tra: ");
                                        System.out.println("1 - RPG");
                                        System.out.println("2 - Azione");
                                        System.out.println("3 - Fantasy");
                                        System.out.println("4 - Trivia");
                                        System.out.println("5 - Platform");
                                        System.out.println("6 - Rhythm");
                                        String genereDaModificareS = s.nextLine();
                                        int genereDaModificare;
                                        Genere genre;
                                        if (genereDaModificareS.trim().isEmpty())
                                            genre = ((Videogioco) game).getGenre();
                                        else {
                                            genereDaModificare = Integer.parseInt(genereDaModificareS);
                                            genre = switch (genereDaModificare) {
                                                case 1 -> Genere.RPG;
                                                case 2 -> Genere.Action;
                                                case 3 -> Genere.Fantasy;
                                                case 4 -> Genere.Trivia;
                                                case 5 -> Genere.Platform;
                                                case 6 -> Genere.Rhythm;
                                                default -> ((Videogioco) game).getGenre();
                                            };
                                        }
                                        Gioco newGame = new Videogioco(title, year, price, platform, duration, genre, id);
                                        myGames.updateGameById(id, newGame);
                                    }
                                }
                                break;
                            } catch (Exception e) {
                                System.err.println(e.getMessage());
                            }
                        }
                        break;
                    case 8:
                        myGames.collectionsStatistics();
                        break;
                    case 9:
                        System.out.println(myGames);
                        break;
                    default:
                        System.out.println("Numero selezionato non valido, riprova");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        s.close();
    }

    public static Gioco creaVideogioco(CollezioneDiGiochi totalGames) {
        System.out.println("Titolo:");
        String title = s.nextLine();
        System.out.println("Anno di pubblicazione:");
        String yearS = s.nextLine();
        int year = Integer.parseInt(yearS);
        System.out.println("Prezzo:");
        String priceS = s.nextLine();
        double price = Double.parseDouble(priceS);
        System.out.println("Piattaforma su cui si può giocare:");
        String platform = s.nextLine();
        System.out.println("Quanto dura, in genere, in ore? Il numero dev'essere intero");
        String durationS = s.nextLine();
        int duration = Integer.parseInt(durationS);
        System.out.println("A quale genere appartiene?");
        System.out.println("Digita un numero tra: ");
        System.out.println("1 - RPG");
        System.out.println("2 - Azione");
        System.out.println("3 - Fantasy");
        System.out.println("4 - Trivia");
        System.out.println("5 - Platform");
        System.out.println("6 - Rhythm");
        String genereDaModificare = s.nextLine();
        Genere genre = switch (genereDaModificare.trim()) {
            case "1" -> Genere.RPG;
            case "2" -> Genere.Action;
            case "3" -> Genere.Fantasy;
            case "4" -> Genere.Trivia;
            case "5" -> Genere.Platform;
            case "6" -> Genere.Rhythm;
            default -> Genere.RPG;
        };
        totalGames.setNumVideogames(totalGames.getNumVideogames() + 1);
        return new Videogioco(title, year, price, platform, duration, genre);
    }

    public static Gioco creaGiocoDaTavolo(CollezioneDiGiochi totalGames) {
        System.out.println("Titolo:");
        String secondTitle = s.nextLine();
        System.out.println("Anno di pubblicazione:");
        String secondYears = s.nextLine();
        int secondYear = Integer.parseInt(secondYears);
        System.out.println("Prezzo:");
        String secondPriceS = s.nextLine();
        double secondPrice = Double.parseDouble(secondPriceS);
        System.out.println("Numero di giocatori:");
        String numPlayerS = s.next();
        int numPlayer = Integer.parseInt(numPlayerS);
        System.out.println("Quanto dura, in genere, in minuti?");
        String secondDurationS = s.next();
        int secondDuration = Integer.parseInt(secondDurationS);
        totalGames.setNumTableGames(totalGames.getNumTableGames() + 1);
        return new GiocoDaTavolo(secondTitle, secondYear, secondPrice, numPlayer, secondDuration);
    }
}
