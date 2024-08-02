package Fragnito;

import java.util.Objects;

public abstract class Leggibile {
    protected int isbnCode;
    protected String title;
    protected int year;
    protected int numOfPages;

    public Leggibile(String title, int isbnCode, int year, int numOfPages) {
        this.isbnCode = isbnCode;
        this.title = title;
        this.year = year;
        this.numOfPages = numOfPages;
    }

    public int getIsbnCode() {
        return isbnCode;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Leggibile leggibile = (Leggibile) o;
        return isbnCode == leggibile.isbnCode && year == leggibile.year && numOfPages == leggibile.numOfPages && Objects.equals(title, leggibile.title);
    }
}
