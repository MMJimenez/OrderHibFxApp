package com.example.orderhibfx.dao;

import com.example.orderhibfx.dao.hibernate.ProductDAOHibernate;
import com.example.orderhibfx.dao.objectdb.ProductDAOObjectDB;
import com.example.orderhibfx.models.Product;
import com.example.orderhibfx.utils.DAO;
import com.example.orderhibfx.utils.DataBase;
import static com.example.orderhibfx.utils.DataBase.SelectedDB.HIBERNATE;
import static com.example.orderhibfx.utils.DataBase.SelectedDB.OBJECTDB;

import java.util.ArrayList;

public class ProductDAO extends DataBase implements DAO<Product> {

    public ProductDAO(SelectedDB selectedDB) {
        setSelectedDB(selectedDB);
    }

    @Override
    public Product get(Integer id) {
        if (getSelectedDB().equals(HIBERNATE)) {
            return new ProductDAOHibernate().get(id);
        } if (getSelectedDB().equals(OBJECTDB)) {
            return new ProductDAOObjectDB().get(id);
        }
        return null;
    }

    @Override
    public ArrayList<Product> getAll() {
        if (getSelectedDB().equals(HIBERNATE)) {
            return new ProductDAOHibernate().getAll();
        } if (getSelectedDB().equals(OBJECTDB)) {
            return new ProductDAOObjectDB().getAll();
        }
        return null;
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
