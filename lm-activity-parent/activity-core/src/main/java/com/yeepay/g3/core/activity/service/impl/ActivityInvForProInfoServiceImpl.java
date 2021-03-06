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
import com.yeepay.g3.core.activity.entity.ActivityInvForProInfo;
import com.yeepay.g3.core.activity.entity.ActivityInvForProRuleXT;
import com.yeepay.g3.core.activity.service.ActivityInvForProInfoService;
import com.yeepay.g3.facade.activity.dto.ActivityInvForProInfoDTO;
import com.yeepay.g3.facade.activity.enums.InvForProInfoStatusEnum;

/**
 * @Description 
 * @author hongbin.kang
 * @CreateTime 2016年7月27日 下午2:15:14
 * @version 1.0
 */
@Service
public class ActivityInvForProInfoServiceImpl implements ActivityInvForProInfoService {
	
    private static Log logger = LogFactory.getLog(ActivityInvForProInfoServiceImpl.class);

	@Autowired
	private ActivityInvForProInfoDao activityInvForProInfoDaoImpl;
	@Autowired
	private ActivityInvForProRuleXTDao activityInvForProRuleDaoImpl;

	@Override
	public ActivityInvForProInfo selectInvForProInfoById(long id) {
		
		return (ActivityInvForProInfo)activityInvForProInfoDaoImpl.queryOne("selectByPrimaryKey", id);
	}

	@Override
	public ActivityInvForProInfo selectAllInvForProInfoById(long id) {
		return (ActivityInvForProInfo)activityInvForProInfoDaoImpl.queryOne("selectAllByPrimaryKey", id);
	}
	@Override
	public void addActivityInvForProInfo(ActivityInvForProInfo activityInvForProInfo,String ruleIdStr) {
		activityInvForProInfoDaoImpl.add(activityInvForProInfo);
		if(null != ruleIdStr && !"".equals(ruleIdStr)&& !"null".equalsIgnoreCase(ruleIdStr)) {
			if(activityInvForProInfo.getId() != null) {
				String[] arry = null;
				if(ruleIdStr.indexOf("#") <0) {
					arry = new String[]{ruleIdStr};
				} else {
					arry = ruleIdStr.split("#");
				}
				if(null != arry) {
					for(String ruleId : arry) {
						ActivityInvForProRuleXT invForProRule = (ActivityInvForProRuleXT) activityInvForProRuleDaoImpl.queryOne("selectByPrimaryKey", Long.valueOf(ruleId));
						invForProRule.setProductId(activityInvForProInfo.getId());
						activityInvForProRuleDaoImpl.update(invForProRule);
					}
				}
			}
		}
		
	}

	@Override
	public List<ActivityInvForProInfo> selectListByParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityInvForProInfo> selectEffInvForProInfoList(Map<String, Object> params) {
		List<ActivityInvForProInfo> InvForProInfoList = activityInvForProInfoDaoImpl.query("selectByStatus", params);
		return InvForProInfoList;
	}

	@Override
	public List<ActivityInvForProInfo> selectByEventCode(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateActivityInvForProAndRule(ActivityInvForProInfo activityInvForProInfo,String ruleIdStr) {
		activityInvForProInfoDaoImpl.update(activityInvForProInfo);
		//原有的关联id置为0，取消关联
		List<ActivityInvForProRuleXT> oldList = activityInvForProRuleDaoImpl.query("selectRuleByProductId",activityInvForProInfo.getId());
		for(ActivityInvForProRuleXT invForProOldRule : oldList) {
			invForProOldRule.setProductId(Long.valueOf(0));
			activityInvForProRuleDaoImpl.update(invForProOldRule);
		}
		//增加选中的规则与产品的关联
		if(null != ruleIdStr && !"".equals(ruleIdStr)&& !"null".equalsIgnoreCase(ruleIdStr)) {
			if(activityInvForProInfo.getId() != null) {
				String[] arry = null;
				if(ruleIdStr.indexOf("#") <0) {
					arry = new String[]{ruleIdStr};
				} else {
					arry = ruleIdStr.split("#");
				}
				//增加关联
				if(null != arry) {
					for(String ruleId : arry) {
						ActivityInvForProRuleXT invForProRule = (ActivityInvForProRuleXT) activityInvForProRuleDaoImpl.queryOne("selectByPrimaryKey", Long.valueOf(ruleId));
						invForProRule.setProductId(activityInvForProInfo.getId());
						activityInvForProRuleDaoImpl.update(invForProRule);
					}
				}
			}
		}
		
	}

	@Override
	public void updateActivityInvForProStatusById(
			ActivityInvForProInfo activityInvForProInfo) {
		//有效直接跟新，无效则释放规则关联
		if(activityInvForProInfo.getStatus().equals(InvForProInfoStatusEnum.EFFECTIVE)) {
			activityInvForProInfoDaoImpl.update(activityInvForProInfo);
		} else if(activityInvForProInfo.getStatus().equals(InvForProInfoStatusEnum.RETURN_BACK)) {
			activityInvForProInfoDaoImpl.update(activityInvForProInfo);
			//原有的关联id置为0，取消关联
			List<ActivityInvForProRuleXT> oldList = activityInvForProRuleDaoImpl.query("selectRuleByProductId",activityInvForProInfo.getId());
			for(ActivityInvForProRuleXT invForProOldRule : oldList) {
				invForProOldRule.setProductId(Long.valueOf(0));
				activityInvForProRuleDaoImpl.update(invForProOldRule);
			}
		}
		
	}

	
	

}
