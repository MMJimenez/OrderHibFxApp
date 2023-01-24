package com.example.orderhibfx.utils;


import com.example.orderhibfx.models.Request;

import java.util.ArrayList;

//DAO generico para ambas clases, Mensajes y Usuario
public interface DAO<T> {
    T get(Integer id);
    void save(T object);
    void update(T object);
    void delete(T object);
    ArrayList<T> getAll();
}