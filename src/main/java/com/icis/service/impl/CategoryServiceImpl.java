package com.icis.service.impl;

import com.icis.dao.ICategoryDao;
import com.icis.dao.UserDao;
import com.icis.dao.impl.CategoryDaoImpl;
import com.icis.dao.impl.UserDaoImpl;
import com.icis.pojo.Category;
import com.icis.pojo.User;
import com.icis.service.ICategoryService;
import com.icis.service.UserService;

import java.util.List;

public class CategoryServiceImpl implements ICategoryService {
    private ICategoryDao categoryDao= new CategoryDaoImpl();

    @Override
    public List<Category> getCategory() {
        return this.categoryDao.getCategory();
    }

    @Override
    public List<Category> getIndexLb() {
        return this.categoryDao.getIndexLb();
    }

    @Override
    public Category getCategoryByCid(Integer cid) {
        return this.categoryDao.getCategoryByCid(cid);
    }
}
