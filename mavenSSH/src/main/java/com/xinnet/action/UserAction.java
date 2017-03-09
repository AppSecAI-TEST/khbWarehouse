package com.xinnet.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.xinnet.entity.User;
import com.xinnet.service.UserManager;
  
@ParentPackage("struts-default")  
@Namespace("/user")  
@AllowedMethods("detail")  
@Results({  
    @Result(name = "success", location = "/user/user.jsp")  
})  
public class UserAction extends ActionSupport implements ModelDriven<User>, Preparable {  
    private static final long serialVersionUID = -4611010990125262639L;  
    private static final Logger LOGGER = Logger.getLogger(UserAction.class);  
    private Integer id;  
    User user;  
    List<User> userList;  
      
    @Autowired  
    private UserManager userManager;  
      
    public Integer getId() {  
        return id;  
    }  
      
    public void setId(Integer id) {  
        this.id = id;  
    }  
      
    public User getUser() {  
        return user;  
    }  
      
    public void setUser(User user) {  
        this.user = user;  
    }  
      
    public List<User> getUserList() {  
        return userList;  
    }  
      
    public void setUserList(List<User> userList) {  
        this.userList = userList;  
    }  
      
    @Override  
    public void prepare() throws Exception {  
          
    }  
  
    @Override  
    public User getModel() {  
        if (null != id) {  
            user = userManager.get(id);  
        } else {  
            user = new User();  
        }  
        return user;  
    }  
      
    @Override  
    public String execute() throws Exception {  
        LOGGER.info("查询所有用户");  
        userList = userManager.findAll();  
        return SUCCESS;  
    }  
  
    public void detail() throws IOException {  
        String id = ServletActionContext.getRequest().getParameter("id");  
        LOGGER.info("查询用户详情：" + id);  
        user = userManager.get(Integer.valueOf(id));  
        //AjaxUtil.ajaxJSONResponse(user);  
        HttpServletResponse response = ServletActionContext.getResponse();  
        PrintWriter out = response.getWriter();  
        out.println("Hello " + user.getUserName());  
    }  
  
}
