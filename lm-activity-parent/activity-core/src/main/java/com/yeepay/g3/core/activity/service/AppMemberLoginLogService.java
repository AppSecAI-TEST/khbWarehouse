package com.yeepay.g3.core.activity.service;

import com.yeepay.g3.core.activity.entity.AppMemberLoginLog;


/**
 * @Title: APP会员登录日志业务处理接口
 * @author ping.zhu
 * @createTime 2016-9-21 下午4:25:04
 */
public interface AppMemberLoginLogService {

	/**
	 * 添加APP会员登录日志
	 * @param appMemberLoginLog
	 */
	public void insertAppMemberLoginLog(AppMemberLoginLog appMemberLoginLog);
}
