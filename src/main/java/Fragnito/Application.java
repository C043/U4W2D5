package Fragnito;

import com.github.javafaker.Faker;

public class Application {
    public static void main(String[] args) {
       /* Supplier<List<Libro>> randomBooks = () -> {
            List<Libro> bookList = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                bookList.add(new Libro(f.book().title(), f.harryPotter().character(), Genre.DRAMA));
            }
            return bookList;
        };*/
        Faker f = new Faker();


        Archivio bookList = new Archivio();

        Libro cadavereSquisito = new Libro(f.book().title(), 100, 2024, f.elderScrolls().firstName() + " " + f.elderScrolls().lastName(), Genre.HORROR);
        Libro readyPlayerOne = new Libro(f.book().title(), 101, 2020, f.harryPotter().character(), Genre.SCI_FI);
        Libro comedyBook = new Libro(f.book().title(), 101, 2020, f.funnyName().name(), Genre.COMEDY);


        bookList.addBook(cadavereSquisito);
        bookList.addBook(readyPlayerOne);
        bookList.addBook(comedyBook);
        bookList.removeIsbn(1000);
        bookList.isbnSearch(100);
        bookList.yearSearch(2020);
    }
}
