package DAO;

import models.Catalogo;
import models.Prestito;
import models.Utente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestitoDAO {
    private static final Logger logger = LoggerFactory.getLogger(CatalogoDAO.class);

    public void save( Prestito object) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            em.persist(object);

            transaction.commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();

            logger.error("Error saving object: " + object.getClass().getSimpleName(), ex);
            throw ex;

        } finally {
            em.close();
        }

    }

    public void refresh(Prestito object) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {

            em.refresh(object);

        } finally {
            em.close();
        }

    }

    public Prestito getById(Long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {

            return em.find(Prestito.class, id);

        } finally {
            em.close();
        }

    }

    public void delete(Prestito object) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            em.remove(em.contains(object) ? object : em.merge(object));

            transaction.commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            logger.error("Error deleting object: " + object.getClass().getSimpleName(), ex);
            throw ex;

        } finally {
            em.close();
        }
    }

    public void getPrestitoByUserId(long userId ) {

        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {

            Query query = em.createNamedQuery( "findLoan" );

            query.setParameter( "numero", userId );
            List<Prestito> r = query.getResultList();

            if(r.size() != 0){
                for (Prestito p : r
                ) {
                    System.out.println(p);
                }
            } else {
                System.out.println("Nessun elemento trovato!");
            }

        } finally {
            em.close();
        }
    }

    public List<Prestito> getScadutiNonRestituiti() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query query = em.createQuery( "select p from Prestito p" );

            List<Prestito> result = query.getResultList();
            List<Prestito> elaborate = new ArrayList<Prestito>();

            for( Prestito p : result ) {
                if( p.getDataRestituizionePrevista().compareTo( LocalDate.now() ) < 0 && p.getDataRestituzioneEffettiva() == null ) {
                    elaborate.add( p );
                }
            }
            return elaborate;
        } finally {
            em.close();
        }
    }

    public static void closePrestito(Long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try {

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            Query q = em.createQuery("UPDATE Prestito p SET dataRestituzioneEffettiva = :dataRestituzione WHERE p.id = :id");

            q.setParameter("dataRestituzione", LocalDate.now());

            q.setParameter("id", id);

            q.executeUpdate();

            transaction.commit();

        } catch (Exception ex) {
            em.getTransaction().rollback();

            logger.error("Error saving object ", ex);
            throw ex;

        } finally {
            em.close();
        }
    }
}
