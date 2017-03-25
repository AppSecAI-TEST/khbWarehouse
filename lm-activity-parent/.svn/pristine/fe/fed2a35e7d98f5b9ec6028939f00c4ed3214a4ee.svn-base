/**
 * @author 陈大涛
 * 2016-6-2下午6:44:36
 */
package com.yeepay.g3.core.activity.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import com.yeepay.g3.core.activity.facade.impl.ActivityWXSendMessageFacadeImpl;
import com.yeepay.g3.facade.activity.dto.ActivityWXSendMessageDTO;
import com.yeepay.g3.facade.activity.dto.ActivityWXSendMessageResultDTO;
import com.yeepay.g3.facade.activity.enums.ActivityWXSendMessageEnum;
import com.yeepay.g3.utils.common.httpclient.SimpleHttpUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigurationUtils;

/**
 * @author 陈大涛
 * 2016-6-2下午6:44:36
 */
public class WxUtil {
	//日志
	private static final Logger logger = LoggerFactory
			.getLogger(ActivityWXSendMessageFacadeImpl.class);
	
	//配置类型:
	private static final String CONFIG_TYPE_SYS="config_type_sys";
	
	//消息模板id配置键
	private static final String LANMAOWX_TEMPLE_ID="lanmaowx_temple_id";
	
	private static final String WX_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";
	//发送模板消息
	private static final String WX_SEND_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send";
	//获取wx用户信息
	private static final String WX_GET_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info";
	
	//获取wx APP_ID
	private static final String APP_ID = ConfigurationUtils
			.getSysConfigParam("lanmaowx_appid").getValue().toString();
	
	//获取wx APP_SECRET
	private static final String APP_SECRET = ConfigurationUtils
			.getSysConfigParam("lanmaowx_appsecret").getValue().toString();
//	public static final String APP_ID="wx196f101900ebc50b";
//	public static final String APP_SECRET="d4624c36b6795d1d99dcf0547af5443d";
	
	public ActivityWXSendMessageResultDTO sendWxMessage(ActivityWXSendMessageEnum type,ActivityWXSendMessageDTO dataDto) {
		Map<String,Object> data = new HashMap<String, Object>();
		Map<String,Object> param = new HashMap<String, Object>();
		data.put("first", getValuesMap(getFormatLineFeed(dataDto.getFirst()), "#173177"));
		data.put("remark", getValuesMap(getFormatLineFeed(dataDto.getRemark()), "#173177"));
		switch (type) {
		case GET_PRIZE://中奖消息模块
			data.put("keyword1", getValuesMap(dataDto.getKeyword1(), "#173177"));
			data.put("keyword2", getValuesMap(dataDto.getKeyword2(), "#173177"));
			break;
		case GET_CHANCE://获得抽奖机会消息模块
			data.put("toName", getValuesMap(dataDto.getToName(), "#173177"));
			data.put("gift", getValuesMap(dataDto.getGift(), "#173177"));
			data.put("time",getValuesMap(dataDto.getTime(), "#173177"));
			break;
		case BUY_SUCCESS://购买成功消息模块
			data.put("keyword1", getValuesMap(dataDto.getKeyword1(), "#173177"));
			data.put("keyword2", getValuesMap(dataDto.getKeyword2(), "#173177"));
			data.put("keyword3", getValuesMap(dataDto.getKeyword3(), "#173177"));
			break;
		case REGISTER_SUCCESS://购买成功消息模块
			data.put("keyword1", getValuesMap(dataDto.getKeyword1(), "#173177"));
			data.put("keyword2", getValuesMap(dataDto.getKeyword2(), "#173177"));
			break;
		case RECHARGE_SUCCESS://充值成功消息模块
			data.put("keyword1", getValuesMap(dataDto.getKeyword1(), "#173177"));
			data.put("keyword2", getValuesMap(dataDto.getKeyword2(), "#173177"));
			data.put("keyword3", getValuesMap(dataDto.getKeyword3(), "#173177"));
			break;
		case CASHIN_SUCCESS://提现成功消息模块
			data.put("keyword1", getValuesMap(dataDto.getKeyword1(), "#173177"));
			data.put("keyword2", getValuesMap(dataDto.getKeyword2(), "#173177"));
			data.put("keyword3", getValuesMap(dataDto.getKeyword3(), "#173177"));
			break;
		case BINDCARD_SUCCESS://绑卡成功消息模块
			data.put("keyword1", getValuesMap(dataDto.getKeyword1(), "#173177"));
			data.put("keyword2", getValuesMap(dataDto.getKeyword2(), "#173177"));
			break;
		case CASH_SUCCESS://兑付成功
			data.put("keyword1", getValuesMap(dataDto.getKeyword1(), "#173177"));
			data.put("keyword2", getValuesMap(dataDto.getKeyword2(), "#173177"));
			data.put("keyword3", getValuesMap(dataDto.getKeyword3(), "#173177"));
			data.put("keyword4", getValuesMap(dataDto.getKeyword4(), "#173177"));
			data.put("keyword5", getValuesMap(dataDto.getKeyword5(), "#173177"));
		default:
			break;
		}
		Map<String, String> ConfigurationMap=new HashMap<String, String>();
		ConfigParam<Map<String, String>> configParam=ConfigurationUtils
				.getConfigParam(CONFIG_TYPE_SYS, LANMAOWX_TEMPLE_ID);
		if(configParam.getValue() == null){
			logger.error("[sendWxMessage] error={}","获取消息模板id，统一配置调用失败");
		}
		ConfigurationMap= configParam.getValue() == null ? new HashMap<String, String>() : configParam.getValue();
		logger.info("[sendWxMessage] touser={},template_id={},url={},data={}", dataDto.getOpenId(),ConfigurationMap.get(type.toString()),dataDto.getUrl(),data);
			param.put("touser", dataDto.getOpenId());
			param.put("template_id",  ConfigurationMap.get(type.toString()));
			param.put("url", dataDto.getUrl());
			param.put("data", data);
			//获取 access_token;
			String	access_token=getOrdinaryAccessToken();
			//发送模板信息
			logger.info("[sendWxMessage] openId={}",dataDto.getOpenId());
			String sendResult = HttpRequestUtils.post(WX_SEND_MESSAGE+"?access_token="+access_token,JSONObject.fromObject(param).toString());
			logger.info("[sendWxMessage] 发送消息模板：返回结果info={}",sendResult);
			Map<String,Object> resultMap = toHashMap(sendResult);
			ActivityWXSendMessageResultDTO resultDto=new ActivityWXSendMessageResultDTO();
			resultDto.setCode((Integer)resultMap.get("errcode"));
			resultDto.setMessage((String)resultMap.get("errmsg"));
			return resultDto;
	}
	
	/**
	 * 公用value map方法
	 * @author 陈大涛
	 * 2016-6-1下午1:22:53
	 */
	private Map<String, Object> getValuesMap(Object value,String color){
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("value", value);
		values.put("color", color);
		return values;
	}
	
	/**
	 * 判断字符串中是否包含\n字段，进行换行
	 * 注意：此方法只限于微信推送消息模板使用，因为此方法只为解决统一配置不能换行问题！！！
	 * 如果字符串中包含\\n 此方法就没有使用的意义了
	 * @author 陈大涛
	 * 2016-9-26上午10:12:18
	 */
	private String getFormatLineFeed(String data){
		if(!data.isEmpty()){
			if(data.contains("\\n")){
				data=data.replace("\\n", "\n");//换行
			}
		}
		return data;
	}
	/** 
	    * 将json格式的字符串解析成Map对象 <li> 
	    * json格式：{"name":"admin","retries":"3fff","testname" 
	    * :"ddd","testretries":"fffffffff"} 
	    */  
	   private  HashMap<String, Object> toHashMap(Object object)  
	   {  
	       HashMap<String, Object> data = new HashMap<String, Object>();  
	       // 将json字符串转换成jsonObject  
	       JSONObject jsonObject = JSONObject.fromObject(object);  
	       Iterator it = jsonObject.keys();  
	       // 遍历jsonObject数据，添加到Map对象  
	       while (it.hasNext())  
	       {  
	           String key = String.valueOf(it.next());  
	           Object value =  jsonObject.get(key);  
	           data.put(key, value);  
	       }  
	       return data;  
	   }  
	
	/**
	 * 获取基础access_token；
	 * @author cdt
	 * @return
	 */
	public  String getOrdinaryAccessToken() {
		// 凭证获取(GET)
		String token_url = WX_ACCESS_TOKEN+"?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		String requestUrl = token_url.replace("APPID",APP_ID).replace("APPSECRET",APP_SECRET);
		// 发起GET请求获取凭证
		String str = SimpleHttpUtils.httpGet(requestUrl, null);
		JSONObject jsonObject = JSONObject.fromObject(str);
		String access_token = null;
		if (null != jsonObject) {
			access_token = jsonObject.getString("access_token");
		}
		return access_token;
	}

	public Map<String,Object> getWxUserInfo(String openId) {
		 String resultJson = HttpRequestUtils.sendHttpRequest(WX_GET_USER_INFO, "get", buildGetUserInfoParam(openId));
		 logger.info("getWxUserInfo openId={}, resultJson={}",openId ,resultJson);
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap = toHashMap(resultJson);
		return resultMap;
	}

	/**
	 * 
	 * 用来获取微信用户信息的请求参数
	 * @param openId
	 * @return
	 */
	public  Map<String,String> buildGetUserInfoParam(String openId){
		Map<String,String> paramMap=new HashMap<String,String>();
		paramMap.put("openid",openId);
		paramMap.put("lang","zh_CN");
		paramMap.put("ACCESS_TOKEN",getOrdinaryAccessToken());
		return paramMap;
	}

}
