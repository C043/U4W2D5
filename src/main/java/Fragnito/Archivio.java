package Fragnito;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Archivio {
    private final List<Leggibile> bookList = new ArrayList<>();

    public void addBook(Leggibile book) {
        this.bookList.add(book);
        System.out.println(book.getTitle() + " aggiunto con successo!");
        System.out.println("La tua lista libri aggiornata:");
        this.bookList.forEach(System.out::println);
    }

    public void removeIsbn(int isbn) {
        Optional<Leggibile> bookToRemove = this.bookList.stream().filter(book -> book.getIsbnCode() == isbn).findFirst();
        if (bookToRemove.isPresent()) {
            this.bookList.remove(bookToRemove.get());
            System.out.println(bookToRemove.get().getTitle() + " rimosso con successo!");
            printList();
        } else if (this.bookList.isEmpty()) {
            System.out.println("La tua lista è vuota!");
        } else System.out.println("Nessun libro con quel codice!");
    }

    public void isbnSearch(int isbn) {
        Optional<Leggibile> bookFound = this.bookList.stream().filter(book -> book.getIsbnCode() == isbn).findFirst();
        if (bookFound.isPresent()) {
            System.out.println("Libro trovato.");
            System.out.println(bookFound.get());
        } else System.out.println("Nessun libro con quel codice!");
    }

    public void yearSearch(int year) {
        Map<Integer, List<Leggibile>> booksFound = this.bookList.stream().filter(book -> book.getYear() == year).collect(Collectors.groupingBy(Leggibile::getYear));
        if (!booksFound.get(year).isEmpty()) {
            System.out.println("Risultati ricerca per anno:");
            System.out.println(year + " - " + booksFound.get(year));
        } else System.out.println("Nessun libro nella tua lista pubblicato quell'anno.");
    }

    public void authorSearch(String author) {
        List<Leggibile> booksFound = this.bookList.stream().filter(book -> {
            if (book instanceof Libro) {
                return Objects.equals(((Libro) book).getAuthor(), author);
            } else return false;
        }).toList();

        if (!booksFound.isEmpty()) {
            System.out.println("Risultati ricerca per autore: " + author);
            booksFound.forEach(System.out::println);
        } else if (this.bookList.isEmpty()) {
            System.out.println("La lista è vuota, aggiungi un libro prima!");
        } else System.out.println("Nessun libro scritto dall'autore cercato nella tua lista.");
    }

    public void saveListOnDisk() {
        StringBuilder textToWrite = new StringBuilder();
        File file = new File("src/bookList.txt");
        this.bookList.forEach(book -> {
            if (book instanceof Libro) {
                textToWrite.append(book.getTitle()).append("@")
                        .append(book.getIsbnCode()).append("@")
                        .append(book.getYear()).append("@")
                        .append(((Libro) book).getAuthor()).append("@")
                        .append(((Libro) book).getGenre()).append("@")
                        .append(book.getNumOfPages()).append("@").append("#");
            } else if (book instanceof Rivista) {
                textToWrite.append(book.getTitle()).append("@")
                        .append(book.getIsbnCode()).append("@")
                        .append(book.getYear()).append("@")
                        .append(((Rivista) book).getPeriodicita()).append("@")
                        .append(book.getNumOfPages()).append("@").append("#");
            }
        });

        try {
            FileUtils.write(file, textToWrite, StandardCharsets.UTF_8);
            System.out.println("Lista libri esportata con successo!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readListToApp(String path) throws IOException {
        File file = new File(path);
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        String[] booksToString = content.split("#");

        for (String book : booksToString) {
            String[] bookParts = book.split("@");
            if (bookParts.length == 5) {
                Rivista rivista = new Rivista(bookParts[0], Integer.parseInt(bookParts[1]), Integer.parseInt(bookParts[2]), Integer.parseInt(bookParts[4]), Periodicita.valueOf(bookParts[3]));
                Leggibile comp = this.bookList.stream().filter(b -> b.getIsbnCode() == rivista.getIsbnCode()).toList().getFirst();
                if (comp.equals(rivista)) {
                    System.out.println(comp.getTitle() + " già presente.");
                } else this.bookList.add(rivista);

            } else if (bookParts.length == 6) {
                Libro libro = new Libro(bookParts[0], Integer.parseInt(bookParts[1]), Integer.parseInt(bookParts[2]), Integer.parseInt(bookParts[5]), bookParts[3], Genre.valueOf(bookParts[4]));
                Leggibile comp = this.bookList.stream().filter(b -> b.getIsbnCode() == libro.getIsbnCode()).toList().getFirst();
                if (comp.equals(libro)) {
                    System.out.println(comp.getTitle() + " già presente.");
                } else this.bookList.add(libro);
            }
        }
        System.out.println("Lista libri importata con successo!");
        this.bookList.forEach(System.out::println);
    }

    public void printList() {
        if (this.bookList.isEmpty()) {
            System.out.println("Nessun libro da mostrare, aggiungine uno prima!");
        } else {
            System.out.println("Ecco la tua lista aggiornata:");
            this.bookList.forEach(System.out::println);
        }
    }
}
