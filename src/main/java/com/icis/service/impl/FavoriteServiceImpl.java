package com.icis.service.impl;

import com.icis.dao.IFavoriteDao;
import com.icis.dao.impl.FavoriteDaoImpl;
import com.icis.pojo.Favorite;
import com.icis.pojo.Route;
import com.icis.service.IFavoriteService;
import com.icis.service.IRouteService;

import java.util.ArrayList;
import java.util.List;

public class FavoriteServiceImpl implements IFavoriteService {
    private IFavoriteDao favoriteDao=new FavoriteDaoImpl();
    private IRouteService routeService=new RouteServiceImpl();
    @Override
    public List<Route> getMyFa(Integer uid) {
        List<Route> routeList=new ArrayList<>();
        List<Integer> rids=this.favoriteDao.getFavoriteByUid(uid);
        for (Integer rid : rids) {
            Route route = this.routeService.getRouteByRid(rid);
            routeList.add(route);
        }
        return routeList;
    }

    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);
        return favorite!=null;
    }

    @Override
    public void addFavorite(String rid, int uid) {
        this.favoriteDao.addFavorite(Integer.parseInt(rid),uid);
    }

    @Override
    public void deleteFavorite(String rid, int uid) {
        this.favoriteDao.deleteFavorite(Integer.parseInt(rid),uid);
    }
}
