package com.yeepay.g3.app.action;

import java.util.Map;

import org.springframework.stereotype.Controller;

import com.yeepay.g3.app.dto.AppVersionDTO;
import com.yeepay.g3.app.enums.ErrorCodeTypeEnum;
import com.yeepay.g3.app.enums.ResultCodeEnum;
import com.yeepay.g3.app.lmweChat.action.BaseAction;
import com.yeepay.g3.app.lmweChat.utils.GetParamUtils;
import com.yeepay.g3.app.lmweChat.utils.JSONObjectUtils;
import com.yeepay.g3.utils.config.ConfigurationUtils;

/**
 * @Title: 关于我们
 * @Description: 关于我们
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-8-15 上午9:52:12
 * @version 2016-8-15
 */
@Controller
public class AboutUsAction extends BaseAction{
	
	public String toAboutUs(){
		return SUCCESS;
	}
	
	/**
	 * 获取最新版本号
	 * @param in -
	 * @return 版本号
	 */
	public String getVersion(){
		logger.info("[getVersion] info={}","获取app最新版本号");
		AppVersionDTO appVersionDto = new AppVersionDTO();
		try{
			Map<String,String> map = (Map<String, String>) ConfigurationUtils.getConfigParam("config_type_text_resources", "lmapp_android_version").getValue();
			logger.info("[getVersion] map={}",map);
			appVersionDto.setAppVersion(map.get("appVersion"));
			appVersionDto.setApkUrl(map.get("apkUrl"));
		}catch(Exception e){
			logger.error("[getVersion] ERROR={}",e);
			appVersionDto.setResultCode(ResultCodeEnum.FAILED);
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
			appVersionDto.setErrorCode(error[0]);
			appVersionDto.setErrorMsg(error[1]);
			String reqResult = JSONObjectUtils.dtoToJSON(appVersionDto);
			setJsonModel(reqResult);
			return SUCCESS;
		}
		appVersionDto.setResultCode(ResultCodeEnum.SUCCESS);
		String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_SUCCESS);
		appVersionDto.setErrorCode(error[0]);
		appVersionDto.setErrorMsg(error[1]);
		String reqResult = JSONObjectUtils.entityToJSON(appVersionDto);
		setJsonModel(reqResult);
		return SUCCESS;
	}

}
