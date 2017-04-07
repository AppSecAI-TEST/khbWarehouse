package com.xinnet.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseController {
	protected static Log logger = LogFactory.getLog(BaseController.class);
	protected ObjectMapper mapper = new ObjectMapper();
	
	protected HttpServletRequest request;  
    protected HttpServletResponse response; 
    protected HttpSession session;
      
    /**
     * 写入请求的resquest和response
     * @author hongbin.kang
     * @date 2016年9月4日 下午11:13:23
     * @param request
     * @param response
     */
    @ModelAttribute  
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;  
        this.response = response;  
        this.session = request.getSession();
    }
	
	
	/**
	 * 打印json格式
	 * 
	 * @param object
	 * @param response
	 */
	public void printJson(Object object, HttpServletResponse response) {
		OutputStream out = null;
		try {
			StringWriter stringWriter = new StringWriter();
			mapper.writeValue(stringWriter, object);
			response.setContentType("text/html;charset=utf-8");
			out = response.getOutputStream();
			out.write(stringWriter.toString().getBytes("UTF-8"));
		} catch (IOException e) {
			logger.error(e);
		} finally {
			if (null != out) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
	}
}
