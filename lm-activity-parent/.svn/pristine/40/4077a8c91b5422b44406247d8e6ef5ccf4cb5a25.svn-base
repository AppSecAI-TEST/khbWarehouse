/**
 * @author hongbin.kang
 * @date 2016-6-1
 * @time 下午4:26:30
 */
package com.yeepay.g3.core.activity.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.dao.ActivityUserInfoDao;
import com.yeepay.g3.core.activity.entity.ActivityUserInfo;
import com.yeepay.g3.core.activity.entity.ActivityUserScoreRecord;
import com.yeepay.g3.core.activity.service.ActivityUserInfoService;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

/**
 * @author hongbin.kang
 *
 */
@Service
public class ActivityUserInfoServiceImpl implements ActivityUserInfoService {

	@Autowired
	private ActivityUserInfoDao activityUserInfoDaoImpl;
	
	Logger logger = LoggerFactory.getLogger(ActivityUserInfoServiceImpl.class);
	
	@Override
	public ActivityUserInfo selectUserInfoByMemberNo(String memberNo) {
		logger.info("[selectUserInfoByMemberNo] memberNo={}",memberNo);
		ActivityUserInfo userInfo = new ActivityUserInfo();
		userInfo = (ActivityUserInfo) activityUserInfoDaoImpl.queryOne("selectUserInfoByMemberNo", memberNo);
		logger.info("[selectUserInfoByMemberNo] userInfo={}",userInfo);
		if(null == userInfo) {
			//用户没有初始化
			updateOrInitUserScore(null,memberNo,0);
			userInfo = (ActivityUserInfo) activityUserInfoDaoImpl.queryOne("selectUserInfoByMemberNo", memberNo);
			logger.info("[selectUserInfoByMemberNo] after updateOrInitUserScore userInfo={}",userInfo);
		}
		return userInfo;
	}

	@Override
	public void updateOrInitUserScore(ActivityUserInfo userInfo, String memberNo, int bindCardScore) {
		logger.info("[updateOrInitUserScore] userInfo={},memberNo={},bindCardScore={}",userInfo,memberNo,bindCardScore);
		// TODO Auto-generated method stub
		if(userInfo == null) {
			userInfo = new ActivityUserInfo();
			userInfo.setMemberNo(memberNo);
			userInfo.setTotalScore(bindCardScore);
			activityUserInfoDaoImpl.add(userInfo);
			logger.info("[updateOrInitUserScore] add userInfo={}",userInfo);
		} else {
			ActivityUserInfo updateUserInfo = new ActivityUserInfo();
			updateUserInfo.setId(userInfo.getId());
			updateUserInfo.setVersion(userInfo.getVersion());
			updateUserInfo.setTotalScore(userInfo.getTotalScore() + bindCardScore);
			
			activityUserInfoDaoImpl.update(updateUserInfo);
			
		}
	}

	@Override
	public void updateUserInfoWX(ActivityUserInfo userInfo,
			Map<String, Object> resultMap) {
		userInfo.setOpenId(resultMap.get("openid").toString());
		userInfo.setWxNickName(resultMap.get("nickname") == null ? "" : resultMap.get("nickname").toString());
		userInfo.setWxSex(Integer.valueOf(resultMap.get("sex") == null ? "0" : resultMap.get("sex").toString()));
		userInfo.setWxProvince(resultMap.get("province") == null ? "" : resultMap.get("province").toString());
		userInfo.setWxCity(resultMap.get("city") == null ? "" : resultMap.get("city").toString());
		userInfo.setWxCountry(resultMap.get("country") == null ? "" : resultMap.get("country").toString());
		userInfo.setWxHeadUrl(resultMap.get("headimgurl") == null ? "" : resultMap.get("headimgurl").toString());
		userInfo.setWxSubscribe(Integer.valueOf(resultMap.get("subscribe") == null ? "0" : resultMap.get("subscribe").toString()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			userInfo.setWxSubscribeTime(sdf.parse(sdf.format(new Date(Long.valueOf(resultMap.get("subscribe_time").toString()+"000")))));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userInfo.setUnionId(resultMap.get("unionid") == null ? "" : resultMap.get("unionid").toString());
		userInfo.setWxRemark(resultMap.get("remark") == null ? "" : resultMap.get("remark").toString());
		userInfo.setWxGroupId(Long.valueOf(resultMap.get("groupid") == null ? "0" : resultMap.get("groupid").toString()));
		activityUserInfoDaoImpl.update(userInfo);
	}
	

}
