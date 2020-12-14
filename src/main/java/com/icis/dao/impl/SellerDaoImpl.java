package com.icis.dao.impl;

import com.icis.dao.ISellerDao;
import com.icis.pojo.Seller;
import com.icis.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SellerDaoImpl implements ISellerDao {
    private final JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Seller getSellerBySid(Integer sid) {
        String sql="SELECT * FROM tab_seller WHERE sid=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(Seller.class),sid);
    }
}
