package com.icis.service.impl;

import com.icis.dao.UserDao;
import com.icis.dao.impl.UserDaoImpl;
import com.icis.pojo.Category;
import com.icis.pojo.User;
import com.icis.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao= new UserDaoImpl();
    @Override
    public void addUser(User user) {
        this.userDao.addUser(user);
    }

    @Override
    public String byUsername(String usernaem) {
        return this.userDao.byUsername(usernaem);
    }

    @Override
    public void activationUser(String code) {
        this.userDao.activationUser(code);
    }

    @Override
    public User login(String username, String password) {
        return this.userDao.login(username,password);
    }


}
