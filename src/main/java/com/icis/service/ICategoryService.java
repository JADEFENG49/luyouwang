package com.icis.service;

import com.icis.pojo.Category;
import com.icis.pojo.User;

import java.util.List;

public interface ICategoryService {

    List<Category> getCategory();

    List<Category> getIndexLb();
    Category getCategoryByCid(Integer cid);
}
