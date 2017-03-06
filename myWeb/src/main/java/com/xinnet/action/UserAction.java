package com.xinnet.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinnet.entity.User;
import com.xinnet.service.IUserService;
  
  
  
@Controller  
@RequestMapping("/user")  
public class UserAction extends BaseAction {  
	
	private static final Logger logger = LoggerFactory.getLogger(UserAction.class);
	
	@Autowired 
    private IUserService userService;   
	
	
    @RequestMapping("/showUser")
    public String toIndex(Model model){  
    	User user = new User();
    	user.setPassWord("1111");
    	user.setUserName("康洪彬");
    	logger.info("用户的实体", user);
    	logger.info(user.toString());
    	try {
			logger.info(checkNull(user));
			checkNull(user);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	try {
    		logger.info("验证判空");
    		userService.add(user);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			model.addAttribute("message", e.getMessage());  
			return jsp("error");
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("接口调用出错");
		} 
    	logger.info("验证通过");
        model.addAttribute("user", user);  
        return jsp("user/userInfo");  
    }  
}
