package com.example.orderhibfx.dao;

import com.example.orderhibfx.models.Product;
import com.example.orderhibfx.models.Request;
import com.example.orderhibfx.utils.DAO;
import com.example.orderhibfx.utils.HibernateUtil;

import java.util.ArrayList;

public class ProductDAO implements DAO {

    @Override
    public Product get(Integer id) {
        try (var s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Product.class, id);
        }
    }

    @Override
    public ArrayList<Product> getAll() {
        try(var s = HibernateUtil.getSessionFactory().openSession()){
            var q = s.createQuery("from Product");
            return (ArrayList<Product>) q.list();
        }
    }

    //métodos que no se usan
    @Override
    public void save(Object object) {

    }

    @Override
    public void update(Object object) {

    }

    @Override
    public void delete(Object object) {

    }

    @Override
    public ArrayList<Request> getAllByClient() {
        return null;
    }

    @Override
    public ArrayList<Request> getAllByDate() {
        return null;
    }

    @Override
    public ArrayList<Request> getAllNotDelivered() {
        return null;
    }

    @Override
    public ArrayList<Request> getAllLastWeek() {
        return null;
    }

    @Override
    public ArrayList<Request> getAllLastMonth() {
        return null;
    }

    @Override
    public ArrayList<Request> getAllLastYear() {
        return null;
    }
}
