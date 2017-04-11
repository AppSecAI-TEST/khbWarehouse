package appTest;

/**
 * @author 陈大涛
 * 2016-6-1下午1:35:23
 */

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yeepay.g3.app.action.AboutUsAction;
import com.yeepay.g3.app.dto.AppVersionDTO;
import com.yeepay.g3.app.dto.UserOpeDTO;
import com.yeepay.g3.app.enums.ErrorCodeTypeEnum;
import com.yeepay.g3.app.enums.ResultCodeEnum;
import com.yeepay.g3.app.lmweChat.biz.SendMsgCodeBiz;
import com.yeepay.g3.app.lmweChat.biz.UserBiz;
import com.yeepay.g3.app.lmweChat.utils.GetParamUtils;
import com.yeepay.g3.app.lmweChat.utils.JSONObjectUtils;
import com.yeepay.g3.facade.activity.dto.ActivityPrizeDTO;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;

/**
 * @author 陈大涛
 * 2016-6-1下午1:35:23
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:lmweChat-app-spring/lmweChat-application.xml"})  
public class SendCodeTest {
	
	@Resource
	private SendMsgCodeBiz sendMsgCodeBizImpl;
	
	@Resource
	private UserBiz userBizImpl;
	
	
	  @Before
	    public void setUp() throws Exception {
//		  	ConfigurationUtils.init();
//			RemoteServiceFactory.init();
	    }
//	  @Test
	  public void testSendCode() throws Exception {
		  String srcNo="0001";
		  String pwd = "liuying123";
		  String loginName="18844195616";
		  String loginPwd = "liuying123";
		  String msgCode = "123123";
		  String deviceType = "ANDROID";
		  String macAddress = "fe80-sd-sdf-sdf-adsf-s-s-sa";
		  String ip = "172.19.62.33";
		  String userAgent = "172.19.62.33 - - [01/Aug/2016:00:00:19 +0800]";
		  String imei = "243 hbrjff ";
		  String mobileNo="13844195616";
//		  String openId="";
		  String opeType="REGISTER";
//		  UserOpeDTO result = userBizImpl.login(loginName, pwd, srcNo);
//		  UserOpeDTO result = sendMsgCodeBizImpl.sendMsgCode(srcNo, loginName, mobileNo, opeType);
		  UserOpeDTO result = userBizImpl.register(srcNo, mobileNo, loginPwd, msgCode, deviceType, macAddress, ip, userAgent, imei);
		  System.out.println(result);
	  }
	  
//	  @Test
	  public void testLogin() throws Exception{
		  String srcNo="0001";
		  String pwd = "liuying123";
		  String loginName="18844195616";
		  String loginPwd = "liuying123";
//		  UserOpeDTO result = userBizImpl.login(loginName, pwd, srcNo);
//		  System.out.println(result);
	  }
//	  @Test
	  public void testErrorCode() throws Exception{
		  String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_ADD_USERSUGGESTION_ERROR);
		  System.out.println(error[0]);
		  System.out.println(error[1]);
	  }
//	  @Test
	  public void testGetAppVersion() throws Exception{
		  AppVersionDTO appVersionDto = new AppVersionDTO();
			try{
				Map<String,String> map = (Map<String, String>) ConfigurationUtils.getConfigParam("config_type_text_resources", "lmapp_android_version").getValue();
				appVersionDto.setAppVersion(map.get("appVersion"));
				appVersionDto.setApkUrl(map.get("apkUrl"));
			}catch(Exception e){
				appVersionDto.setResultCode(ResultCodeEnum.FAILED);
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
				appVersionDto.setErrorCode(error[0]);
				appVersionDto.setErrorMsg(error[1]);
				String reqResult = JSONObjectUtils.dtoToJSON(appVersionDto);
				System.out.println(reqResult);
				return;
			}
			appVersionDto.setResultCode(ResultCodeEnum.SUCCESS);
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_SUCCESS);
			appVersionDto.setErrorCode(error[0]);
			appVersionDto.setErrorMsg(error[1]);
			String reqResult = JSONObjectUtils.entityToJSON(appVersionDto);
			System.out.println(reqResult);
	  }
}
