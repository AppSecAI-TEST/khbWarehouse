package com.xinnet.service.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.xinnet.entity.UserInfo;
import com.xinnet.service.UserInfoService;
  
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {  
        "classpath:spring.xml",  
        "classpath:spring-hibernate.xml"  
})  
public class TestUserService {

	private static final Logger LOGGER = Logger
			.getLogger(TestUserService.class);

	@Autowired
	private UserInfoService userInfoService;

	@Test
	public void save() {
		UserInfo userInfo = new UserInfo();
		userInfo.setName("zty");
		userInfo.setAge(23);
		userInfo.setTelephone("1");
		Integer id = userInfoService.save(userInfo);
		JSON.toJSONString(id);
	}

}
