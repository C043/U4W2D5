package Fragnito;

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
}
