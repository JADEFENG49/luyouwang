package com.icis.dao;

import com.icis.pojo.Favorite;

import java.util.List;

public interface IFavoriteDao {
    List<Integer> getFavoriteByUid(Integer uid);

    int findCountByRid(int rid);

    Favorite findByRidAndUid(int i, int uid);

    void addFavorite(int rid, int uid);

    void deleteFavorite(int rid, int uid);
}
