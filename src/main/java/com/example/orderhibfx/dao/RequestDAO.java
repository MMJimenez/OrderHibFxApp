package com.example.orderhibfx.dao;

import com.example.orderhibfx.models.Request;
import com.example.orderhibfx.utils.DAO;
import com.example.orderhibfx.utils.HibernateUtil;
import com.mysql.cj.xdevapi.Client;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class RequestDAO implements DAO<Request> {
    @Override
    public void save(Request request) {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.save(request);
            t.commit();
        }
    }

    @Override
    public void update(Request request) {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.update(request);
            t.commit();
        }
    }

    @Override
    public Request get(Integer id) {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Request.class, id);
        }
    }

    @Override
    public void delete(Request request) {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.remove(request);
            t.commit();
        }
    }

    @Override
    public ArrayList<Request> getAll() {
        try(var s = HibernateUtil.getSessionFactory().openSession()){
            var q = s.createQuery("from Request");
            return (ArrayList<Request>) q.list();
        }
    }

    @Override
    public ArrayList<Request> getAllByClient() {
        try(var s = HibernateUtil.getSessionFactory().openSession()){
            var q = s.createQuery("from Request as rq order by rq.client");
            return (ArrayList<Request>) q.list();
        }
    }

    @Override
    public ArrayList<Request> getAllByDate() {
        try(var s = HibernateUtil.getSessionFactory().openSession()){
            var q = s.createQuery("from Request as rq order by rq.date");
            return (ArrayList<Request>) q.list();
        }
    }

    @Override
    public ArrayList<Request> getAllNotDelivered() {
        try(var s = HibernateUtil.getSessionFactory().openSession()){
            var q = s.createQuery("from Request as rq where rq.delivered = 0");
            return (ArrayList<Request>) q.list();
        }
    }

    @Override
    public ArrayList<Request> getAllLastWeek() {
        try(var s = HibernateUtil.getSessionFactory().openSession()){
            var q = s.createQuery("from Request as rq where rq.date >= current_date-7");
            return (ArrayList<Request>) q.list();
        }
    }

    @Override
    public ArrayList<Request> getAllLastMonth() {
        try(var s = HibernateUtil.getSessionFactory().openSession()){
            var q = s.createQuery("from Request as rq where rq.date >= current_date-30");
            return (ArrayList<Request>) q.list();
        }
    }

    @Override
    public ArrayList<Request> getAllLastYear() {
        try(var s = HibernateUtil.getSessionFactory().openSession()){
            var q = s.createQuery("from Request as rq where rq.date >= current_date-365");
            return (ArrayList<Request>) q.list();
        }
    }

    public ArrayList<String> getAllClients() {
        try(var s = HibernateUtil.getSessionFactory().openSession()){
            var q = s.createQuery("select distinct rq.client from Request as rq");
            return (ArrayList<String>) q.list();
        }
    }
}
