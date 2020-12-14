package com.icis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.User;
import com.icis.service.UserService;
import com.icis.service.impl.UserServiceImpl;
import com.icis.utils.MailUtils;
import com.icis.utils.Md5Util;
import com.icis.utils.UuidUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BassServlet {
    private UserService userService=new UserServiceImpl();
    private ObjectMapper mapper=new ObjectMapper();
    /**
     * 登录功能
     * @param request
     * @param response
     */
    public void login(HttpServletRequest request, HttpServletResponse response){
        try {
            String username = request.getParameter("username");
            String ps = request.getParameter("password");
            String password = Md5Util.encodeByMd5(ps);
            String check = request.getParameter("check");
            Map<String ,Integer> map=new HashMap<>();
            HttpSession session = request.getSession();
            String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
            session.removeAttribute("CHECKCODE_SERVER");
            if (check.equalsIgnoreCase(checkcode_server)){
                try {
                    User user = userService.login(username, password);
                    if (user.getStatus().equals("N")){
                        map.put("id",2);
                        String Msg = mapper.writeValueAsString(map);
                        response.getWriter().write(Msg);
                    }else {
                        String checkbox = request.getParameter("checkbox");
                        if (checkbox!=null&&!"".equals(checkbox)){
                            Cookie cookie1=new Cookie("username",user.getUsername());
                            Cookie cookie2=new Cookie("password",user.getPassword());
                            cookie1.setMaxAge(60);
                            cookie2.setMaxAge(60);
                            cookie1.setPath(request.getContextPath());
                            cookie2.setPath(request.getContextPath());
                            response.addCookie(cookie1);
                            response.addCookie(cookie2);
                        }
                        session.setAttribute("user",user);
                        map.put("id",3);
                        String Msg = mapper.writeValueAsString(map);
                        response.getWriter().write(Msg);
                    }
                }catch (Exception e){
                    map.put("id",0);
                    String Msg = mapper.writeValueAsString(map);
                    response.getWriter().write(Msg);
                }
            }else {
                map.put("id",1);
                String Msg = mapper.writeValueAsString(map);
                response.getWriter().write(Msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void checkUsernaem(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
            String username = request.getParameter("username");
            String name=this.userService.byUsername(username);
            if (name!=null&&!"".equals(name)){
                response.getWriter().write("Y");
            }
        }catch (Exception e){
            response.getWriter().write("N");
        }
    }
    public void registerUser(HttpServletRequest request,HttpServletResponse response){
        try {
            Map<String, String[]> map = request.getParameterMap();
            User user=new User();
            BeanUtils.populate(user,map);
            String password = Md5Util.encodeByMd5(user.getPassword());
            user.setStatus("N");
            user.setCode(UuidUtil.getUuid());
            user.setPassword(password);
            String check = request.getParameter("check");
            HttpSession session = request.getSession();
            String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
            session.removeAttribute("CHECKCODE_SERVER");
            if (check.equalsIgnoreCase(checkcode_server)){
                userService.addUser(user);
                MailUtils.sendMail(user.getEmail(),"这是一个激活页面，<a href=\"http://localhost:8086"+request.getContextPath()+"/user/activeUser?code="+user.getCode()+"\">点击激活</a>","激活页面");
                response.getWriter().write("true");
            }else {
                response.getWriter().write("验证码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void activeUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
            String code = request.getParameter("code");
            this.userService.activationUser(code);
            response.getWriter().write("激活成功");
        }catch (Exception e){
            response.getWriter().write("激活失败");
        }
    }
    public void signOut(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //清除账号信息
        request.getSession().invalidate();
        //清除cookie
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())||"password".equals(cookie.getName())){
                    cookie.setMaxAge(0);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
            }
        }
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
    //点击收藏
    public void findUser(HttpServletRequest request,HttpServletResponse response){
        try {
            Map<String,Boolean> map=new HashMap<>();
            User user = (User) request.getSession().getAttribute("user");
            if (user==null){
                map.put("flag",false);
                String s = mapper.writeValueAsString(map);
                response.getWriter().write(s);
            }else {
                map.put("flag",true);
                String s = mapper.writeValueAsString(map);
                response.getWriter().write(s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
