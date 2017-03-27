package com.xinnet.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinnet.core.dao.OrderMapper;
import com.xinnet.core.entity.Order;
import com.xinnet.core.service.IOrderService;
import com.xinnet.core.utils.CheckParamUtils;

@Service
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public void insertSelective(Order record) {
		// TODO Auto-generated method stub
		CheckParamUtils.isEmpty(record);
		orderMapper.insertSelective(record);
		
	}

	@Override
	public Order selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
