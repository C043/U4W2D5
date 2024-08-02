package Fragnito;

public class Rivista extends Leggibile {
    private final Periodicita periodicita;

    public Rivista(String title, Periodicita periodicita) {
        super(title);
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
