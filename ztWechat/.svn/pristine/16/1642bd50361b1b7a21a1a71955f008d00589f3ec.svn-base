package com.yeepay.g3.app.lmweChat.action.focus;

import org.apache.commons.lang.StringUtils;

import com.yeepay.g3.app.lmweChat.action.BaseAction;
import com.yeepay.g3.utils.web.emvc.annotation.Param;

public class FocusAction extends BaseAction {
	/**
	 * 跳转关于我们首页
	 * 
	 * @return
	 */
	public String toFocus() {
		return "success";
	}

	/**
	 * 根据参数跳转不同关于我们的页面
	 * 
	 * @return
	 */
	public String toAboutUs(@Param("toWhere") String toWhere) {
		if (StringUtils.isEmpty(toWhere))
			return SYSTEM_EXCEPTION;
		else
			return toWhere;
	}
	
	/**
	 * 跳转联系客服
	 * 
	 * @return
	 */
	public String toContactService() {
		return "success";
	}
	

}
