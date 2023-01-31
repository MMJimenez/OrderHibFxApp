package com.example.orderhibfx.dao.objectdb;

import com.example.orderhibfx.models.Request;
import com.example.orderhibfx.utils.DAO;
import com.example.orderhibfx.utils.HibernateUtil;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import java.util.ArrayList;

import static com.example.orderhibfx.utils.ObjectDBUtil.getEntityManager;

public class RequestDAOObjectDB implements DAO<Request> {

    @Override
    public void save(Request request) {
        try {
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            em.persist(request);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void update(Request request) {

    }

    @Override
    public Request get(Integer id) {
        return null;
    }

    @Override
    public void delete(Request request) {

    }

    @Override
    public ArrayList<Request> getAll() {
        return null;
    }

    public ArrayList<Request> getAllByClient() {
        return null;
    }

    public ArrayList<Request> getAllByDate() {
        return null;
    }

    public ArrayList<Request> getAllNotDelivered() {
        return null;
    }

    public ArrayList<Request> getAllDelivered() {
        return null;
    }

    public ArrayList<Request> getAllToday() {
        return null;
    }

    public ArrayList<Request> getAllLastWeek() {
        return null;
    }

    public ArrayList<Request> getAllLastMonth() {
        return null;
    }

    public ArrayList<Request> getAllLastYear() {
        return null;
    }

    public ArrayList<String> getAllClients() {
        return null;
    }
}

