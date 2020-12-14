package com.icis.dao;

import com.icis.pojo.RouteImg;

import java.util.List;

public interface IRouteImgDao {
    List<RouteImg> getRouteImgByRid(Integer rid);
}
