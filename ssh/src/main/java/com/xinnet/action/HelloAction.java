package com.xinnet.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

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
	 
	
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
					throws Exception{
		List<UserInfo> userInfos = userInfoService.findAll();
		request.setAttribute("list",userInfos);
		return mapping.findForward("list");
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		LOGGER.info("查看用户详情：" + id);
		UserInfo userInfo = userInfoService.get(Integer.valueOf(id));
//		AjaxUtil.ajaxJSONResponse(userInfo);
		request.setAttribute("user",userInfo);
		return mapping.findForward("detail");

	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("edit");

	}
	@SuppressWarnings("unchecked")
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<UserInfo> list = new ArrayList<UserInfo>();
		Integer num = Integer.valueOf(request.getParameter("num"));
		for(int i = 0; i< num; i++ ) {
			UserInfo user = new UserInfo();
			user.setName(request.getParameter("users["+i+"].name"));
			user.setAge(Integer.valueOf(request.getParameter("users["+i+"].age")));
			list.add(user);
		}
		
		
		for(UserInfo user : list) {
			userInfoService.save(user);
		}
		return mapping.findForward("success");

	}
	
	
}