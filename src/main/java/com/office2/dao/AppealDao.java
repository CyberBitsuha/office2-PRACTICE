package com.office2.dao;

import com.office2.model.Appeal;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppealDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Appeal appeal) {
        sessionFactory.getCurrentSession().saveOrUpdate(appeal);
    }

    public Appeal findById(Long id) {
        return sessionFactory.getCurrentSession().get(Appeal.class, id);
    }

    public List<Appeal> findByManager(String manager) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Appeal WHERE managername = :m", Appeal.class)
                .setParameter("m", manager)
                .list();
    }
}
