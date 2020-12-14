package com.icis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.Category;
import com.icis.pojo.User;
import com.icis.service.ICategoryService;
import com.icis.service.impl.CategoryServiceImpl;
import com.icis.utils.JedisUtil;
import redis.clients.jedis.Jedis;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/category/*")
public class CategoryServlet extends BassServlet{
    private ICategoryService categoryService=new CategoryServiceImpl();
    private ObjectMapper mapper=new ObjectMapper();
    public void getHeaderData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        List categoryList=new ArrayList<>();
        if (user!=null){
            Map<String,String> map=new HashMap<>();
            map.put("name",user.getName());
            categoryList.add(map);
        }
        Jedis jedis = JedisUtil.getJedis();
        if (null==jedis.lrange("cname", 0, -1)||
                jedis.lrange("cname", 0, -1).isEmpty()||
                jedis.lrange("cname", 0, -1).size()<=0){
            List<Category> list=this.categoryService.getCategory();
            String[] cid=new String[list.size()];
            String[] cname=new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                cid[i]= String.valueOf(list.get(i).getCid());
                cname[i]=list.get(i).getCname();
            }
            jedis.rpush("cid",cid);
            jedis.rpush("cname",cname);
        }
        List<String> list= jedis.lrange("cid", 0, -1);
        for (int i = 0; i < list.size(); i++) {
            List<String> list1= jedis.lrange("cname", 0, -1);
            Category category=new Category(Integer.parseInt(list.get(i)),list1.get(i));
            categoryList.add(category);
        }
        String s = this.mapper.writeValueAsString(categoryList);
        response.getWriter().write(s);
    }
    public void getIndexLabel(HttpServletRequest request, HttpServletResponse response){
        try {
            List<Category> list=this.categoryService.getIndexLb();
            String s = this.mapper.writeValueAsString(list);
            response.getWriter().write(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
