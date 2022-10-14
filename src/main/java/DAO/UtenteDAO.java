package DAO;

import models.Catalogo;
import models.Utente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UtenteDAO {
    private static final Logger logger = LoggerFactory.getLogger(CatalogoDAO.class);

    public void save( Utente object) {
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

    public void refresh(Utente object) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {

            em.refresh(object);

        } finally {
            em.close();
        }

    }

    public Utente getById(Long id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {

            return em.find(Utente.class, id);

        } finally {
            em.close();
        }

    }

    public void delete(Utente object) {
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
}
