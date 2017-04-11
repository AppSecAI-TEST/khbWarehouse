package com.yeepay.g3.app.lmweChat.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.yeepay.g3.app.dto.LoginResultDTO;
import com.yeepay.g3.app.enums.LoginResultEnum;
import com.yeepay.g3.app.lmweChat.biz.UserBiz;
import com.yeepay.g3.app.lmweChat.biz.impl.UserBizImpl;
import com.yeepay.g3.app.lmweChat.utils.HttpRequestUtils;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.web.emvc.EmvcActionInvocation;
import com.yeepay.g3.utils.web.emvc.EmvcInterceptor;

@Component
public class LoginInterceptor implements EmvcInterceptor{
	private static final Logger logger = LoggerFactory
			.getLogger(LoginInterceptor.class);
	private UserBiz userBizImpl;
	@Override
	public String intercept(EmvcActionInvocation invocation) throws Exception {
//		logger.info("[LoginInterceptor] info={}","登录拦截...");
		try {
			//待写入登录拦截相关逻辑处理代码
			logger.debug("[LoginInterceptor] info={}","获取session...");
			HttpSession session=invocation.getMVCFacade().getRequest().getSession();
			MemberDto memberDto = (MemberDto) session.getAttribute("member");
			logger.debug("[LoginInterceptor] memberDto={}",memberDto);
			logger.debug("platform={}",session.getAttribute("platform"));
			
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
						logger.debug("[LoginInterceptor] actionName={}",invocation.getActionConfig().getName());
						//验证APP端是否登录
						LoginResultDTO loginResultDto=userBizImpl.obtainLogin(loginName,srcNo,userSessionKey);
						logger.info("loginResultDto : " + loginResultDto);
						//YSE－存储会员信息到session
						if(null!=loginResultDto&&loginResultDto.getResultMsg()==LoginResultEnum.SUCCESS) {
							//增加APP端的标识
							if(null==session.getAttribute("platform")){
								session.setAttribute("platform", "APP");
							}
							session.setAttribute("member", loginResultDto.getMemberDto());
							return invocation.invoke();
						} else{
							HttpServletRequest request = invocation.getMVCFacade().getRequest();
							logger.info("[LoginInterceptor] returnUrl={}",HttpRequestUtils.buildReturnUrl(request));
							invocation.getMVCFacade().getResponse().sendRedirect(request.getScheme() + "://"
									+ request.getServerName() + ":" + request.getServerPort()
									+ request.getContextPath() + "/account/toLogin?returnUrl="
									+ HttpRequestUtils.buildReturnUrl(request));
							return null;
						}
					} else{
						HttpServletRequest request = invocation.getMVCFacade().getRequest();
						invocation.getMVCFacade().getResponse().sendRedirect(request.getScheme() + "://"
								+ request.getServerName() + ":" + request.getServerPort()
								+ request.getContextPath() + "/account/toLogin?returnUrl="
								+ HttpRequestUtils.buildReturnUrl(request));
						return null;
					}
				} else{
					HttpServletRequest request = invocation.getMVCFacade().getRequest();
					logger.info("[LoginInterceptor] returnUrl={}",HttpRequestUtils.buildReturnUrl(request));
					invocation.getMVCFacade().getResponse().sendRedirect(request.getScheme() + "://"
							+ request.getServerName() + ":" + request.getServerPort()
							+ request.getContextPath() + "/account/toLogin?returnUrl="
							+ HttpRequestUtils.buildReturnUrl(request));
					return null;
				}
			}else{
				logger.info("[LoginInterceptor] returnUrl={}",memberDto.getLoginName()+"未被登录拦截");
				return invocation.invoke();

			}
		
			
		} catch (Exception e) {
			logger.error("登录拦截异常" , e);
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
