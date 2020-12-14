package com.icis.service.impl;

import com.icis.dao.IRouteImgDao;
import com.icis.dao.impl.RouteImgDaoImpl;
import com.icis.pojo.RouteImg;
import com.icis.service.IRouteImgService;

import java.util.List;

public class RouteImgServiceImpl implements IRouteImgService {
    private IRouteImgDao routeImgDao=new RouteImgDaoImpl();
    @Override
    public List<RouteImg> getRouteImgByRid(Integer rid) {
        return this.routeImgDao.getRouteImgByRid(rid);
    }
}
