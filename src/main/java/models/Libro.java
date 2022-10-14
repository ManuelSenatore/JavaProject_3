package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Libro extends Catalogo{

    String autore;

    @Enumerated(EnumType.STRING)
    Genere genere;

    public Libro() {
    }

    public Libro(String titolo, int annoPubblicazione, int numeroPagine, String autore, Genere genere) {
        super(titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String toString() {
        return "ISBN: " + this.getCodiceISBN()
                + " - " + " Titolo: " + this.getTitolo()
                + " - " + " Anno: " + this.getAnnoPubblicazione()
                + " - " + " Autore: " + this.getAutore()
                + " - " + " Pagine: " + this.getNumeroPagine();
    }
    // GETTER & SETTER
    public String getAutore() {
        return autore;
    }

    public void setAutore( String autore ) {
        this.autore = autore;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere( Genere genere ) {
        this.genere = genere;
    }
}
