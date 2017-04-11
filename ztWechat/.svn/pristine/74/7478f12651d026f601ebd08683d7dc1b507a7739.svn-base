package com.yeepay.g3.app.lmweChat.biz;

import javax.servlet.http.HttpServletRequest;

import com.yeepay.g3.app.dto.CardTypeDTO;
import com.yeepay.g3.app.dto.UserOpeDTO;

/**
 * @Description 用户绑卡、查询等请求
 * @author hongbin.kang
 * @CreateTime 2016年8月1日  15:43:41
 * @version 1.0
 */
public interface CardBiz {

	UserOpeDTO addBankCard(String realName, String idCard, String cardNo,
			String tel, String identifyCode, String tradePwd, String srcNo,
			String loginName, String macAddress, String ip, String userAgent,
			String imei, String deviceType, String userSessionKey);
	
	CardTypeDTO verifyCardInfo(String cardNo);
	
	UserOpeDTO verTradePwd(String loginName,String srcNo,String userSessionKey,String tradePwd);
	
}
