package com.icis.dao;

import com.icis.pojo.Category;
import com.icis.pojo.User;

import java.util.List;

public interface ICategoryDao {
    List<Category> getCategory();

    List<Category> getIndexLb();

    Category getCategoryByCid(Integer cid);
}
