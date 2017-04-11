package appTest;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.HttpRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yeepay.g3.app.dto.CardTypeDTO;
import com.yeepay.g3.app.dto.UserOpeDTO;
import com.yeepay.g3.app.lmweChat.biz.CardBiz;
import com.yeepay.g3.app.lmweChat.biz.PersonalCenterBiz;
import com.yeepay.g3.app.lmweChat.biz.SendMsgCodeBiz;
import com.yeepay.g3.app.lmweChat.biz.UserBiz;
import com.yeepay.g3.app.lmweChat.utils.JSONObjectUtils;
import com.yeepay.g3.facade.activity.dto.ActivityPrizeDTO;
import com.yeepay.g3.utils.common.httpclient.SimpleHttpParam;
import com.yeepay.g3.utils.common.httpclient.SimpleHttpResult;
import com.yeepay.g3.utils.common.httpclient.SimpleHttpUtils;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:lmweChat-app-spring/lmweChat-application.xml"})  
public class BindCardTest {
	
	@Resource
	private PersonalCenterBiz personalCenterBiz;
	
	@Resource
	private CardBiz cardBiz;
	
	@Resource
	private UserBiz userBiz;
	
	  @Before
	    public void setUp() throws Exception {
//		  	ConfigurationUtils.init();
//			RemoteServiceFactory.init();
	    }
//	  @Test
	  public void testBindCard() throws Exception {
//		  String srcNo="0001";
//		  String pwd = "liuying123";
//		  String loginName="18844195616";
//		  String realName = "范国良";
//		  String idCard = "32010219820802591X";
//		  String cardNo = "6214623721000123450";
//		  String macAddress = "fe80-sd-sdf-sdf-adsf-s-s-sa";
//		  String ip = "172.19.62.33";
//		  String userAgent = "172.19.62.33 - - [01/Aug/2016:00:00:19 +0800]";
//		  String imei = "243 hbrjff ";
//		  String tel="13844195616";
//		  String identifyCode="123456";
//		  String tradePwd="345kang";
//		  UserOpeDTO result = userBiz.login(loginName, pwd, srcNo);
//		  UserOpeDTO result = sendMsgCodeBizImpl.sendMsgCode(srcNo, loginName, mobileNo, opeType);
//		  UserOpeDTO card = cardBiz.addBankCard(realName, idCard, cardNo, tel, identifyCode, tradePwd, srcNo, loginName, macAddress, ip, userAgent, imei);
//		  String json = JSONObjectUtils.entityToJSON(card);
//		  System.out.println(json);
			SimpleHttpParam param = new SimpleHttpParam("http://localhost:8080/lmweChat/personalCenter/addUserSuggestion");
			Map<String,String> headers = new HashMap<String,String>();
			headers.put("Connection", "close");
			param.setHeaders(headers);
			Map<Object,Object> paramMap = new HashMap<Object,Object>();
			paramMap.put("loginName", "13700010002");
			paramMap.put("srcNo", "0001");
			paramMap.put("userSessionKey", "c677909c69cf05002e81bcb7354c8b57");
			paramMap.put("suggestion", "嘻嘻嘻嘻嘻嘻嘻嘻");
//			paramMap.put("suggestionImg", new byte[1024]);
			param.setParameters(paramMap);
			param.setMethod("GET");
			param.setSslVerify(false);
			SimpleHttpResult result = SimpleHttpUtils.httpRequest(param);
			String queryResult = result.getContent();
			System.out.println(queryResult);
	  }
//	  @Test
	  public void testVerifyCardType(){
		  String cardNo = "6214623721000123450";
		  CardTypeDTO result = cardBiz.verifyCardInfo(cardNo);
		  System.out.println(result);
	  }
}
