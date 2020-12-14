package com.icis.test;

import com.icis.dao.ICategoryDao;
import com.icis.dao.IFavoriteDao;
import com.icis.dao.IRouteDao;
import com.icis.dao.impl.*;
import com.icis.pojo.*;
import com.icis.service.ICategoryService;
import com.icis.service.IFavoriteService;
import com.icis.service.IRouteService;
import com.icis.service.impl.CategoryServiceImpl;
import com.icis.service.impl.FavoriteServiceImpl;
import com.icis.service.impl.RouteServiceImpl;
import com.icis.utils.JDBCUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RouteTest {
    private IRouteService service=new RouteServiceImpl();
    private ICategoryDao categoryService=new CategoryDaoImpl();
    private SellerDaoImpl sellerDao=new SellerDaoImpl();
    private RouteImgDaoImpl dao=new RouteImgDaoImpl();
    private IFavoriteService favoriteService=new FavoriteServiceImpl();
    @Test
    public void get(){
        JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
        String sql="SELECT rid FROM (SELECT rid, COUNT(*)conut FROM tab_favorite GROUP BY rid ORDER BY conut DESC)a LIMIT 0,8";
        List<Integer> integers = template.queryForList(sql, Integer.class);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }
    @Test
    public void gs(){
        int[]a={1,2,3,4,5,6,7};
        for (int i = 0; i < a.length/2; i++) {
            int b=a[i];
            a[i]=a[a.length-(i+1)];
            a[a.length-i-1]=b;
        }
        System.out.println(Arrays.toString(a));
    }
}
