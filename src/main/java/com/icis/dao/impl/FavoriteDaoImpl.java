package com.icis.dao.impl;

import com.icis.dao.IFavoriteDao;
import com.icis.pojo.Favorite;
import com.icis.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public class FavoriteDaoImpl implements IFavoriteDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Integer> getFavoriteByUid(Integer uid) {
        String sql="SELECT rid FROM tab_favorite WHERE uid=?";
        return template.queryForList(sql,Integer.class,uid);
    }

    @Override
    public int findCountByRid(int rid) {
        String sql = "SELECT COUNT(*) FROM tab_favorite WHERE rid = ?";
        return template.queryForObject(sql,Integer.class,rid);
    }

    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql="SELECT * FROM tab_favorite WHERE rid = ? AND uid = ?";
            favorite = template.queryForObject(sql,new BeanPropertyRowMapper<>(Favorite.class),rid,uid);
        }catch (Exception e){

        }
        return favorite;
    }

    @Override
    public void addFavorite(int rid, int uid) {
        String sql = "INSERT INTO tab_favorite VALUES(?,?,?)";
        template.update(sql,rid,new Date(),uid);
    }

    @Override
    public void deleteFavorite(int rid, int uid) {
        String sql = "DELETE FROM tab_favorite WHERE rid=? AND uid=?";
        template.update(sql,rid,uid);
    }
}
