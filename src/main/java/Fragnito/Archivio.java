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
            System.out.println("Ecco la lista aggiornata:");
            this.bookList.forEach(System.out::println);
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

    public void authorSearch(String author) throws RuntimeException {
        List<Leggibile> booksFound = this.bookList.stream().filter(book -> {
            if (book instanceof Libro) {
                return Objects.equals(((Libro) book).getAuthor(), author);
            } else return false;
        }).toList();

        if (booksFound.getFirst() != null) {
            System.out.println("Risultati ricerca per autore: " + author);
            booksFound.forEach(System.out::println);
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
