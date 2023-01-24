package com.example.orderhibfx.dao.hibernate;

import com.example.orderhibfx.models.Product;
import com.example.orderhibfx.utils.DAO;
import com.example.orderhibfx.utils.HibernateUtil;
import java.util.ArrayList;

public class ProductDAOHibernate implements DAO<Product> {

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
    public void save(Product product) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Product product) {

    }
}
