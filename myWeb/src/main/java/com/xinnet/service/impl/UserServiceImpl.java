package com.xinnet.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinnet.dao.IdentifyingCodeMapper;
import com.xinnet.dao.UserMapper;
import com.xinnet.entity.IdentifyingCode;
import com.xinnet.entity.RegisterResultDto;
import com.xinnet.entity.User;
import com.xinnet.enums.SendModeEnum;
import com.xinnet.service.IOrderService;
import com.xinnet.service.IUserService;
import com.xinnet.utils.CheckParamUtils;
import com.xinnet.utils.EncryptUtils;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private IdentifyingCodeMapper dentifyingCodeMapper;
	
	
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
		RegisterResultDto dto = new RegisterResultDto();
		Map<Object, Object> param = new HashMap<Object, Object>();
		param.put("email", record.getEmail());
		param.put("time", new Date());
		param.put("code", code);
		param.put("effective", "true");
		IdentifyingCode identifyingCode = dentifyingCodeMapper.selectByParam(param);
		if(null == identifyingCode) {
			dto.setResult("false");
			dto.setMseeage("验证码失效");
			return dto;
		} else {
			dto.setResult("success");
			identifyingCode.setEffective("false");
			dentifyingCodeMapper.updateByPrimaryKeySelective(identifyingCode);
		}
		record.setPassWord(EncryptUtils.MD5(record.getPassWord()));
		record.setCreatTime(new Date());
		userMapper.insertSelective(record);
		dto.setUser(record);
		return dto;
		
		/*Order order = new Order();
		order.setUserId(record.getId());
		order.setAmout(new BigDecimal(10000));
		order.setWaterNum(UUID.randomUUID().toString());
		orderService.insertSelective(order);
//		throw new RuntimeException();
*/	}
}
