package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Rivista extends Catalogo{

    @Enumerated(EnumType.STRING)
    Periodicita periodicita;


    public Rivista() {
    }

    public Rivista(String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "ISBN: " + this.getCodiceISBN()
                + " - " + " Titolo: " + this.getTitolo()
                + " - " + " Anno: " + this.getAnnoPubblicazione()
                + " - " + " Pagine: " + this.getNumeroPagine();
    }

    // GETTERS AND SETTERS
    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita( Periodicita periodicita ) {
        this.periodicita = periodicita;
    }
}
