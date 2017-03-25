package com.yeepay.g3.core.activity.dao;

import java.util.List;

import com.yeepay.g3.core.activity.entity.ActivityEvent;
import com.yeepay.g3.utils.persistence.GenericDao;

/**
 * 
 * @Description 事件定义数据服务接口
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午5:03:30
 * @version 1.0
 */
public interface ActivityEventDao extends GenericDao<ActivityEvent> {

	public int deleteByPrimaryKey(Long id);

	public ActivityEvent selectByPrimaryKey(Long id);
	
	/**
	 * 根据规则ID获取事件列表
	 * @param id
	 * @return
	 */
	public List<ActivityEvent> selectByRuleId(Long ruleId);

}