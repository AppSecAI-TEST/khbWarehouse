package com.xinnet.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinnet.dao.OrderMapper;
import com.xinnet.entity.Order;
import com.xinnet.service.IOrderService;
import com.xinnet.utils.CheckParamUtils;
import com.xinnet.yeepay.YeepayDefault;
import com.xinnet.yeepay.dao.YeepayDefaultDao;

@Service
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	@Resource
	private YeepayDefaultDao yeepayDefaultDao;

	@Override
	public void insertSelective(Order record) throws Exception {
		// TODO Auto-generated method stub
		CheckParamUtils.isEmpty(record);
		orderMapper.insertSelective(record);
		throw new RuntimeErrorException(null);
	}

	@Override
	public Order selectByPrimaryKey(Integer id) {
		return orderMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Order> getAll() {
		return orderMapper.getAll();
	}

	@Override
	public Order selectMybatisCache(int id) {
		//同一事物之中共享sqlsession，可以使用mybatis一级缓存，但是在两次相同查询之间任何更新或者删除操作都会清除sqlsession的一级缓存
		//使用一级缓存，执行相同语句只会调用一次sql
		System.out.println("firstSelect查询");
		Order firstSelect = orderMapper.selectByPrimaryKey(id);
		System.out.println("secondSelect查询");
		Order secondSelect = orderMapper.selectByPrimaryKey(id);
		if(firstSelect == secondSelect) {
			System.out.println("Order是统一sqlsession");
		} else {
			System.out.println("Order是不同sqlsession");
		}
		return orderMapper.selectByPrimaryKey(id);
	}

}
