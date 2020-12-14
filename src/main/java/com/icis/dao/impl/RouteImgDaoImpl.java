package com.icis.dao.impl;

import com.icis.dao.IRouteImgDao;
import com.icis.pojo.RouteImg;
import com.icis.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteImgDaoImpl implements IRouteImgDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<RouteImg> getRouteImgByRid(Integer rid) {
        String sql="SELECT * FROM tab_route_img WHERE rid=?";
        return template.query(sql,new BeanPropertyRowMapper<>(RouteImg.class),rid);
    }
}
