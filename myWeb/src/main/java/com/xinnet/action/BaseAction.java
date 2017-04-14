package com.xinnet.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.xinnet.annotation.NotNull;
import com.xinnet.entity.User;


public class BaseAction {
	
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
	 * 返回html页面
	 * @author hongbin.kang
	 * @date 2016年9月2日 下午10:52:40
	 * @param tpl 返回页面的地址，返回html
	 * @return
	 */
	public String html(String tpl) {
		return tpl+".html";
	}
	
	/**
	 * 返回jsp页面
	 * @author hongbin.kang
	 * @date 2016年9月2日 下午10:52:44
	 * @param tpl 返回页面的地址，返回jsp
	 * @return
	 */
	public String jsp(String tpl) {
		return tpl+".jsp";
	}
	
	/**
	 * 返回ajax状态和数据
	 * @author hongbin.kang
	 * @date 2016年9月4日 下午11:26:18
	 * @param jsonString = new jsonobject.toString()
	 * @return
	 */
	public String ajax(String jsonString){
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8"); 
		try {
			PrintWriter out = response.getWriter();
			out.print(jsonString);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 页面重定向
	 * @author hongbin.kang
	 * @date 2016年9月5日 上午10:18:02
	 * @param uri 重定向地址
	 * @return
	 */
	public String redirect(String uri) {
		return "redirect:" + uri;
	}

	/**
	 * 内部跳转
	 * @author hongbin.kang
	 * @date 2016年9月5日 上午10:18:19
	 * @param uri 跳转地址
	 * @return
	 */
	public String forward(String uri) {
		return "forward:" + uri;
	}
	
	/**
	 * 判断属性是否为空
	 * @author hongbin.kang
	 * @date 2017年1月23日 上午11:11:07
	 * @param obj
	 * @return
	 * @throws IllegalAccessException
	 */
	public String checkNull(Object obj) throws IllegalAccessException {
    	Class clazz = obj.getClass();
    	Field[] fields = clazz.getDeclaredFields();
    	
    	for(Field field : fields) {
    		NotNull notNull = field.getAnnotation(NotNull.class);
    		if(notNull != null){
    			field.setAccessible(true);
    			Object value =  field.get(obj);
    			if(value == null) {
    				return field.getName() + "不能为空";
    			}
    			String type = field.getType().toString();
    			if(type.endsWith("String")) {
    				if(((String)value).length()==0){
    					return field.getName() + "不能为空";
    				}
    			}
    		}
    	}
    	return null;
	}

}
