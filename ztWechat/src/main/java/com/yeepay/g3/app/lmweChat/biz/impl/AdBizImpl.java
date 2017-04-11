/**
 * @author 陈大涛
 * 2016-8-3下午4:29:32
 */
package com.yeepay.g3.app.lmweChat.biz.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yeepay.g3.app.dto.AppAdDTO;
import com.yeepay.g3.app.dto.LoginResultDTO;
import com.yeepay.g3.app.enums.ErrorCodeTypeEnum;
import com.yeepay.g3.app.enums.LoginResultEnum;
import com.yeepay.g3.app.enums.ResultCodeEnum;
import com.yeepay.g3.app.lmweChat.biz.AdBiz;
import com.yeepay.g3.app.lmweChat.biz.UserBiz;
import com.yeepay.g3.app.lmweChat.utils.GetParamUtils;
import com.yeepay.g3.facade.activity.dto.ActivityAppAdDTO;
import com.yeepay.g3.facade.activity.facade.ActivityAppAdFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;

/**
 * @Description
 * @author 陈大涛
 * 2016-8-3下午4:29:32
 */
@Component
public class AdBizImpl implements AdBiz {
	
	@Autowired
	private UserBiz userBizImpl;
	
//	private ActivityAppAdFacade activityAppAdFacadeImpl = RemoteServiceFactory
//			.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityAppAdFacade.class);
	
	protected ActivityAppAdFacade activityAppAdFacadeImpl = RemoteServiceFactory.getService(ActivityAppAdFacade.class);
	
	
	private static final Logger logger = LoggerFactory.getLogger(CardBizImpl.class);

	@Override
	public AppAdDTO queryAd() {
				//统一配置取错误码
				ConfigParam<Map<String,String>> config = ConfigurationUtils.getConfigParam("config_type_text_resources", "lmapp_error_code");
				ConfigParam<Map<String,String>> urlConfig = ConfigurationUtils.getConfigParam("config_type_text_resources", "activity_app_ad_url");
				AppAdDTO appAdDTO = new AppAdDTO();
				ActivityAppAdDTO  activityAppAdDTO  = activityAppAdFacadeImpl.queryAppAdForApp();
				if(activityAppAdDTO.getId()==null){//广告页无效或过期
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_QUERY_AD_ZERO_NUM_EXCEPTION);	
					appAdDTO.setResultCode(ResultCodeEnum.FAILED);
					appAdDTO.setErrorCode(error[0]);
					appAdDTO.setErrorMsg(error[1]);
					appAdDTO.setUrl("");
					appAdDTO.setImgUrl("");
				}else{
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_SUCCESS);	
					appAdDTO.setResultCode(ResultCodeEnum.SUCCESS);
					appAdDTO.setErrorCode(error[0]);
					appAdDTO.setErrorMsg(error[1]);
					appAdDTO.setUrl(activityAppAdDTO.getUrl()==null?"":activityAppAdDTO.getUrl());
					appAdDTO.setImgUrl(urlConfig.getValue().get("imgUrl")+"?id="+activityAppAdDTO.getId());
					}
					return appAdDTO;
	}
	

}
