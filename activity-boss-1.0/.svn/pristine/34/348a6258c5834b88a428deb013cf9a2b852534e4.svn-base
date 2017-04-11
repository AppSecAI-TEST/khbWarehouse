/**
 * @author 陈大涛
 * 2016-8-2下午5:12:30
 */
package com.yeepay.g3.boss.activity.controller.app;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yeepay.g3.boss.activity.controller.ActivityBaseController;
import com.yeepay.g3.facade.activity.dto.ActivityAppAdDTO;
import com.yeepay.g3.facade.activity.dto.ActivityInfoDTO;
import com.yeepay.g3.facade.activity.facade.ActivityAppAdFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;

/**
 * @Description app广告
 * @author 陈大涛
 * 2016-8-2下午5:12:30
 */
@Controller
@RequestMapping("/app")
public class AppController extends ActivityBaseController{
	private ActivityAppAdFacade activityAppAdFacade = RemoteServiceFactory
	.getService(ActivityAppAdFacade.class);
	
	/*private ActivityAppAdFacade activityAppAdFacade = RemoteServiceFactory
			.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityAppAdFacade.class);*/
	
	private Logger logger = LoggerFactory.getLogger(AppController.class);
	/**
	 * 查询app广告页列表
	 * @author 陈大涛
	 * 2016-8-2下午5:16:17
	 */
	@RequestMapping(value="/queryAppAd")
	public String queryAppAd(){
		return "app/queryAppAd";
	}
	
	/**
	 * 去新增app广告页
	 * @author 陈大涛
	 * 2016-8-2下午5:21:46
	 */
	@RequestMapping(value="/toAddAppAd")
	public String toAddAppAd(){
		return "app/addAppAd";
	}
	
	/**
	 * 新增app广告页
	 * @author 陈大涛
	 * 2016-8-2下午5:23:41
	 * @throws IOException 
	 */
	@RequestMapping(value="/addAppAd")
	public String addAppAd(@ModelAttribute ActivityAppAdDTO activityAppAdDTO,@RequestParam(value = "adImg", required = false) MultipartFile adImg,
			HttpSession session,HttpServletRequest request) throws IOException{
		logger.info("[addAppAd] 参数 ActivityActionDTO={}",activityAppAdDTO);
		String creator=getCurrentUser(session);
		activityAppAdDTO.setCretor(creator);
		if(!adImg.isEmpty()){
			String reg = ".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$";
			 Pattern pattern = Pattern.compile(reg);
			  Matcher matcher = pattern.matcher(adImg.getOriginalFilename());
			  boolean flag= matcher.matches();
			  activityAppAdDTO.setImg(adImg.getBytes());
		}
		activityAppAdFacade.insertAppAd(activityAppAdDTO);
		return "app/queryAppAd";
	}
	
	/**
	 * 去修改app广告页
	 * @author 陈大涛
	 * 2016-8-2下午5:21:46
	 */
	@RequestMapping(value="/toUpdateAppAd")
	public String toUpdateAppAd(@RequestParam(value="id",required=true) Long id,Model model){
		ActivityAppAdDTO activityAppAdDTO = activityAppAdFacade.queryAppAdDetail(id);
		model.addAttribute("activityAppAdDTO", activityAppAdDTO);
		return "app/updateAppAd";
	}
	
	/**
	 * 修改app广告页
	 * @author 陈大涛
	 * 2016-8-2下午5:23:41
	 */
	@RequestMapping(value="/updateAppAd")
	public String updateAppAd(@ModelAttribute ActivityAppAdDTO activityAppAdDTO,HttpSession session){
		String updator=getCurrentUser(session);
		activityAppAdDTO.setUpdator(updator);
		activityAppAdFacade.updateAppAd(activityAppAdDTO);
		return "app/queryAppAd";
	}
	
	/**
	 * 查询详情
	 * @author 陈大涛
	 * 2016-8-3上午9:36:05
	 */
	@RequestMapping(value="/queryAppAdDetail")
	public String queryAppAdDetail(@RequestParam(value="id",required=true) Long id,Model model){
		ActivityAppAdDTO activityAppAdDTO = activityAppAdFacade.queryAppAdDetail(id);
		model.addAttribute("activityAppAdDTO", activityAppAdDTO);
		return "app/appAdDetail";
	}
	/**
	 * 查看图片
	 * @author 陈大涛
	 * 2016-6-3下午7:17:43
	 */
	@RequestMapping(value="lookAppAdImg")
	public void lookAppAdImg(@RequestParam(required = true, value = "id") Long id,
			HttpServletRequest request,HttpServletResponse response){
		logger.info("[lookAppAdImg]  参数： id={}",id);
		//查询活动详情
		ActivityAppAdDTO activityAppAdDTO= activityAppAdFacade.queryAppAdDetail(id);
		logger.info("[lookAppAdImg] activityAppAdDTO={}",activityAppAdDTO);
		try {
			byte[] data = activityAppAdDTO.getImg();
			OutputStream out = response.getOutputStream();
			byte[] buff = new byte[(int)data.length ];
			int i=0;
			InputStream in=new ByteArrayInputStream(data);
			while((i= in.read(buff))!=-1){
				out.write(buff);
			}
			out.close();
			in.close();	
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
