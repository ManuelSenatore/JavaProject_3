package models;

import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@NamedQuery(
        name = "findLoan",
        query = "select p from Prestito p where p.utente.id = :numero"
)
public class Prestito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "elemento_prestato_codice_isbn")
    private Catalogo elementoPrestato;

    private LocalDate dataInizioPrestito = LocalDate.now();

    private LocalDate dataRestituizionePrevista = LocalDate.now().plusMonths(1) ;

    private LocalDate dataRestituzioneEffettiva;

    public Prestito() {
    }

    public Prestito(Utente utente, Catalogo elementoPrestato) {
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
    }

    public Catalogo getElementoPrestato() {
        return elementoPrestato;
    }

    public void setElementoPrestato(Catalogo elementoPrestato) {
        this.elementoPrestato = elementoPrestato;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInizioPrestito(LocalDate dataInizioPrestito) {
        return this.dataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }

    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public LocalDate getDataRestituizionePrevista() {
        return dataRestituizionePrevista;
    }

    public void setDataRestituizionePrevista(LocalDate dataRestituizionePrevista) {
        this.dataRestituizionePrevista = dataRestituizionePrevista;
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "id=" + id +
                ", utente=" + utente +
                ", elementoPrestato=" + elementoPrestato +
                ", dataInizioPrestito=" + dataInizioPrestito +
                ", dataRestituizionePrevista=" + dataRestituizionePrevista +
                ", dataRestituzioneEffettiva='" + dataRestituzioneEffettiva + '\'' +
                '}';
    }
}
