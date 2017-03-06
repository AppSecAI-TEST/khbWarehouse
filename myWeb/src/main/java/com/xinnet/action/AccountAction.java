package com.xinnet.action;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinnet.annotation.NotLogin;
import com.xinnet.entity.User;
import com.xinnet.service.IEmailService;
import com.xinnet.service.IUserService;
import com.xinnet.utils.CheckEmailAndPhoneUtils;
import com.xinnet.utils.EncryptUtils;

@Controller
@RequestMapping("account")
public class AccountAction extends BaseAction  {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountAction.class);
	@Autowired
	IUserService userServiceImpl;
	
	@Autowired
	IEmailService emailServiceImpl;
	
	@RequestMapping("toRegister")
	@NotLogin
	public String toRegister(String returnUrl,ModelMap model) {
		model.put("returnUrl", returnUrl);
		return jsp("account/register");
	}
	
	@RequestMapping("sendEmailCode")
	@NotLogin
	public String sendEmailCode(String email) {
		JSONObject json = new JSONObject();
		if(!CheckEmailAndPhoneUtils.checkEmail(email)) {
			json.put("code", "false");
			json.put("message", "输入格式不正确");
		} else {
			try {
				emailServiceImpl.sendEmailCode(email);
				json.put("code", "success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				json.put("code", "false");
				json.put("message", "系统异常");
			}
		}
		return ajax(json.toString());
	}
	
	@RequestMapping("register")
	@NotLogin
	public String register(@ModelAttribute User user,String code,ModelMap model, String returnUrl) {
		logger.info("User={}",user);
		
		
		userServiceImpl.add(user,code);
		model.put("returnUrl", returnUrl);
		return jsp("account/register");
	}
	
	@RequestMapping("toLogin")
	@NotLogin
	public String toLogin(String returnUrl,ModelMap model) {
		model.put("returnUrl", returnUrl);
		return html("login");
	}
	
	@RequestMapping("login")
	@NotLogin
	public void login(String returnUrl,ModelMap model,Integer id) {
		model.put("returnUrl", returnUrl);
		logger.info("userId={},returnUrl={}", id,returnUrl);
		User user = userServiceImpl.getUserById(id);
		logger.info("userDto={}", user.toString());
		session.setAttribute("User", user);
		try {
			response.sendRedirect(returnUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
