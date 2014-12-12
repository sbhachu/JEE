package com.sbhachu.oauth.demo.dao;

import java.util.List;

public interface IGenericDAO<T> {
    public List<T> findAll();

    public T findById(Long id);

    public Long create(T item);

    public boolean update(T item);

    public boolean remove(T item);
}
