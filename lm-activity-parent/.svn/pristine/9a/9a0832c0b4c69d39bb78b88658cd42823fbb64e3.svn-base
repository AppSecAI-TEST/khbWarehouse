package com.yeepay.g3.core.activity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.activity.dao.ActivityUserMessageDao;
import com.yeepay.g3.core.activity.entity.ActivityUserMessage;
import com.yeepay.g3.utils.persistence.mybatis.GenericDaoDefault;

/**
 * 
 * @Description 用户消息数据服务实现类
 * @author zhenping.zhou
 * @CreateTime 2016年6月4日 下午2:49:56
 * @version 1.0
 */
@Repository
public class ActivityUserMessageDaoImpl extends  GenericDaoDefault<ActivityUserMessage> implements ActivityUserMessageDao {

	@Override
	public ActivityUserMessage selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateByPrimaryKey(ActivityUserMessage record) {
		this.update(record);
	}

	@Override
	public void insertUserMessage(List<ActivityUserMessage> userMessageList) {
		for(ActivityUserMessage userMsg : userMessageList) {
			this.add(userMsg);
		}
	}

}
