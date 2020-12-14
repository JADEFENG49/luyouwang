package com.icis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.Route;
import com.icis.pojo.User;
import com.icis.service.IFavoriteService;
import com.icis.service.impl.FavoriteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
@WebServlet("/favorite/*")
public class FavoriteServlet extends BassServlet {
    private IFavoriteService favoriteService=new FavoriteServiceImpl();
    private ObjectMapper mapper=new ObjectMapper();
    public void getMyFavorite(HttpServletRequest request, HttpServletResponse response){
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            Integer uid = user.getUid();
            List<Route> routeList=this.favoriteService.getMyFa(uid);
            if (routeList!=null&&routeList.size()>0){
                String s = mapper.writeValueAsString(routeList);
                response.getWriter().write(s);
            }else {
                String s = mapper.writeValueAsString("1");
                response.getWriter().write(s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
