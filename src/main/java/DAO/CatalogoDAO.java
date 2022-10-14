package DAO;

import models.Catalogo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class CatalogoDAO {
    private static final Logger logger = LoggerFactory.getLogger(CatalogoDAO.class);

    public void save( Catalogo object) {
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

    public void refresh(Catalogo object) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {

            em.refresh(object);

        } finally {
            em.close();
        }

    }

    public Catalogo getById(Long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {

            return em.find(Catalogo.class, id);

        } finally {
            em.close();
        }

    }

    public void delete(Catalogo object) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            em.remove(object);

            transaction.commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            logger.error("Error deleting object: " + object.getClass().getSimpleName(), ex);
            throw ex;

        } finally {
            em.close();
        }

    }

    public void getCatalogoPerAnno(int anno){
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try{
            Query q = em.createQuery(
                    "SELECT c FROM Catalogo c WHERE c.annoPubblicazione = :anno"
            );
            q.setParameter("anno", anno);
            List<Catalogo> r = q.getResultList();

            if(r.size() != 0){
                for (Catalogo c : r
                ) {
                    System.out.println(c);
                }
            } else {
                System.out.println("Nessun elemento trovato!");
            }
        } finally {
            em.close();
        }
    }

    public void getCatalogoPerAutore(String autore){
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try{
            Query q = em.createQuery(
                    "SELECT c FROM Catalogo c WHERE UPPER(c.autore) = UPPER(:autore)"
            );
            q.setParameter("autore", autore);
            List<Catalogo> r = q.getResultList();

            if(r.size() != 0){
                for (Catalogo c : r
                ) {
                    System.out.println(c);
                }
            } else {
                System.out.println("Nessun elemento trovato!");
            }
        } finally {
            em.close();
        }
    }

    public void getCatalogoPerTitolo(String titolo){
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try{
            Query q = em.createQuery(
                    "SELECT c FROM Catalogo c WHERE UPPER(c.titolo) LIKE UPPER(:titolo)"
            );
            q.setParameter("titolo", "%" + titolo + "%");
            List<Catalogo> r = q.getResultList();

            if(r.size() != 0){
                for (Catalogo c : r
                ) {
                    System.out.println(c);
                }
            } else {
                System.out.println("Nessun elemento trovato!");
            }

        } finally {
            em.close();
        }
    }

}