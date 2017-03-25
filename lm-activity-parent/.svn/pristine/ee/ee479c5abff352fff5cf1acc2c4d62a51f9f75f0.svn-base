/**
 * 
 */
package com.yeepay.g3.core.activity.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.dao.ActivityInvForProInfoDao;
import com.yeepay.g3.core.activity.dao.ActivityInvForProRuleXTDao;
import com.yeepay.g3.core.activity.entity.ActivityGoods;
import com.yeepay.g3.core.activity.entity.ActivityInvForProInfo;
import com.yeepay.g3.core.activity.entity.ActivityInvForProRuleXT;
import com.yeepay.g3.core.activity.service.ActivityInvForProInfoService;
import com.yeepay.g3.core.activity.service.ActivityInvForProRuleService;
import com.yeepay.g3.facade.activity.dto.ActivityInvForProRuleXTDTO;

/**
 * @Description 
 * @author hongbin.kang
 * @CreateTime 2016年7月27日 下午2:15:14
 * @version 1.0
 */
@Service
public class ActivityInvForProRuleServiceImpl implements ActivityInvForProRuleService {
	
    private static Log logger = LogFactory.getLog(ActivityInvForProRuleServiceImpl.class);

	@Autowired
	private ActivityInvForProRuleXTDao activityInvForProRuleDaoImpl;

	@Override
	public ActivityInvForProRuleXT selectInvForProRuleById(long id) {
		// TODO Auto-generated method stub
		return (ActivityInvForProRuleXT)activityInvForProRuleDaoImpl.queryOne("selectByPrimaryKey", id);
	}

	@Override
	public void addActivityInvForProRule(
			ActivityInvForProRuleXT activityInvForProRule) {
		if(null != activityInvForProRule) {
			activityInvForProRuleDaoImpl.add(activityInvForProRule);
		}
	}

	@Override
	public List<ActivityInvForProRuleXT> selectListByParams(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityInvForProRuleXT> selectEffInvForProRuleList() {
		return activityInvForProRuleDaoImpl.query("selectEffInvForProRuleList");
	}

	@Override
	public List<ActivityInvForProRuleXT> selectByEventCode(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateActivityInvForProRuleById(
			ActivityInvForProRuleXT activityInvForProRule) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ActivityInvForProRuleXT> selectRuleByProductId(Long id) {
		// TODO Auto-generated method stub
		return activityInvForProRuleDaoImpl.query("selectRuleByProductId",id);
	}

	@Override
	public List<ActivityInvForProRuleXT> selectForProRuleList() {
		// TODO Auto-generated method stub
		return activityInvForProRuleDaoImpl.query("selecForProRuleList");
	}

}
