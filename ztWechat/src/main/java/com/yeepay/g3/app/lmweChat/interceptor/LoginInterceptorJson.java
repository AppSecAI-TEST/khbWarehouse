package com.yeepay.g3.app.lmweChat.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.yeepay.g3.app.dto.LoginResultDTO;
import com.yeepay.g3.app.enums.LoginResultEnum;
import com.yeepay.g3.app.lmweChat.action.popularize.PopularizeAction;
import com.yeepay.g3.app.lmweChat.biz.UserBiz;
import com.yeepay.g3.app.lmweChat.biz.impl.UserBizImpl;
import com.yeepay.g3.app.lmweChat.utils.HttpRequestUtils;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.web.emvc.EmvcActionInvocation;
import com.yeepay.g3.utils.web.emvc.EmvcInterceptor;

@Component
public class LoginInterceptorJson implements EmvcInterceptor{
	
	private static final Logger logger = LoggerFactory
			.getLogger(LoginInterceptorJson.class);
	private UserBiz userBizImpl;
	@Override
	public String intercept(EmvcActionInvocation invocation) throws Exception {
		try {
			//待写入登录拦截相关逻辑处理代码
			HttpSession session=invocation.getMVCFacade().getRequest().getSession();
			MemberDto memberDto = (MemberDto) session.getAttribute("member");
			if(memberDto==null){
				
				//获取请求参数
			 String loginName=invocation.getMVCFacade().getRequest().getParameter("loginName");
			 String srcNo=invocation.getMVCFacade().getRequest().getParameter("srcNo");
			 String userSessionKey=invocation.getMVCFacade().getRequest().getParameter("userSessionKey");
			 String userAgent = invocation.getMVCFacade().getRequest().getHeader("User-Agent");
			 logger.info("LoginInterceptor.intercept, loginName={},srcNo={},userSessionKey={},userAgent={}",loginName,srcNo,userSessionKey,userAgent);
				
				if(StringUtils.isNotEmpty(userSessionKey)) {
					//过滤掉toFinishedContact方法，此方法在app端没有userAgent
					if((StringUtils.isNotEmpty(invocation.getActionConfig().getName()) &&"toFinishedContact".equals(invocation.getActionConfig().getName()))||StringUtils.isNotEmpty(userAgent) && userAgent.indexOf("lanmao") > -1) {
						//验证APP端是否登录
						LoginResultDTO loginResultDto=userBizImpl.obtainLogin(loginName,srcNo,userSessionKey);
						logger.info("loginResultDto : " + loginResultDto);
						//YSE－存储会员信息到session
						if(null!=loginResultDto&&loginResultDto.getResultMsg()==LoginResultEnum.SUCCESS) {
							session.setAttribute("member", loginResultDto.getMemberDto());
							return invocation.invoke();
						} else{
							HttpServletRequest request= invocation.getMVCFacade().getRequest();
							logger.info("[LoginInterceptor] returnUrl={}",HttpRequestUtils.buildReturnUrl(request));
							invocation.getMVCFacade().getResponse().sendRedirect(request.getScheme() + "://"
					                    + request.getServerName() + ":" + request.getServerPort()
					                    + request.getContextPath() + "/account/toLoginJson");
							return null;
						}
					} else{
						HttpServletRequest request= invocation.getMVCFacade().getRequest();
						logger.info("[LoginInterceptor] returnUrl={}",HttpRequestUtils.buildReturnUrl(request));
						invocation.getMVCFacade().getResponse().sendRedirect(request.getScheme() + "://"
				                    + request.getServerName() + ":" + request.getServerPort()
				                    + request.getContextPath() + "/account/toLoginJson");
						return null;
					}
				} else{
					HttpServletRequest request= invocation.getMVCFacade().getRequest();
					logger.info("[LoginInterceptor] returnUrl={}",HttpRequestUtils.buildReturnUrl(request));
					invocation.getMVCFacade().getResponse().sendRedirect(request.getScheme() + "://"
			                    + request.getServerName() + ":" + request.getServerPort()
			                    + request.getContextPath() + "/account/toLoginJson");
					return null;
				}
			}else{
				logger.info("[LoginInterceptorJson] info={}",memberDto.getLoginName()+"未被json登录拦截...");
				return invocation.invoke();
			}
		} catch (Exception e) {
			return "SYSTEM_EXCEPTION";
		}
	}
	public void setUserBizImpl(UserBizImpl userBizImpl){
		this.userBizImpl=userBizImpl;
	}
	public UserBiz getsetUserBizImpl(){
		return userBizImpl;
	}
}
