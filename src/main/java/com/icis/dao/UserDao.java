package com.icis.dao;

import com.icis.pojo.Category;
import com.icis.pojo.User;

import java.util.List;

public interface UserDao {
    String byUsername(String username);

    void activationUser(String code);

    void addUser(User user);

    User login(String username, String password);
}
