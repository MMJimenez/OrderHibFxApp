package com.example.orderhibfx.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class ObjectDBUtil {
    private final static EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("db/order.odb");
//            em = emf.createEntityManager();
        } catch (HibernateException ex) {
            throw  new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEMF() {
        return emf;
    }

}
