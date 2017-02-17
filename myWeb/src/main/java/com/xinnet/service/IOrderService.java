package com.xinnet.service;

import com.xinnet.entity.Order;

public interface IOrderService {
	
	public void insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);
}
