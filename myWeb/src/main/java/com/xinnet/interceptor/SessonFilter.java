package com.xinnet.interceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessonFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(SessonFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        //empty implement
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String uri = httpRequest.getRequestURI();
        String ctxpath = httpRequest.getContextPath();

        String userid = (String)session.getAttribute("userid");

        if(uri.contains("/user/login") || uri.contains("/user/regPage") ){
            chain.doFilter(request, response);
        }else{  
            if(userid == null){
                String contextPath = httpRequest.getContextPath();
                String redirect =  contextPath ;
                //ajax session 过期处理
                //1:判断是否是ajax请求
                if (httpRequest.getHeader("x-requested-with") != null 
                        && "XMLHttpRequest".equalsIgnoreCase(httpRequest.getHeader("x-requested-with"))) {   
                    //向http头添加 状态 sessionstatus
                    httpResponse.setHeader("sessionstatus","timeout");
                    httpResponse.setStatus(403);
                    //向http头添加登录的url
                    httpResponse.addHeader("loginPath", ctxpath);
                    chain.doFilter(request, response);
                    logger.debug("ajax request");
                    return ;
                }
                httpResponse.sendRedirect(redirect);
                return;
            }else{
                chain.doFilter(request, response);
            }
        }
    }

    public void destroy() {
        //empty implement
    }
}