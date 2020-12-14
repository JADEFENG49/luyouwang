package com.icis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.PageBean;
import com.icis.pojo.Route;
import com.icis.pojo.User;
import com.icis.service.IFavoriteService;
import com.icis.service.IRouteService;
import com.icis.service.impl.FavoriteServiceImpl;
import com.icis.service.impl.RouteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/route/*")
public class RouteServlet extends BassServlet {
    private IRouteService routeService=new RouteServiceImpl();
    private ObjectMapper mapper=new ObjectMapper();
    private IFavoriteService favoriteService=new FavoriteServiceImpl();
    public void getRouteByCid(HttpServletRequest request, HttpServletResponse response){
        try {
            String currentPageStr = request.getParameter("currentPage");
            String pageSizeStr = request.getParameter("pageSize");
            String cidstr = (request.getParameter("cid"));
            int cid = 0;
            if(!"null".equals(cidstr)&&cidstr!=null && cidstr.length() > 0){
                cid = Integer.parseInt(cidstr);
            }
            String rname = request.getParameter("rname");
            PageBean<Route> routePageBean=this.routeService.pageQuery(currentPageStr,pageSizeStr,cid,rname);
            String s = mapper.writeValueAsString(routePageBean);
            response.getWriter().write(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getIndexData(HttpServletRequest request, HttpServletResponse response){
        try {
            List<Route> routeByCid = this.routeService.getIndexRoute();
            String s = mapper.writeValueAsString(routeByCid);
            response.getWriter().write(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void getRecommendation(HttpServletRequest request, HttpServletResponse response){
        try {
            String cidstr = (request.getParameter("cid"));
            int cid = 0;
            if(!"null".equals(cidstr)&&cidstr!=null && cidstr.length() > 0){
                cid = Integer.parseInt(cidstr);
            }
            List<Route> recommendation = this.routeService.getRecommendation(cid);
            String s = mapper.writeValueAsString(recommendation);
            response.getWriter().write(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getRouteByRid(HttpServletRequest request, HttpServletResponse response){
        try {
            Integer rid = Integer.parseInt(request.getParameter("rid"));
            Route route=this.routeService.getRouteByRid(rid);
            String s = this.mapper.writeValueAsString(route);
            response.getWriter().write(s);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getCollection(HttpServletRequest request, HttpServletResponse response){
        try {
            List<Route> routeList=this.routeService.getCollection();
            String s = this.mapper.writeValueAsString(routeList);
            response.getWriter().write(s);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Boolean> map=new HashMap();
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if (user==null){
            uid=0;
        }else {
            uid=user.getUid();
        }
        boolean flag = favoriteService.isFavorite(rid, uid);
        map.put("id",flag);
        String s = this.mapper.writeValueAsString(map);
        response.getWriter().write(s);
    }
    //点击收藏
    public void Favorite(HttpServletRequest request, HttpServletResponse response){
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        favoriteService.addFavorite(rid,user.getUid());
    }
    //取消收藏
    public void deleteFavorite(HttpServletRequest request, HttpServletResponse response){
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        favoriteService.deleteFavorite(rid,user.getUid());
    }
}
