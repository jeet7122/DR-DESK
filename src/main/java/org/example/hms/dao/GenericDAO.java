package org.example.hms.dao;

import java.util.List;

public interface GenericDAO<T>{
    void insert(T obj);
    void update(T obj, int id);
    void delete(int id);
    List<T> getAll();

}
