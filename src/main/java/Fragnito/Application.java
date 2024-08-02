package Fragnito;

public class Application {
    public static void main(String[] args) {
       /* Supplier<List<Libro>> randomBooks = () -> {
            Faker f = new Faker();
            List<Libro> bookList = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                bookList.add(new Libro(f.book().title(), f.harryPotter().character(), Genre.DRAMA));
            }
            return bookList;
        };*/


        Archivio bookList = new Archivio();

        Libro cadavereSquisito = new Libro("Cadavere Squisito", 100, "Augustina Bazterrica", Genre.HORROR);
        Libro readyPlayerOne = new Libro("Ready PLayer One", 101, "Ernest Cline", Genre.SCI_FI);

        bookList.addBook(cadavereSquisito);
        bookList.addBook(readyPlayerOne);
        bookList.removeIsbn(1000);
        bookList.isbnSearch(100);
    }
}
