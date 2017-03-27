package com.xinnet.core.service;

import com.xinnet.core.entity.Order;

public interface IOrderService {
	
	public void insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);
}
