package com.icis.service;

import com.icis.pojo.Category;
import com.icis.pojo.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    //验证用户名是否存在
    String byUsername(String usernaem);

    void activationUser(String code);

    User login(String username, String password);

}
