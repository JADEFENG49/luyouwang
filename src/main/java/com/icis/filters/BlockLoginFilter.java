package com.icis.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.User;
import com.icis.service.UserService;
import com.icis.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter("/*")
public class BlockLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //ObjectMapper mapper=new ObjectMapper();
        UserService userService=new UserServiceImpl();
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/login")){
            Cookie[] cookies = request.getCookies();
            if(cookies!=null){
                String username=null;
                String password=null;
                for(Cookie cookie:cookies){
                    if ("username".equals(cookie.getName())){
                        username=cookie.getValue();
                    }else if ("password".equals(cookie.getName())){
                        password=cookie.getValue();
                    }
                }
                if (null!=username&&null!=password){
                    try {
                        User user = userService.login(username, password);
                        request.getSession().setAttribute("user",user);
                        response.sendRedirect(request.getContextPath()+"/index.html");
                    }catch (Exception e){
                        chain.doFilter(req, resp);
                    }
                }else {
                    chain.doFilter(req, resp);
                }
            }else {
                chain.doFilter(req, resp);
            }
        }else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
