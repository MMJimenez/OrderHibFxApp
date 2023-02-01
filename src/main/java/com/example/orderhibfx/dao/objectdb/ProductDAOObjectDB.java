package com.example.orderhibfx.dao.objectdb;

import com.example.orderhibfx.models.Product;
import com.example.orderhibfx.models.Request;
import com.example.orderhibfx.utils.DAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static com.example.orderhibfx.utils.ObjectDBUtil.getEntityManager;

public class ProductDAOObjectDB implements DAO<Product> {

    @Override
    public Product get(Integer id) {
        Product productResult = new Product();
        try {
            EntityManager em = getEntityManager();
            productResult = em.find(Product.class, id);
            em.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return productResult;
    }

    @Override
    public ArrayList<Product> getAll() {
        try {
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
            List<Product> results = query.getResultList();
            em.close();
            return new ArrayList<>(results);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public void save(Product product) {
        try {
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
            em.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public void update(Product object) {

    }

    @Override
    public void delete(Product object) {

    }
}
