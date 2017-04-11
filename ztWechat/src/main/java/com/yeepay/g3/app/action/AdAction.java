/**
 * @author 陈大涛
 * 2016-8-3下午5:51:47
 */
package com.yeepay.g3.app.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yeepay.g3.app.dto.AppAdDTO;
import com.yeepay.g3.app.dto.PersonalDTO;
import com.yeepay.g3.app.enums.ErrorCodeTypeEnum;
import com.yeepay.g3.app.enums.ResultCodeEnum;
import com.yeepay.g3.app.lmweChat.action.BaseAction;
import com.yeepay.g3.app.lmweChat.biz.AdBiz;
import com.yeepay.g3.app.lmweChat.utils.GetParamUtils;
import com.yeepay.g3.app.lmweChat.utils.JSONObjectUtils;
import com.yeepay.g3.facade.activity.dto.ActivityAppAdDTO;
import com.yeepay.g3.facade.activity.facade.ActivityAppAdFacade;
import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import com.yeepay.g3.utils.web.emvc.annotation.Param;

/**
 * @Description
 * @author 陈大涛
 * 2016-8-3下午5:51:47
 */
@Controller
public class AdAction extends BaseAction {

	@Autowired
	private AdBiz adBizImpl;
	
	private ActivityAppAdFacade activityAppAdFacade = RemoteServiceFactory
			.getService(ActivityAppAdFacade.class);
			
/*	private ActivityAppAdFacade activityAppAdFacade = RemoteServiceFactory
					.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityAppAdFacade.class);*/
	
	/**
	 * 查询广告
	 * @author 陈大涛
	 * 2016-8-3下午4:05:49
	 */
	public  String queryAd(){
		try {
			AppAdDTO result = adBizImpl.queryAd();
			setJsonModel(result);
		} catch (Exception e) {
			logger.error("查询广告异常：", e);
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
			AppAdDTO result = new AppAdDTO();
			result.setErrorCode(error[0]);
			result.setErrorMsg(error[1]);
			result.setResultCode(ResultCodeEnum.FAILED);
			setJsonModel(result);
		}
		
		return "json";
	}
	
	/**
	 * 根据id查询广告图片
	 * @author 陈大涛
	 * 2016-8-3下午6:02:00
	 */
	public String queryImgAd(@Param("id") Long id){
		logger.info("[queryImgAd]  参数： id={}",id);
		//查询活动详情
		ActivityAppAdDTO  activityAppAdDTO = activityAppAdFacade.queryAppAdDetail(id);
		logger.info("[queryImgAd] activityAppAdDTO={}",activityAppAdDTO);
		try {
			HttpServletResponse response =getResponse();
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
		return "success";
	}
}
