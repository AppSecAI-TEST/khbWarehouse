package com.xinnet.action;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinnet.annotation.NotLogin;
import com.xinnet.utils.DateUtil;

@Controller
@RequestMapping("git")
public class GitTest extends BaseAction{

	@RequestMapping("test")
	@NotLogin
	public String test() {
		
		return jsp("git/testGit");
	}
	
	@RequestMapping("testIP")
	@NotLogin
	public String testIP(ModelMap modelMap) {
		modelMap.put("time",DateUtil.format(new Date(),DateUtil.YYYYMMDDHHMMSS));
		modelMap.put("ip", request.getRemoteAddr());
		return jsp("git/testIP");
	}
}
