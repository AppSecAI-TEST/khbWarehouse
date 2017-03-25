package com.yeepay.g3.core.activity.dao;

import java.util.List;

import com.yeepay.g3.core.activity.entity.ActivityUserMessage;
import com.yeepay.g3.utils.persistence.GenericDao;

/**
 * 
 * @Description 用户消息数据服务接口类
 * @author zhenping.zhou
 * @CreateTime 2016年6月4日 下午2:24:22
 * @version 1.0
 */
public interface ActivityUserMessageDao extends GenericDao<ActivityUserMessage> {

    public ActivityUserMessage selectByPrimaryKey(Long id);

    public void updateByPrimaryKey(ActivityUserMessage record);

	/**
	 * 批量创建消息
	 * @param userMessageList
	 */
	public void insertUserMessage(List<ActivityUserMessage> userMessageList);

}