package com.yeepay.g3.app.lmweChat.service;

import java.util.Map;

import com.yeepay.g3.utils.config.ConfigurationUtils;

public interface RequestParamBuilderService {

	public static final String APP_ID = ConfigurationUtils.getSysConfigParam("lanmaowx_appid").getValue().toString();
	public static final String APP_SECRET = ConfigurationUtils.getSysConfigParam("lanmaowx_appsecret").getValue().toString();
//	public static final String APP_ID = "wx196f101900ebc50b";
//	public static final String APP_SECRET = "d4624c36b6795d1d99dcf0547af5443d";
	public Map<String,String> buildGetWebAccessTokenParam(String code);
	
	/**
	 * 获取线下理财推荐人对应的链接
	 * @param actUrl
	 * @param memberNo
	 * @return
	 */
	public String getConsultantUserUrlParam(String actUrl, String memberNo);
}
