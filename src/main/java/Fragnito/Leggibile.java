package Fragnito;

import java.util.Random;

public abstract class Leggibile {
    protected int isbnCode;
    protected String title;
    protected int year;
    protected int numOfPages;

    public Leggibile(String title) {
        Random rand = new Random();
        this.isbnCode = rand.nextInt(1000, 10000);
        this.title = title;
        this.year = rand.nextInt(1990, 2024);
        this.numOfPages = rand.nextInt(100, 1000);
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
