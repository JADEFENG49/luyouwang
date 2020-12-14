package com.icis.service.impl;

import com.icis.dao.*;
import com.icis.dao.impl.*;
import com.icis.pojo.*;
import com.icis.service.IFavoriteService;
import com.icis.service.IRouteService;

import java.util.List;

public class RouteServiceImpl implements IRouteService{
    private IRouteDao routeDao=new RouteDaoImpl();
    private ICategoryDao categoryDao=new CategoryDaoImpl();
    private IRouteImgDao routeImgDao=new RouteImgDaoImpl();
    private ISellerDao sellerDao=new SellerDaoImpl();
    private IFavoriteDao favoriteDao=new FavoriteDaoImpl();
    @Override
    public List<Route> getRouteByCid(Integer cid) {
        return routeDao.getRouteByCid(cid);
    }

    @Override
    public List<Route> getIndexRoute() {
        List<Route> routeList = routeDao.getIndexRoute();
        List<Route> routeList1=routeDao.getIndexRouteJX(2);
        List<Route> routeList2=routeDao.getIndexRouteJX(3);
        for (Route route : routeList1) {
            routeList.add(route);
        }
        for (Route route : routeList2) {
            routeList.add(route);
        }
        return routeList;
    }

    @Override
    public List<Route> getRecommendation(Integer cid) {
        return this.routeDao.getRecommendation(cid);
    }

    @Override
    public PageBean<Route> pageQuery(String _currentPage, String _pageSize, Integer cid, String rname) {
        PageBean<Route> pageBean=new PageBean<>();
        if (_currentPage==null || "".equals(_currentPage)){
            _currentPage="1";
        }
        if (_pageSize==null || "".equals(_pageSize)){
            _pageSize="4";
        }
        Integer currentPage = Integer.parseInt(_currentPage);
        Integer pageSize = Integer.parseInt(_pageSize);
        pageBean.setPageSize(pageSize);
        Integer total=this.routeDao.getRouteCount(cid,rname);
        pageBean.setTotalCount(total);
        Integer totalpage=total%pageSize==0?total/pageSize:total/pageSize+1;
        pageBean.setTotalPage(totalpage);
        if (currentPage<=0){
            currentPage=1;
        }else if(currentPage>totalpage&&totalpage!=0){
            currentPage=totalpage;
        }
        pageBean.setCurrentPage(currentPage);
        int start = (currentPage - 1) * pageSize;
        List<Route> routeList=this.routeDao.findPageRout(start,pageSize,cid,rname);
        pageBean.setDataList(routeList);
        return pageBean;
    }

    @Override
    public Route getRouteByRid(Integer rid) {
        Route route=this.routeDao.getRouteByRid(rid);
        List<RouteImg> routeImgList = this.routeImgDao.getRouteImgByRid(rid);
        Category category = this.categoryDao.getCategoryByCid(route.getCid());
        Seller seller = this.sellerDao.getSellerBySid(route.getSid());
        int count = this.favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);
        route.setCategory(category);
        route.setSeller(seller);
        route.setRouteImgList(routeImgList);
        return route;
    }

    @Override
    public List<Route> getCollection() {
        return this.routeDao.getCollection();
    }
}
