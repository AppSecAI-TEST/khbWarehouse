package com.yeepay.g3.core.activity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.dao.ActivityUserMessageDao;
import com.yeepay.g3.core.activity.entity.ActivityUserMessage;
import com.yeepay.g3.core.activity.service.ActivityUserMessageService;

/**
 * 
 * @Description 用户消息业务逻辑处理实现类
 * @author zhenping.zhou
 * @CreateTime 2016年6月4日 下午2:59:49
 * @version 1.0
 */
@Service
public class ActivityUserMessageServiceImpl implements ActivityUserMessageService {

	@Autowired
	private ActivityUserMessageDao activityUserMessageDaoImpl;

	@Override
	public ActivityUserMessage selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public void updateByPrimaryKey(ActivityUserMessage record) {
		
	}

	@Override
	public List<ActivityUserMessage> selectByMemberNo(String memberNo) {
		return activityUserMessageDaoImpl.query("selectByMemberNo", memberNo);
	}

	/* (non-Javadoc)
	 * @see com.yeepay.g3.core.activity.service.ActivityUserMessageService#updateReadStatus(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void updateReadStatus(ActivityUserMessage activityUserMessage) {
		activityUserMessageDaoImpl.update(activityUserMessage);
	}

	/* (non-Javadoc)
	 * @see com.yeepay.g3.core.activity.service.ActivityUserMessageService#queryMessageCountByMemberNo(java.lang.String)
	 */
	@Override
	public Integer queryMessageCountByMemberNo(String memberNo) {
		
		return (Integer) activityUserMessageDaoImpl.queryOne("queryMessageCountByMemberNo", memberNo);
	}
	
}
