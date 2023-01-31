package com.example.orderhibfx.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ObjectDBUtil {
    protected final static EntityManagerFactory emf;
    private final static EntityManager em;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("db/order.odb");
            em = emf.createEntityManager();
        } catch (HibernateException ex) {
            throw  new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager getEntityManager() {
        return em;
    }

    public static void closeEmf() {
        try {
            em.close();
        } catch (Exception ex) {

        } finally {
            emf.close();
        }
    }

}
