package com.example.orderhibfx.dao;

import com.example.orderhibfx.dao.hibernate.ProductDAOHibernate;
import com.example.orderhibfx.dao.hibernate.RequestDAOHibernate;
import com.example.orderhibfx.dao.objectdb.ProductDAOObjectDB;
import com.example.orderhibfx.dao.objectdb.RequestDAOObjectDB;
import com.example.orderhibfx.models.Request;
import com.example.orderhibfx.utils.DAO;
import com.example.orderhibfx.utils.DataBase;
import com.example.orderhibfx.utils.HibernateUtil;
import org.hibernate.Transaction;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.orderhibfx.utils.DataBase.SelectedDB.HIBERNATE;
import static com.example.orderhibfx.utils.DataBase.SelectedDB.OBJECTDB;

public class RequestDAO extends DataBase implements DAO<Request> {

    @Override
    public void save(Request request) {
        if (getSelectedDB().equals(HIBERNATE)) {
            new RequestDAOHibernate().save(request);
        } if (getSelectedDB().equals(OBJECTDB)) {
            new RequestDAOObjectDB().save(request);
        }
    }

    @Override
    public void update(Request request) {
        if (getSelectedDB().equals(HIBERNATE)) {
            new RequestDAOHibernate().update(request);
        } if (getSelectedDB().equals(OBJECTDB)) {
            new RequestDAOObjectDB().update(request);
        }
    }

    @Override
    public Request get(Integer id) {
        if (getSelectedDB().equals(HIBERNATE)) {
            return new RequestDAOHibernate().get(id);
        } if (getSelectedDB().equals(OBJECTDB)) {
            return new RequestDAOObjectDB().get(id);
        }
        return null;
    }

    @Override
    public void delete(Request request) {
        if (getSelectedDB().equals(HIBERNATE)) {
            new RequestDAOHibernate().delete(request);
        } if (getSelectedDB().equals(OBJECTDB)) {
            new RequestDAOObjectDB().delete(request);
        }
    }

    @Override
    public ArrayList<Request> getAll() {
        if (getSelectedDB().equals(HIBERNATE)) {
            return new RequestDAOHibernate().getAll();
        } if (getSelectedDB().equals(OBJECTDB)) {
            return new RequestDAOObjectDB().getAll();
        }
        return null;
    }

    public ArrayList<Request> getAllByClient() {
        if (getSelectedDB().equals(HIBERNATE)) {
            return new RequestDAOHibernate().getAllByClient();
        } if (getSelectedDB().equals(OBJECTDB)) {
            //TODO: No tiene uso
//            return new RequestDAOObjectDB().getAllByClient();
        }
        return null;
    }

    public ArrayList<Request> getAllByDate() {
        if (getSelectedDB().equals(HIBERNATE)) {
            return new RequestDAOHibernate().getAllByDate();
        } if (getSelectedDB().equals(OBJECTDB)) {
            return new RequestDAOObjectDB().getAllByDate();
        }
        return null;
    }

    public ArrayList<Request> getAllNotDelivered() {
        if (getSelectedDB().equals(HIBERNATE)) {
            return new RequestDAOHibernate().getAllNotDelivered();
        } if (getSelectedDB().equals(OBJECTDB)) {
            return new RequestDAOObjectDB().getAllNotDelivered();
        }
        return null;
    }

    public ArrayList<Request> getAllDelivered() {
        if (getSelectedDB().equals(HIBERNATE)) {
            return new RequestDAOHibernate().getAllDelivered();
        } if (getSelectedDB().equals(OBJECTDB)) {
            //TODO: No tiene uso
            //return new RequestDAOObjectDB().getAllDelivered();
        }
        return null;
    }

    public ArrayList<Request> getAllToday() {
        if (getSelectedDB().equals(HIBERNATE)) {
            return new RequestDAOHibernate().getAllToday();
        } if (getSelectedDB().equals(OBJECTDB)) {
            return new RequestDAOObjectDB().getAllToday();
        }
        return null;
    }

    public ArrayList<Request> getAllLastWeek() {
        if (getSelectedDB().equals(HIBERNATE)) {
            return new RequestDAOHibernate().getAllLastWeek();
        } if (getSelectedDB().equals(OBJECTDB)) {
            return new RequestDAOObjectDB().getAllLastWeek();
        }
        return null;
    }

    public ArrayList<Request> getAllLastMonth() {
        if (getSelectedDB().equals(HIBERNATE)) {
            return new RequestDAOHibernate().getAllLastMonth();
        } if (getSelectedDB().equals(OBJECTDB)) {
            //TODO: No tiene uso
//            return new RequestDAOObjectDB().getAllLastMonth();
        }
        return null;
    }

    public ArrayList<Request> getAllLastYear() {
        if (getSelectedDB().equals(HIBERNATE)) {
            return new RequestDAOHibernate().getAllLastYear();
        } if (getSelectedDB().equals(OBJECTDB)) {
            return new RequestDAOObjectDB().getAllLastYear();
        }
        return null;
    }

    public ArrayList<String> getAllClients() {
        if (getSelectedDB().equals(HIBERNATE)) {
            return new RequestDAOHibernate().getAllClients();
        } if (getSelectedDB().equals(OBJECTDB)) {
            return new RequestDAOObjectDB().getAllClients();
        }
        return null;
    }
}
