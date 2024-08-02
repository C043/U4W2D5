package Fragnito;

import com.github.javafaker.Faker;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class Application {
    public static void main(String[] args) {
        Faker f = new Faker();
        final int[] isbn = {1000};
        Supplier<Integer> isbnGen = () -> {
            isbn[0] = isbn[0]++;
            return isbn[0];
        };


        Archivio bookList = new Archivio();

        Libro cadavereSquisito = new Libro(f.book().title(), isbnGen.get(), 2024, "Ernest Cline", Genre.HORROR);
        Libro readyPlayerOne = new Libro(f.book().title(), isbnGen.get(), 2020, f.harryPotter().character(), Genre.SCI_FI);
        Libro comedyBook = new Libro(f.book().title(), isbnGen.get(), 2020, f.funnyName().name(), Genre.COMEDY);


        bookList.addBook(cadavereSquisito);
        bookList.addBook(readyPlayerOne);
        bookList.addBook(comedyBook);

        bookList.removeIsbn(2000);

        bookList.isbnSearch(1002);

        bookList.yearSearch(2020);
        try {
            bookList.authorSearch("Ernest Cline");
        } catch (NoSuchElementException e) {
            System.out.println("Nessun libro scritto dall'autore cercato è presente nella tua lista.");
        }

/*
        bookList.saveListOnDisk();
*/
        bookList.printList();
        try {
            bookList.readListToApp("src/bookList.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
