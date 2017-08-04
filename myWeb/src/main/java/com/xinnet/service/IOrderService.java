package com.xinnet.service;

import java.util.List;

import com.xinnet.entity.Order;

public interface IOrderService {
	
	public void insertSelective(Order record) throws Exception;

    Order selectByPrimaryKey(Integer id);
    
    List<Order> getAll();

	public Order selectMybatisCache(int i);
}
