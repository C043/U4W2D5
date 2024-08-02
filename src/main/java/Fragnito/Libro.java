package Fragnito;

public class Libro extends Leggibile {
    private final String author;
    private final Genre genre;

    public Libro(String title, int isbnCode, int year, int numOfPages, String author, Genre genre) {
        super(title, isbnCode, year, numOfPages);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", isbnCode=" + isbnCode +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", numOfPages=" + numOfPages +
                '}';
    }
}
