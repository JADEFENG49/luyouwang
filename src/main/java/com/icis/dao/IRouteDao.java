package com.icis.dao;

import com.icis.pojo.Route;

import java.util.List;

public interface IRouteDao {
    List<Route> getRouteByCid(Integer cid);

    List<Route> getIndexRoute();

    List<Route> getIndexRouteJX(Integer cid);

    List<Route> getRecommendation(Integer cid);

    Integer getRouteCount(Integer cid, String rname);

    List<Route> findPageRout(int start, Integer pageSize, Integer cid, String rname);

    Route getRouteByRid(Integer rid);

    List<Route> getCollection();
}
