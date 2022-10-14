import DAO.CatalogoDAO;
import DAO.PrestitoDAO;
import DAO.UtenteDAO;
import models.*;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        UtenteDAO utentedao = new UtenteDAO();
        PrestitoDAO prestitodao = new PrestitoDAO();
        CatalogoDAO catalogodao = new CatalogoDAO();

        /*
        saveLibro("Thor", "Marvel", Genere.FANTASY, 2000, 120);
        saveRivista("Cucina", 2010, 110, Periodicita.MENSILE);
        saveUtente("Antonio", "Rossi", "10/10/80");
        Utente utente = utentedao.getById(1L);
        Catalogo catalogo = catalogodao.getById(5L);
        savePrestito(utente, catalogo);
         */
        catalogodao.getCatalogoPerAnno(2010);
        prestitodao.getPrestitoByUserId(1L);
        catalogodao.getCatalogoPerAutore("marvel");
        System.out.println("--------------------");
        catalogodao.getCatalogoPerTitolo("a");
    }

    public static void saveLibro(String titolo, String autore, Genere genere, int anno, int numeroPagine){
        Libro lib = new Libro();
        lib.setAutore(autore);
        lib.setGenere(genere);
        lib.setTitolo(titolo);
        lib.setAnnoPubblicazione(anno);
        lib.setNumeroPagine(numeroPagine);

        CatalogoDAO dao = new CatalogoDAO();
        dao.save(lib);

    }

    public static void saveRivista(String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita){
        Rivista riv = new Rivista();
        riv.setTitolo(titolo);
        riv.setNumeroPagine(numeroPagine);
        riv.setAnnoPubblicazione(annoPubblicazione);
        riv.setPeriodicita(periodicita);

        CatalogoDAO dao = new CatalogoDAO();
        dao.save(riv);

    }

    public static void saveUtente(String nome, String cognome, String dataDiNascita){
        Utente ut = new Utente();
        ut.setNome(nome);
        ut.setCognome(cognome);
        ut.setDataDiNascita(dataDiNascita);

        UtenteDAO dao = new UtenteDAO();
        dao.save(ut);

    }

    public static void savePrestito(Utente utente, Catalogo elementoPrestato){
        Prestito pre = new Prestito();
        pre.setUtente(utente);
        pre.setElementoPrestato(elementoPrestato);

        PrestitoDAO dao = new PrestitoDAO();
        dao.save(pre);

    }
}
