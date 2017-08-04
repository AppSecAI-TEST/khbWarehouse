package com.xinnet.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.swing.JOptionPane;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xinnet.annotation.NotLogin;
import com.xinnet.annotation.UserHolder;
import com.xinnet.entity.Order;
import com.xinnet.entity.User;
import com.xinnet.reids.Redis;
import com.xinnet.service.IOrderService;
import com.xinnet.service.IUserService;
import com.xinnet.service.impl.UserServiceImpl;
import com.xinnet.utils.ExportExcelUtil;

@Controller
@RequestMapping("test")
/*@Scope("prototype")*/
public class TestAction extends BaseAction {
	
	private static final Logger logger = LoggerFactory.getLogger(TestAction.class);
	private static int st = 0;      //静态的
    private int index = 0;          //非静态
    
    @Autowired
    private UserHolder userHolder;
    @Autowired
    private IUserService userService;
    @Autowired
    private IOrderService orderServiceImpl;
    @Autowired
    private Redis redis;

	@RequestMapping("showList")
	@NotLogin
	public void showList(String id) throws IOException {
		String serverPath = request.getServletPath();
		logger.info("请求的action serverPath={}",serverPath);
		session.setAttribute("user", id);
//		response.sendRedirect("resultList");
		
		
        Map<String, Object> headMap = new HashMap<String, Object>();
        headMap.put("shuliang", "数量");
        headMap.put("type", "类型");
        headMap.put("sttue", "leno");
        headMap.put("yeshu", "页数");
        headMap.put("haoma", "出版时间");
        headMap.put("chubanshe", "出版社");
        List<Map<String, Object>> dataset2 = new ArrayList<Map<String, Object>>();  
        try  
        {  
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("shuliang", 1);
        	map.put("type", "jsp");
        	map.put("sttue", "leno");
        	map.put("yeshu", 300.34);
        	map.put("haoma", "2016-12-13 23:45:56");
        	map.put("chubanshe", "清华出版社");
        	Map<String, Object> map1 = new HashMap<String, Object>();
        	map1.put("shuliang", 2);
        	map1.put("type", "java编程思想");
        	map1.put("sttue", "brucl");
        	map1.put("yeshu", "3020.33f");
        	map1.put("haoma", new Date());
        	map1.put("chubanshe", "阳光出版社");
        	
        	dataset2.add(map);
        	dataset2.add(map1);
  
            OutputStream out2 = new FileOutputStream("E://c.xls");  
            ExportExcelUtil.download(null, response, headMap, dataset2);  
            out2.close();  
//            JOptionPane.showMessageDialog(null, "导出成功!");  
            System.out.println("excel导出成功！");  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    
		
		
		
//		return jsp("showList");
	}
	
	@RequestMapping("resultList")
	@NotLogin
	public String resultList() {
		String serverPath = request.getServletPath();
		logger.info("serverPath={},user={}",serverPath,session.getAttribute("user"));
		System.out.println(st++ + " | " + index++);
		return html("resultList");
	}
	
	@RequestMapping("info")
	@NotLogin
	public String innerParamTest(Model model){
		session = request.getSession();
		session.setAttribute("test", "session223");
		
		ServletContext application = request.getSession().getServletContext();
		// application.setInitParameter("appicationParam", "appicationParam");
		//Initialization parameters can not be set after the context has been initialized
		application.setAttribute("test", "applicationAttr");
		request.setAttribute("port", request.getServerPort());
		request.setAttribute("portPc", request.getLocalPort());
		request.setAttribute("test", "request");
		model.addAttribute("test", "model123");
		return jsp("testParam2");
	}
	
	@RequestMapping("creatOrder")
	@NotLogin
	public String creatOrder() {
		return jsp("order/creatOrder");
	}
	
	
	
	@RequestMapping("testOrder")
	@NotLogin
	public String doOrder() {
		JSONObject json = new JSONObject();
		Order order1 = new Order(UUID.randomUUID().toString(),1,BigDecimal.ZERO);
		try {
			orderServiceImpl.insertSelective(order1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String jsonString = json.toString();
		
		return ajax(jsonString);
	}
	
	@RequestMapping("/addUser")
    @NotLogin
    public String addUser(Model model){  
    	User user = new User();
    	user.setPassWord("1111");
    	user.setUserName("康洪彬");
    	user.setEmail("113255@qq.com");
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
    
    @RequestMapping("/showUser")
    @NotLogin
    public String showUser(Model model){  
    	User user = new User();
    	user.setPassWord("1111");
    	user.setUserName("康洪彬");
    	user.setEmail("113255@qq.com");
    	logger.info("用户的实体", user);
    	logger.info(user.toString());
    	
    	try {
    		logger.info("验证判空");
    		userService.getUserById(1);
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
