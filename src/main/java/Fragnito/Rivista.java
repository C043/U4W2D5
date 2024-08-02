package Fragnito;

public class Rivista extends Leggibile {
    private final Periodicita periodicita;

    public Rivista(String title, int isbnCode, int year, Periodicita periodicita) {
        super(title, isbnCode, year);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "periodicita=" + periodicita +
                ", isbnCode=" + isbnCode +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", numOfPages=" + numOfPages +
                '}';
    }
}
