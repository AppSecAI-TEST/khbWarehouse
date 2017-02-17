package com.xinnet.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinnet.annotation.NotLogin;

@Controller
@RequestMapping("git")
public class GitTest extends BaseAction{

	@RequestMapping("test")
	@NotLogin
	public String test() {
		
		return jsp("git/testGit");
	}
}
