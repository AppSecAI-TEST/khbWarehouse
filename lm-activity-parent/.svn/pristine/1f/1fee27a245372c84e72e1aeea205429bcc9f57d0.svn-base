package com.yeepay.g3.core.activity.dao;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityRule;
import com.yeepay.g3.utils.persistence.GenericDao;

/**
 * 
 * @Description 规则定义数据服务接口
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午5:01:59
 * @version 1.0
 */
public interface ActivityRuleDao extends GenericDao<ActivityRule> {

	public int deleteByPrimaryKey(Long id);

	public ActivityRule selectByPrimaryKey(Long id);
	
	/**
	 * 根据参数获取规则列表
	 * @param params
	 * @return
	 */
	public List<ActivityRule> selectByParams(Map<String, Object> params);

	public int updateByPrimaryKey(ActivityRule record);
}