package com.yeepay.g3.core.activity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.dao.AppMemberLoginLogDao;
import com.yeepay.g3.core.activity.entity.AppMemberLoginLog;
import com.yeepay.g3.core.activity.service.AppMemberLoginLogService;


/**
 * @Title: APP会员登录日志业务处理接口实现类
 * @author ping.zhu
 * @createTime 2016-9-21 下午4:25:04
 */
@Service
public class AppMemberLoginLogServiceImpl implements AppMemberLoginLogService{

	@Autowired
	private AppMemberLoginLogDao appMemberLoginLogDaoImpl;
	
	@Override
	public void insertAppMemberLoginLog(AppMemberLoginLog appMemberLoginLog) {
		// TODO Auto-generated method stub
		appMemberLoginLogDaoImpl.add(appMemberLoginLog);
	}

}
