package com.kravchenko.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class represents General DAO methods.
 */
public abstract class AbstractDAO {

    /**
     * Session factory instance.
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Returns sessionFactory session.
     * @return current Session
     */
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Saves entity in session.
     * @param entity Any entity
     */
    public void persist(Object entity) {
        getSession().persist(entity);
    }

    /**
     * Deletes entity in session.
     * @param entity Any entity
     */
    public void delete(Object entity) {
        getSession().delete(entity);
    }

}
