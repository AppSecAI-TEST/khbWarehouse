package com.xinnet.service.impl;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinnet.dao.OrderMapper;
import com.xinnet.entity.Order;
import com.xinnet.service.IOrderService;
import com.xinnet.utils.CheckParamUtils;

@Service
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public void insertSelective(Order record) throws Exception {
		// TODO Auto-generated method stub
		CheckParamUtils.isEmpty(record);
		orderMapper.insertSelective(record);
		throw new RuntimeErrorException(null);
	}

	@Override
	public Order selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
