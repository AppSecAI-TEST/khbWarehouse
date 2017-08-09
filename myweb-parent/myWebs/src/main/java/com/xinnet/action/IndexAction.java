package com.xinnet.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinnet.annotation.NotLogin;
  
  
  
@Controller  
public class IndexAction extends BaseAction {  
	
	private static final Logger logger = LoggerFactory.getLogger(IndexAction.class);
	
    @RequestMapping("/index")
    @NotLogin
    public String toIndex(){  
    	logger.info("welcome to my Web");
        return jsp("index");  
    }  
    
    @RequestMapping("/toAbout")
    @NotLogin
    public String toAbout(){  
    	logger.info("welcome to my about");
        return jsp("about");  
    } 
}
