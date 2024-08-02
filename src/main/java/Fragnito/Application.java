package Fragnito;

import com.github.javafaker.Faker;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;

public class Application {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        Faker f = new Faker();
        final int[] isbn = {1000};
        Supplier<Integer> isbnGen = () -> {
            isbn[0] = isbn[0] + 1;
            return isbn[0];
        };

        Archivio bookList = new Archivio();

        System.out.println("Benvenuto su GoodReads!");
        System.out.println("\"" + f.harryPotter().quote() + "\"");

        try {
            bookList.logInImport("src/bookList.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Cosa vuoi fare?");

        int input = 100;

        while (input != 0) {
            System.out.println("Digita il numero corrispondente o 0 per uscire.");
            int option = 1;
            System.out.println(option + " - Guarda la tua lista di libri.");
            option++;
            System.out.println(option + " - Aggiungi un libro alla tua lista di libri.");
            option++;
            System.out.println(option + " - Rimuovi un libro dalla tua lista di libri.");
            option++;
            System.out.println(option + " - Ricerca un libro nella tua lista tramite il suo codice ISBN.");
            option++;
            System.out.println(option + " - Filtra i libri nella tua lista tramite l'anno di pubblicazione.");
            option++;
            System.out.println(option + " - Filtra i libri nella tua lista tramite autore.");
            option++;
            System.out.println(option + " - Salva la tua lista attuale in un file esterno.");
            option++;
            System.out.println(option + " - Importa libri nella tua lista tramite file esterno.");
            try {

                input = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Devi inserire un numero valido.");
            }

            switch (input) {
                case 1: {
                    bookList.printList();
                    System.out.println("Ora cosa vuoi fare?");
                    break;
                }
                case 2: {
                    System.out.println("Vuoi aggiungere un libro o una rivista?");
                    System.out.println("Digita 1 per selezionare il libro.");
                    System.out.println("Digita 2 per selezionare la rivista.");
                    try {
                        input = Integer.parseInt(scanner.nextLine());
                        switch (input) {
                            case 1: {
                                System.out.println("Inserisci il titolo del libro.");
                                String title = "";
                                try {
                                    title = scanner.nextLine();
                                } catch (Exception e) {
                                    System.out.println("Dovevi inserire un titolo.");
                                }
                                System.out.println("Inserisci l'anno di pubblicazione.");
                                try {
                                    int year = Integer.parseInt(scanner.nextLine());
                                    Libro libro = new Libro(title, isbnGen.get(), year, rand.nextInt(100, 500), f.funnyName().name(), Genre.randomGenre());
                                    bookList.addBook(libro);
                                    System.out.println("Ora cosa vuoi fare?");
                                } catch (Exception e) {
                                    System.out.println("Dovevi inserire un anno di pubblicazione, riprova!");
                                }
                                break;
                            }
                            case 2: {
                                System.out.println("Inserisci il titolo della rivista.");
                                String title = "";
                                try {
                                    title = scanner.nextLine();
                                } catch (Exception e) {
                                    System.out.println("Dovevi inserire un titolo.");
                                }
                                System.out.println("Inserisci l'anno di pubblicazione.");
                                try {
                                    int year = Integer.parseInt(scanner.nextLine());
                                    Rivista rivista = new Rivista(title, isbnGen.get(), year, rand.nextInt(100, 500), Periodicita.randomPeriodicita());
                                    bookList.addBook(rivista);
                                    System.out.println("Cosa vuoi fare ora?");
                                } catch (Exception e) {
                                    System.out.println("Dovevi inserire un anno di pubblicazione, riprova!");
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Devi inserire un numero valido.");
                    }
                    break;
                }
                case 3: {
                    System.out.println("Inserisci il codice ISBN.");
                    try {
                        int isbnCode = Integer.parseInt(scanner.nextLine());
                        bookList.removeIsbn(isbnCode);
                        System.out.println("Cosa vuoi fare ora?");
                    } catch (NumberFormatException e) {
                        System.out.println("Dovevi inserire un codice ISBN valido, riprova!");
                    }
                    break;
                }
                case 4: {
                    System.out.println("Inserisci il codice ISBN.");
                    try {
                        int isbnCode = Integer.parseInt(scanner.nextLine());
                        bookList.isbnSearch(isbnCode);
                        System.out.println("Cosa vuoi fare ora?");
                    } catch (NumberFormatException e) {
                        System.out.println("Dovevi inserire un codice ISBN valido, riprova!");
                    }
                    break;
                }
                case 5: {
                    System.out.println("Inserisci l'anno di pubblicazione.");
                    try {
                        int year = Integer.parseInt(scanner.nextLine());
                        bookList.yearSearch(year);
                        System.out.println("Cosa vuoi fare ora?");
                    } catch (NumberFormatException e) {
                        System.out.println("Dovevi inserire un anno di pubblicazione valido, riprova!");
                    }
                    break;
                }
                case 6: {
                    System.out.println("Inserisci un autore.");
                    try {
                        String author = scanner.nextLine();
                        bookList.authorSearch(author);
                        System.out.println("Cosa vuoi fare ora?");
                    } catch (Exception e) {
                        System.out.println("Dovevi inserire un autore, riprova!");
                    }
                    break;
                }
                case 7: {
                    bookList.saveListOnDisk();
                    System.out.println("Cosa vuoi fare ora?");
                    break;
                }
                case 8: {
                    try {
                        bookList.readListToApp("src/bookList.txt");
                        System.out.println("Cosa vuoi fare ora?");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case 0: {
                    System.out.println("See you space cowboy...");
                    break;
                }
                default: {
                    System.out.println("Opzione non valida");
                    break;
                }
            }
        }
    }
}
