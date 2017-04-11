package com.yeepay.g3.boss.activity.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.yeepay.g3.boss.activity.utils.DataFormater;
import com.yeepay.g3.utils.common.MessageFormater;
import com.yeepay.g3.utils.config.TextResource;


/**
 * 
 * @Description 优惠券controller基类
 * @author zhenping.zhou
 * @CreateTime 2016年3月28日 上午11:35:11
 * @version 1.0
 */
public class ActivityBaseController {

	protected final TextResource textResource = new TextResource();

	protected final MessageFormater messageFormater = new MessageFormater();

	protected final DataFormater dataFormater = new DataFormater();

	@ModelAttribute("_textResource")
	public TextResource getTextMap() {
		return textResource;
	}

	@ModelAttribute("_messageFormater")
	public MessageFormater getMessageFormater() {
		return messageFormater;
	}

	@ModelAttribute("_dataFormater")
	public DataFormater getDataFormater() {
		return dataFormater;
	}

	/**
	 *  获取操作员
	 * @param @param session
	 * @return String  
	 */
	protected String getCurrentUser(HttpSession session) {
		return  (String)session.getAttribute("yeepay_sso_session_userid");
	}
	
}
