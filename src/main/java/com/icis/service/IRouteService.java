package com.icis.service;

import com.icis.pojo.PageBean;
import com.icis.pojo.Route;

import java.util.List;

public interface IRouteService {
    List<Route> getRouteByCid(Integer cid);

    List<Route> getIndexRoute();

    List<Route> getRecommendation(Integer cid);

    PageBean<Route> pageQuery(String currentPageStr, String pageSizeStr, Integer cid, String rname);

    Route getRouteByRid(Integer rid);

    List<Route> getCollection();
}
