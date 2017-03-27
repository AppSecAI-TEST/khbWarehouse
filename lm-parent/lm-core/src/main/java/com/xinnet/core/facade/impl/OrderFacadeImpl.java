package com.xinnet.core.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinnet.core.annotation.HessianService;
import com.xinnet.core.dao.OrderMapper;
import com.xinnet.core.entity.Order;
import com.xinnet.core.entity.User;
import com.xinnet.core.service.IOrderService;
import com.xinnet.core.utils.CheckParamUtils;
import com.xinnet.core.utils.EntityDTOConvert;
import com.xinnet.facade.dto.OrderDTO;
import com.xinnet.facade.dto.UserInfoDTO;
import com.xinnet.facade.facade.OrderFacade;

@HessianService
public class OrderFacadeImpl implements OrderFacade {
	
	@Autowired
	private IOrderService orderServiceImpl;

	@Override
	public void insertSelective(OrderDTO record) {
		Order order = EntityDTOConvert.toTarget(record, Order.class);
		orderServiceImpl.insertSelective(order);
	}

	@Override
	public OrderDTO selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		Order order =  orderServiceImpl.selectByPrimaryKey(id);
		OrderDTO dto = EntityDTOConvert.toTarget(order, OrderDTO.class);
		return dto;
	}

	

}
