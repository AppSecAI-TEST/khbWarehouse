package com.xinnet.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xinnet.dao.UserMapper;
import com.xinnet.entity.Order;
import com.xinnet.entity.RegisterResultDto;
import com.xinnet.entity.User;
import com.xinnet.service.IOrderService;
import com.xinnet.service.IUserService;
import com.xinnet.utils.CheckParamUtils;
import com.xinnet.utils.EncryptUtils;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private IOrderService orderService;
	
	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public int adds(int a, int b) {
		// TODO Auto-generated method stub
		return a+b;
	}

	@Override
	public RegisterResultDto add(User record,String code) {
		CheckParamUtils.isEmpty(record);
		record.setPassWord(EncryptUtils.MD5(record.getPassWord()));
		record.setCreatTime(new Date());
		userMapper.insertSelective(record);
		return null;
		
		/*Order order = new Order();
		order.setUserId(record.getId());
		order.setAmout(new BigDecimal(10000));
		order.setWaterNum(UUID.randomUUID().toString());
		orderService.insertSelective(order);
//		throw new RuntimeException();
*/	}
}
