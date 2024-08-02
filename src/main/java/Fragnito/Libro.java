package Fragnito;

public class Libro extends Leggibile {
    private final String author;
    private final String genre;

    public Libro(String title, String author, String genre) {
        super(title);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
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
