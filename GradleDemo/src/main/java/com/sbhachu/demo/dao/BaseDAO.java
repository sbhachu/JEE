package com.sbhachu.demo.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public abstract class BaseDAO<T extends Serializable> implements IGenericDAO<T> {
    protected SessionFactory sessionFactory;

    private final Class<T> type;

    public BaseDAO(Class<T> type) {
        this.type = type;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Criteria createCriteria() {
        return this.sessionFactory.getCurrentSession().createCriteria(type);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return (List<T>) createCriteria().list();
    }

    @SuppressWarnings("unchecked")
    public List<T> findAllOrderByParameter(Boolean ascending, String parameter) {
        Order order;
        if (ascending)
            order = Order.asc(parameter);
        else
            order = Order.desc(parameter);

        Criteria criteria = createCriteria().addOrder(order);

        return (List<T>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    public T findById(Long id) {
        return (T) createCriteria().add(Restrictions.eq("id", id)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public T findByParameter(String parameter, Object value) {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq(parameter, value));

        return (T) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public T findByInsensitiveParameter(String parameter, Object value) {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.ilike(parameter, value));

        return (T) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public T findByParameters(HashMap<String, Object> parameters) {
        Criteria criteria = createCriteria();

        for (String key : parameters.keySet()) {
            criteria.add(Restrictions.eq(key, parameters.get(key)));
        }

        return (T) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<T> findAllByParameter(String parameter, Object value) {
        Criteria criteria = createCriteria();

        return (List<T>) criteria.add(Restrictions.eq(parameter, value)).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> findAllByParameters(HashMap<String, Object> parameters) {
        Criteria criteria = createCriteria();

        for (String key : parameters.keySet()) {
            criteria.add(Restrictions.eq(key, parameters.get(key)));
        }

        return (List<T>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<T> findAllByInsensitiveParameter(String parameter, Object value) {
        return (List<T>) createCriteria().add(Restrictions.ilike(parameter, value)).list();
    }

    public Long create(T item) {
        return (Long) this.sessionFactory.getCurrentSession().save(item);
    }

    public boolean update(T item) {
        this.sessionFactory.getCurrentSession().update(item);
        return true;
    }

    public void createOrUpdate(T item) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(item);
    }

    @SuppressWarnings("unchecked")
    public T merge(T item) {
        return (T) this.sessionFactory.getCurrentSession().merge(item);
    }

    public boolean remove(T item) {
        this.sessionFactory.getCurrentSession().delete(item);
        return false;
    }
}
