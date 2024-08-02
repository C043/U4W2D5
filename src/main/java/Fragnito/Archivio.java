package Fragnito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
}
