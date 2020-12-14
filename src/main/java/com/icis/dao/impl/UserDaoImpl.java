package com.icis.dao.impl;

import com.icis.dao.UserDao;
import com.icis.pojo.Category;
import com.icis.pojo.User;
import com.icis.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public String byUsername(String username) {
        String sql="SELECT username FROM tab_user WHERE username=?";
        return template.queryForObject(sql,String.class,username);
    }

    @Override
    public void activationUser(String code) {
        String sql="UPDATE tab_user SET STATUS='Y' WHERE CODE=?";
        template.update(sql,code);
    }

    @Override
    public void addUser(User user) {
        String sql="INSERT INTO tab_user(uid,username,PASSWORD,NAME,birthday,sex,telephone,email,STATUS,CODE) VALUES(NULL,?,?,?,?,?,?,?,?,?)";
        template.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),user.getStatus(),user.getCode());
    }

    @Override
    public User login(String username, String password) {
        String sql="SELECT * FROM tab_user WHERE username=? AND PASSWORD=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),username,password);
    }


}
