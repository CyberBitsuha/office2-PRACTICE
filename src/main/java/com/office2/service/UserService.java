package com.office2.service;

import com.office2.dao.UserDao;
import com.office2.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    public User authenticate(String login, String password) {
        User user = userDao.findByUsername(login);
        if (user == null) return null;
        if (BCrypt.checkpw(password, user.getPassword())) return user;
        return null;
    }

    /** Позволяет фильтру восстановить User по username */
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
