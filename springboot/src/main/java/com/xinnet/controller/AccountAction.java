package com.xinnet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinnet.annotation.NotLogin;

@Controller
@RequestMapping("account")
public class AccountAction extends BaseController  {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountAction.class);
	
	@RequestMapping("toLogin")
	@NotLogin
	public String toLogin(String returnUrl,ModelMap model) {
		if(StringUtils.isEmpty(returnUrl)) {
			returnUrl = request.getContextPath()+"/index";
		}
		model.put("returnUrl", returnUrl);
		return "account/login";
	}
}
