package com.yeepay.g3.app.lmweChat.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.yeepay.g3.app.enums.ErrorCodeTypeEnum;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.exception.YeepayRuntimeException;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigurationUtils;
public class GetParamUtils {

	/**
	 * 通用异常处理相关
	 */
	public static final String PEBI900008 = "PEBI900008"; // 支付超限
	public static final String COMMON_MESSAGE_SPLIT = "_";
	
	public static String getScbPlatNo() {
		String scbPlatNo = null;
		ConfigParam<String> scbPlatNoConfig = ConfigurationUtils
				.getSysConfigParam("MLANMAO_SCB_PLAT_NO");
		if (scbPlatNoConfig != null && scbPlatNoConfig.getValue() != null) {
			scbPlatNo = scbPlatNoConfig.getValue();
		} else {
			throw new YeepayRuntimeException("生财宝平台号未获取到");
		}
		return scbPlatNo;
	}
	
	//获取基金的懒猫的平台编号
	public static String getFundPlatNo() {
		String fundPlatNo = null;
		ConfigParam<String> fundPlatNoConfig = ConfigurationUtils
				.getSysConfigParam("MLANMAO_FUND_PLAT_NO");
		if (fundPlatNoConfig != null && fundPlatNoConfig.getValue() != null) {
			fundPlatNo = fundPlatNoConfig.getValue();
		} else {
			throw new YeepayRuntimeException("基金平台号未获取到");
		}
		return fundPlatNo;
	}
	/**
	 * 获取基金的域名
	 * @author hongbin.kang
	 * @date 2016年8月19日 下午5:21:36
	 * @return
	 */
	public static String getfundSalesUrl() {
		String fundSalesUrl = null;
		ConfigParam<String> fundUrlConfig = ConfigurationUtils
				.getSysConfigParam("LM_fund_sales_url");
		if (fundUrlConfig != null && fundUrlConfig.getValue() != null) {
			fundSalesUrl = fundUrlConfig.getValue();
		} else {
			throw new YeepayRuntimeException("基金的url地址未找到");
		}
		return fundSalesUrl;
	}
	
	public static Boolean getIsVerifyCode(){
		Boolean isVerifyCode=null;
		ConfigParam<Boolean> isVerifyCodeConfig = ConfigurationUtils
				.getSysConfigParam("MLANMAO_IS_VERIFY_CODE");
		if (isVerifyCodeConfig != null && isVerifyCodeConfig.getValue() != null) {
			isVerifyCode = isVerifyCodeConfig.getValue();
		} else {
			throw new YeepayRuntimeException("短信验证码验证模式未获取到");
		}
		return isVerifyCode;
	}
	/**
	 * 读取统一配置文件
	 */
	@SuppressWarnings("unchecked")
	public static String readCommonMessage(String key){
		Map<String, String> exes = (Map<String, String>)ConfigurationUtils.getConfigParam("config_type_text_resources",  
				"lmportal_exe_message_config").getValue();
		return StringUtils.isEmpty(exes.get(key)) ? "_" : exes.get(key);
	}
	
	/**
	 * 奥运活动配置信息
	 * @param in -
	 * @return 
	 */
	public static Map<?,?> readOlympic(){
		ConfigParam<?> config = ConfigurationUtils.getConfigParam("config_type_text_resources", "lmportal_activity_info");
		Map<?,?> map = config.getValue() == null ? new LinkedHashMap<String,String>() : (Map<?,?>)config.getValue(); 
		return map;
	}
	/**
	 * 读错误码统一配置
	 * @param in -
	 * @return 
	 */
	public static String[] readErrorCodeConfig(ErrorCodeTypeEnum key){
		Map<String,String> map = (Map<String, String>) ConfigurationUtils.getConfigParam("config_type_text_resources", "lmapp_error_code_config").getValue();
		String[] error = (String[]) (StringUtils.isEmpty(map.get(key.toString())) ? "_" : map.get(key.toString()).split(COMMON_MESSAGE_SPLIT));
		return error;
	}
	/**
	 * 旅游半价送京东卡总数量查询
	 * @param in -
	 * @return 
	 */
	public static String readTotalCount(){
		Map<String,String> map = (Map<String, String>) ConfigurationUtils.getConfigParam("config_type_text_resources", "lm_tour_JD_total_count").getValue();
		String total_count = StringUtils.isEmpty(map.get("total_count"))  ? "0" : map.get("total_count");
		return total_count;
	}
	
	/**
	 * 兑换码消息公告跳转链接
	 * @return
	 */
	public static Map<?,?> readRedeemCodeLink(){
		ConfigParam<?> config = ConfigurationUtils.getConfigParam("config_type_text_resources", "lm_activity_redeemCode_link");
		Map<?,?> map = config.getValue() == null ? new LinkedHashMap<String,String>() : (Map<?,?>)config.getValue(); 
		return map;
	}
	
	/**
	 * 读生财宝开关统一配置
	 * @param in -
	 * @return 
	 */
	public static Map<String,String> readScbSwitchConfig(){
		Map<String,String> map = (Map<String, String>) ConfigurationUtils.getConfigParam("config_type_text_resources", "lmapp_scb_switch_config").getValue();
		return map = (map == null? new LinkedHashMap<String,String>() : map);
	}
	
	/**
	 * 读取秒杀的展示时间
	 */
	public static Map<?,?> readSeckillConfig(){
		ConfigParam<?> config = ConfigurationUtils.getConfigParam("config_type_text_resources", "lmportal_act_mky");
		Map<?, ?> map = config.getValue() == null ? new LinkedHashMap<String,String>() : (Map<?, ?>) config.getValue();
		return map;
	}
	/**
	 * 计算是否需要展示秒客盈栏位
	 * status0 不显示
	 * status1 倒计时
	 * status2 秒杀中或秒杀后
	 */
	public static String readSeckillFlag(){
		Logger logger = LoggerFactory.getLogger(GetParamUtils.class);
		String showFlag = "status0";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try{
			//售卖时间
			Date date = sdf.parse(readSeckillConfig().get("saleDate").toString());
			//首页开始展示的时间
			Calendar cds = Calendar.getInstance();
			cds.setTime(date);
			cds.add(Calendar.HOUR_OF_DAY, Integer.parseInt(readSeckillConfig().get("startShow").toString()));
			//首页结束展示的时间
			Calendar cde = Calendar.getInstance();
			cde.setTime(date);
			cde.add(Calendar.HOUR_OF_DAY, Integer.parseInt(readSeckillConfig().get("endShow").toString()));
			Date curDate = new Date();
			//当前时间大于展示时间,当前时间是否小于售卖时间
			showFlag = curDate.getTime() >= cds.getTime().getTime() ? (curDate.getTime() < date.getTime() ? "status1" : (curDate.getTime() <= cde.getTime().getTime() ? "status2" : "status0")): "status0";
			return showFlag;
		}catch(Exception e){
			logger.info("[readSeckillFlag] info={},ERROR={}", "计算是否需要展示秒客盈时异常",e);
			return "status0";
		}
	}
}
