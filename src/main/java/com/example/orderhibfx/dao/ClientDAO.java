package com.example.orderhibfx.dao;

import com.example.orderhibfx.models.Client;
import com.example.orderhibfx.utils.HibernateUtil;
import com.example.orderhibfx.utils.DAO;

import org.hibernate.Transaction;


public class ClientDAO implements DAO<Client> {

    @Override
    public void save(Client client) {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.save(client);
            t.commit();
        }
    }

    @Override
    public void update(Client client) {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.update(client);
            t.commit();
        }
    }

    @Override
    public Client get(Integer id) {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Client.class, id);
        }
    }

    @Override
    public void delete(Client client) {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.remove(client);
            t.commit();
        }
    }
}