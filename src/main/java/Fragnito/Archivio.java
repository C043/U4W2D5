package Fragnito;

import java.util.ArrayList;
import java.util.List;

public class Archivio {
    private final List<Leggibile> bookList = new ArrayList<>();

    public void addBook(Leggibile book) {
        this.bookList.add(book);
        System.out.println(book.getTitle() + " aggiunto con successo!");
        System.out.println("La tua lista libri aggiornata:");
        this.bookList.forEach(System.out::println);
    }

    
}
