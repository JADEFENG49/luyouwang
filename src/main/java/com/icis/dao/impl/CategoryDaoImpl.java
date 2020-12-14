package com.icis.dao.impl;

import com.icis.dao.ICategoryDao;
import com.icis.dao.UserDao;
import com.icis.pojo.Category;
import com.icis.pojo.User;
import com.icis.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements ICategoryDao {
    JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> getCategory() {
        String sql="SELECT * FROM tab_category ORDER BY cid";
        return template.query(sql,new BeanPropertyRowMapper<>(Category.class));
    }

    @Override
    public List<Category> getIndexLb() {
        String sql="SELECT * FROM tab_category ORDER BY cid LIMIT 0,3";
        return template.query(sql,new BeanPropertyRowMapper<>(Category.class));
    }

    @Override
    public Category getCategoryByCid(Integer cid) {
        String sql="SELECT * FROM tab_category WHERE cid=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(Category.class),cid);
    }
}
