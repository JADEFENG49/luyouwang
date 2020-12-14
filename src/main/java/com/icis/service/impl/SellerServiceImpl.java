package com.icis.service.impl;

import com.icis.dao.ISellerDao;
import com.icis.dao.impl.SellerDaoImpl;
import com.icis.pojo.Seller;
import com.icis.service.ISellerService;

public class SellerServiceImpl implements ISellerService {
    private ISellerDao sellerDao=new SellerDaoImpl();
    @Override
    public Seller getSellerBySid(Integer sid) {
        return this.sellerDao.getSellerBySid(sid);
    }
}
