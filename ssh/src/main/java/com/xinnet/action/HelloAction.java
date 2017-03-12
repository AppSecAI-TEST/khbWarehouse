package com.xinnet.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.actions.DispatchAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xinnet.entity.UserInfo;
import com.xinnet.service.UserInfoService;
import com.xinnet.utils.SpringContextUtil;



/**
 * 创建时间：2015-2-13 下午2:49:22
 * 
 * @author andy
 * @version 2.2 描述： user的Action
 */
public class HelloAction extends DispatchAction  {
	
	
	private static final Logger LOGGER = Logger.getLogger(UserinfoAction.class);
	
	private UserInfoService userInfoService = (UserInfoService)SpringContextUtil.getBean("userInfoServiceImpl");
	/**
	 * 获得注册Bean     
	 * @param beanName String 注册Bean的名称
	 * @return
	 */
	/*protected final Object getBean(String beanName) {
		return ctx.getBean(beanName);
	}
	
	public void setServlet(ActionServlet servlet) {
		this.servlet = servlet;
		this.ctx = WebApplicationContextUtils.getWebApplicationContext(servlet.getServletContext());
		this.userInfoService = (UserInfoService) getBean("userInfoService");
	} */  
	
	
	
	/**
	 * web应用上下文环境变量
	 */
	protected WebApplicationContext ctx;
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
					throws Exception{
		List<UserInfo> userInfos = userInfoService.findAll();
		request.setAttribute("list",userInfos);
		return (mapping.findForward("list"));
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		LOGGER.info("查看用户详情：" + id);
		UserInfo userInfo = userInfoService.get(Integer.valueOf(id));
//		AjaxUtil.ajaxJSONResponse(userInfo);
		return mapping.findForward("datail");

	}
	
}