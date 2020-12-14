package com.icis.dao;

import com.icis.pojo.Seller;

public interface ISellerDao {
    Seller getSellerBySid(Integer sid);
}
