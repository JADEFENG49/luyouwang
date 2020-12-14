package com.icis.service;

import com.icis.pojo.Route;

import java.util.List;

public interface IFavoriteService {
    List<Route> getMyFa(Integer uid);

    boolean isFavorite(String rid, int uid);

    void addFavorite(String rid, int uid);

    void deleteFavorite(String rid, int uid);
}
