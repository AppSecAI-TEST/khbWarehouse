package com.yeepay.g3.app.lmweChat.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yeepay.g3.app.lmweChat.service.RequestParamBuilderService;
import com.yeepay.g3.utils.common.encrypt.Digest;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigurationUtils;
@Service
public class RequestParamBuilderServiceImpl implements
		RequestParamBuilderService {
	protected static Logger logger = LoggerFactory.getLogger(RequestParamBuilderServiceImpl.class);

	@Override
	public Map<String, String> buildGetWebAccessTokenParam(String code) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("appid",APP_ID );
		paramMap.put("secret", APP_SECRET);
		paramMap.put("grant_type", "authorization_code");
		paramMap.put("code", code);
		return paramMap;
	}

	@Override
	public String getConsultantUserUrlParam(String actUrl, String memberNo) {
		
		StringBuilder consultantUserUrl = new StringBuilder();
		try {

			String baseUrl = ConfigurationUtils.getSysConfigParam("lm_consultant_base_url").getValue().toString();
			String channelCode = ConfigurationUtils.getSysConfigParam("lm_consultant_channel_code").getValue().toString();
			// 签名，加密使用,需懒猫平台提供
			String signKey = ConfigurationUtils.getSysConfigParam("lm_consultant_quick_login_signkey").getValue().toString();
			// 请求url，测试环境与生产不一致
			String time = new Date().getTime() + "";
			String signStr = channelCode + memberNo + time + signKey;
			String md5sign = Digest.md5Digest(signStr);
			
			consultantUserUrl.append(baseUrl)
							 .append(actUrl)
							 .append("?source=").append(channelCode)
							 .append("&userId=").append(memberNo)
							 .append("&tist=").append(time)
							 .append("&sign=").append(md5sign);
			logger.info("consultantUserUrl is : " + consultantUserUrl);
		} catch(Exception e) {
			logger.error("计算线下理财顾问URL出错",e);
		}
		
		return consultantUserUrl.toString();
	}

}
