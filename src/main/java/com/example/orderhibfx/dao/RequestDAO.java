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

    //TODO: no estaba en la interfaz. Habría que ponerla
    public ArrayList<Request> getAll(String command){
        try(var s = HibernateUtil.getSessionFactory().openSession()){
            var q = s.createQuery("from Request"+command);
            return (ArrayList<Request>) q.list();
        }
    }
}
