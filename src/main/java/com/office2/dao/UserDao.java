package com.office2.dao;

import com.office2.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public User findByUsername(String username) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM User WHERE username = :u", User.class)
                .setParameter("u", username)
                .uniqueResult();
    }
}
