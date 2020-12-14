package com.icis.dao.impl;

import com.icis.dao.IFavoriteDao;
import com.icis.dao.IRouteDao;
import com.icis.pojo.Route;
import com.icis.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements IRouteDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    private IFavoriteDao favoriteDao=new FavoriteDaoImpl();
    @Override
    public List<Route> getRouteByCid(Integer cid) {
        String sql="SELECT * FROM tab_route WHERE cid=?";
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class),cid);
    }

    @Override
    public List<Route> getIndexRoute() {
        String sql="SELECT * FROM tab_route WHERE cid=1 ORDER BY rid LIMIT 0,12";
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class));
    }

    @Override
    public List<Route> getIndexRouteJX(Integer cid) {
        String sql="SELECT * FROM tab_route WHERE cid=? ORDER BY rid LIMIT 0,6";
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class),cid);
    }

    @Override
    public List<Route> getRecommendation(Integer cid) {
        String sql = " select * from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if(cid != 0){
            sb.append( " and cid = ? ");
            params.add(cid);
        }
        sb.append("ORDER BY price LIMIT 0,5");
        sql = sb.toString();
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class),params.toArray());
    }

    @Override
    public Integer getRouteCount(Integer cid, String rname) {
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if(cid != 0){
            sb.append( " and cid = ? ");
            params.add(cid);
        }
        if(!"null".equals(rname)&&rname != null && rname.length() > 0){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sql = sb.toString();
        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Route> findPageRout(int start, Integer pageSize, Integer cid, String rname) {
        String sql = " select * from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if(cid != 0){
            sb.append( " and cid = ? ");
            params.add(cid);
        }
        if(!"null".equals(rname)&&rname != null && rname.length() > 0){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sb.append(" limit ? , ? ");
        sql = sb.toString();
        params.add(start);
        params.add(pageSize);
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class),params.toArray());
    }

    @Override
    public Route getRouteByRid(Integer rid) {
        String sql="SELECT * FROM tab_route WHERE rid=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(Route.class),rid);
    }

    @Override
    public List<Route> getCollection() {
        List<Route> routeList=new ArrayList<>();
        String sql="SELECT rid FROM (SELECT rid, COUNT(*)conut FROM tab_favorite GROUP BY rid ORDER BY conut DESC)a LIMIT 0,8";
        List<Integer> ridList = template.queryForList(sql, Integer.class);
        for (Integer rid : ridList) {
            Route route = getRouteByRid(rid);
            int count = this.favoriteDao.findCountByRid(route.getRid());
            route.setCount(count);
            routeList.add(route);
        }
        return routeList;
    }
}
