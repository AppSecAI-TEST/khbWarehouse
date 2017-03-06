package com.xinnet.action;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinnet.annotation.NotLogin;
import com.xinnet.entity.User;
import com.xinnet.service.IEmailService;
import com.xinnet.service.IUserService;

@Controller
@RequestMapping("account")
public class AccountAction extends BaseAction  {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountAction.class);
	@Autowired
	IUserService userServiceImpl;
	
	@Autowired
	IEmailService emailServiceImpl;
	
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
