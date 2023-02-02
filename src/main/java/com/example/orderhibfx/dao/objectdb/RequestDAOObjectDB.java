package com.example.orderhibfx.dao.objectdb;

import com.example.orderhibfx.models.Request;
import com.example.orderhibfx.utils.DAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.orderhibfx.utils.ObjectDBUtil.getEMF;

public class RequestDAOObjectDB implements DAO<Request> {

    @Override
    public void save(Request request) {
        try {
            EntityManager em = getEMF().createEntityManager();
            em.getTransaction().begin();
            em.persist(request);
            em.getTransaction().commit();
            em.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void update(Request request) {
        try {
            EntityManager em = getEMF().createEntityManager();
            Request tempRequest = em.find(Request.class, request.getId());
            em.getTransaction().begin();
            tempRequest.setDate(request.getDate());
            tempRequest.setClient(request.getClient());
            tempRequest.setDelivered(request.getDelivered());
            tempRequest.setProduct(request.getProduct());
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Request get(Integer id) {
        Request requestResult = new Request();
        try {
            EntityManager em = getEMF().createEntityManager();
            requestResult = em.find(Request.class, id);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return requestResult;
    }

    @Override
    public void delete(Request request) {
        try {
            EntityManager em = getEMF().createEntityManager();
            Request tempRequest = em.find(Request.class, request.getId());
            em.getTransaction().begin();
            em.remove(tempRequest);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public ArrayList<Request> getAll() {
        try {
            EntityManager em = getEMF().createEntityManager();
            em.getTransaction().begin();
            TypedQuery<Request> query = em.createQuery("SELECT r FROM Request r", Request.class);
            List<Request> results = query.getResultList();
            return new ArrayList<>(results);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public ArrayList<Request> getAllByClient() {
        //TODO: No tiene uso
        return null;
    }

    public ArrayList<Request> getAllByDate() {
        try {
            EntityManager em = getEMF().createEntityManager();
            em.getTransaction().begin();
            TypedQuery<Request> query = em.createQuery("SELECT r FROM Request r ORDER BY r.date", Request.class);
            List<Request> results = query.getResultList();
            return new ArrayList<>(results);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public ArrayList<Request> getAllNotDelivered() {
        try {
            EntityManager em = getEMF().createEntityManager();
            em.getTransaction().begin();
            TypedQuery<Request> query = em.createQuery("SELECT r FROM Request r WHERE r.delivered = false ORDER BY r.date", Request.class);
            List<Request> results = query.getResultList();
            return new ArrayList<>(results);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public ArrayList<Request> getAllDelivered() {
        //TODO: No tiene uso
        return null;
    }

    public ArrayList<Request> getAllToday() {
        try {
            EntityManager em = getEMF().createEntityManager();
            em.getTransaction().begin();
            TypedQuery<Request> query = em.createQuery(
                    "SELECT r FROM Request r WHERE r.date = :date", Request.class);
            query.setParameter("date", java.sql.Date.valueOf(LocalDate.now()));
            List<Request> results = query.getResultList();
            return new ArrayList<>(results);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public ArrayList<Request> getAllLastWeek() {
        try {
            EntityManager em = getEMF().createEntityManager();
            em.getTransaction().begin();
            TypedQuery<Request> query = em.createQuery(
                    "SELECT r FROM Request r WHERE r.date >= :date", Request.class);
            query.setParameter("date", subDaysToActualDate(7));
            List<Request> results = query.getResultList();
            return new ArrayList<>(results);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public ArrayList<Request> getAllLastMonth() {
        //TODO: No tiene uso
        return null;
    }

    public ArrayList<Request> getAllLastYear() {
        try {
            EntityManager em = getEMF().createEntityManager();
            em.getTransaction().begin();
            TypedQuery<Request> query = em.createQuery(
                    "SELECT r FROM Request r WHERE r.date = :date", Request.class);
            query.setParameter("date", subDaysToActualDate(365));
            List<Request> results = query.getResultList();
            return new ArrayList<>(results);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public ArrayList<String> getAllClients() {
        try {
            EntityManager em = getEMF().createEntityManager();
            em.getTransaction().begin();
            TypedQuery<String> query = em.createQuery(
                    "SELECT DISTINCT r.client FROM Request r", String.class);
            List<String> results = query.getResultList();
            return new ArrayList<>(results);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    private Date subDaysToActualDate(int days) {
        LocalDate date = LocalDate.now().minusDays(days);
        return java.sql.Date.valueOf(date);
    }


}

