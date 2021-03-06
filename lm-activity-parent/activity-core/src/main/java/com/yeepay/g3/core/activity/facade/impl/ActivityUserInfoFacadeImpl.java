/**
 * @author hongbin.kang
 * @date 2016-6-1
 * @time 下午4:10:41
 */
package com.yeepay.g3.core.activity.facade.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityPrize;
import com.yeepay.g3.core.activity.entity.ActivityUserInfo;
import com.yeepay.g3.core.activity.service.ActivityUserInfoService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.core.activity.utils.WxUtil;
import com.yeepay.g3.facade.activity.dto.ActivityUserInfoDTO;
import com.yeepay.g3.facade.activity.dto.ActivityUserPrizeDTO;
import com.yeepay.g3.facade.activity.facade.ActivityUserInfoFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;



/**
 * @author hongbin.kang
 *
 */
@Service
public class ActivityUserInfoFacadeImpl implements ActivityUserInfoFacade {
	
	@Autowired
	private ActivityUserInfoService ActivityUserInfoServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityUserInfoFacadeImpl.class);

	@Override
	public ActivityUserInfoDTO selectUserInfoByMemberNo(String memberNo) {
		
		ActivityUserInfoDTO userInfoDto = new ActivityUserInfoDTO();
		ActivityUserInfo activityUserInfo = ActivityUserInfoServiceImpl.selectUserInfoByMemberNo(memberNo);
		userInfoDto = EntityDTOConvert.toTarget(activityUserInfo, ActivityUserInfoDTO.class);
		return userInfoDto;
	}

	@Override
	public void updateActivityUserInfoById(ActivityUserPrizeDTO userPrizeDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyUserInfoByWX(ActivityUserInfoDTO userInfoDto, String openId) {
		Map<String,Object> resultMap = queryWXUserInfo(openId);
		if(null == resultMap.get("openid")) {
			logger.info("返回用户信息异常：errcode={}，errmsg={}", resultMap.get("errcode"),resultMap.get("errmsg"));
		} else{
			ActivityUserInfo userInfo = EntityDTOConvert.toTarget(userInfoDto,ActivityUserInfo.class);
			ActivityUserInfoServiceImpl.updateUserInfoWX(userInfo,resultMap);
		}
		
	}

	@Override
	public Map<String, Object> queryWXUserInfo(String openId) {
		WxUtil wxUtil = new WxUtil();
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap = wxUtil.getWxUserInfo(openId);
		} catch (Exception e) {
			logger.info("查询用户信息异常",e.getMessage());
		}
		return resultMap;
	}
	
}
