package com.yeepay.g3.boss.activity.utils;

import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @Description 数据字典工具类
 * @author zhenping.zhou
 * @CreateTime 2016年4月5日 下午4:04:25
 * @version 1.0
 */
@Component
public class DataMapHelper {
	private static final String CONFIG_TYPE = "config_type_text_resources";

	private static final String FUNDS_ACCOUNT_STATUS = "funds_account_status";
	private static final String CREDIT_ACCOUNT_STATUS = "credit_account_status";
	private static final String FUNDS_ACCOUNT_TYPE = "funds_account_type";
	private static final String CREDIT_ACCOUNT_TYPE = "credit_account_type";
	private static final String ACCOUNT_FREEZE_TYPE = "account_freeze_type";
	private static final String ACCOUNT_FREEZE_REASON = "account_freeze_reason";
	private static final String FUNDS_ACCOUNT_ADJUST_TYPE = "funds_account_adjust_type";
	private static final String CREDIT_ACCOUNT_ADJUST_TYPE_KEY = "credit_account_adjust_type";
	private static final String FUNDS_ACCOUNT_TRADE_TYPE = "account_trade_type";
	private static final String CREDIT_ACCOUNT_TRADE_TYPE = "credit_account_trade_type";
	private static final String CREDIT_ACCOUNT_FREEZE_TYPE = "credit_account_freeze_type";
	private static final String CREDIT_ACCOUNT_INCREASE_CREDIT_TYPE = "credit_account_increase_credit_type";
	private static final String CREDIT_ACCOUNT_INCREASE_CREDIT_STATUS = "credit_account_increase_credit_status";
	private static final String CREDIT_ACCOUNT_CIRCLE_TIME_TYPE = "credit_account_circle_time_type";
	private static final String ADJUST_APPLY_CHECK_STATUS = "adjust_apply_check_status";
	private static final String ADJUST_APPLY_EXECUTE_STATUS = "adjust_apply_execute_status";

	/**
	 * 获取资金账户状态map
	 *
	 * @return
	 */
	public Map<String, String> getFundsAccountStatus() {
		ConfigParam<Map<String, String>> configParam = ConfigurationUtils
				.getConfigParam(CONFIG_TYPE, FUNDS_ACCOUNT_STATUS);
		return configParam.getValue() == null ? new HashMap<String, String>() : configParam.getValue();
	}

	/**
	 * 获取信用账户状态map
	 *
	 * @return
	 */
	public Map<String, String> getCreditAccountStatus() {
		ConfigParam<Map<String, String>> configParam = ConfigurationUtils
				.getConfigParam(CONFIG_TYPE, CREDIT_ACCOUNT_STATUS);
		return configParam.getValue() == null ? new HashMap<String, String>() : configParam.getValue();
	}

	/**
	 * 获取资金账户类别map
	 *
	 * @return
	 */
	public Map<String, String> getFundsAccountType() {
		ConfigParam<Map<String, String>> configParam = ConfigurationUtils
				.getConfigParam(CONFIG_TYPE, FUNDS_ACCOUNT_TYPE);
		return configParam.getValue() == null ? new HashMap<String, String>() : configParam.getValue();
	}

	/**
	 * 获取信用账户类别map
	 *
	 * @return
	 */
	public Map<String, String> getCreditAccountType() {
		ConfigParam<Map<String, String>> configParam = ConfigurationUtils
				.getConfigParam(CONFIG_TYPE, CREDIT_ACCOUNT_TYPE);
		return configParam.getValue() == null ? new HashMap<String, String>() : configParam.getValue();
	}

	/**
	 * 获取账户冻结类型map
	 *
	 * @return
	 */
	public Map<String, String> getAccountFreezeType() {
		ConfigParam<Map<String, String>> configParam = ConfigurationUtils
				.getConfigParam(CONFIG_TYPE, ACCOUNT_FREEZE_TYPE);
		return configParam.getValue() == null ? new HashMap<String, String>() : configParam.getValue();
	}

	/**
	 * 获取 信用账户冻结类型map
	 *
	 * @return
	 */
	public Map<String, String> getCreditAccountFreezeType() {
		ConfigParam<Map<String, String>> configParam = ConfigurationUtils
				.getConfigParam(CONFIG_TYPE, CREDIT_ACCOUNT_FREEZE_TYPE);
		return configParam.getValue() == null ? new HashMap<String, String>() : configParam.getValue();
	}

	/**
	 * 获取账户冻结原因
	 *
	 * @return
	 */
	public List<String> getAccountFreezeReason() {
		ConfigParam<List<String>> configParam = ConfigurationUtils
				.getConfigParam(CONFIG_TYPE, ACCOUNT_FREEZE_REASON);
		return configParam.getValue() == null ? new ArrayList<String>() : configParam.getValue();
	}

	/**
	 * 获得资金账户调账类型
	 *
	 * @return
	 */
	public Map<String, String> getFundsAccountAdjustType() {
		ConfigParam<Map<String, String>> configParam = ConfigurationUtils
				.getConfigParam(CONFIG_TYPE, FUNDS_ACCOUNT_ADJUST_TYPE);
		Map<String, String> result = configParam.getValue() == null ? new HashMap<String, String>() : configParam.getValue();
		return result;
	}

	/**
	 * 获得信用账户调账类型
	 *
	 * @return
	 */
	public Map<String, String> getCreditAccountAdjustType() {
		ConfigParam<Map<String, String>> configParam = ConfigurationUtils
				.getConfigParam(CONFIG_TYPE, CREDIT_ACCOUNT_ADJUST_TYPE_KEY);
		return configParam.getValue() == null ? new HashMap<String, String>() : configParam.getValue();
	}

	/**
	 * 获取资金账户交易类型
	 *
	 * @return
	 */
	public Map<String, String> getFundsAccountTradeType() {
		ConfigParam<Map<String, String>> configParam = ConfigurationUtils
				.getConfigParam(CONFIG_TYPE, FUNDS_ACCOUNT_TRADE_TYPE);
		Map<String, String> result = configParam.getValue() == null ? new HashMap<String, String>() : configParam.getValue();
		return result;
	}

	/**
	 * 获取信用账户交易类型
	 *
	 * @return
	 */
	public Map<String, String> getCreditAccountTradeType() {
		ConfigParam<Map<String, String>> configParam = ConfigurationUtils
				.getConfigParam(CONFIG_TYPE, CREDIT_ACCOUNT_TRADE_TYPE);
		return configParam.getValue() == null ? new HashMap<String, String>() : configParam.getValue();
	}

	/**
	 * 获取信用账户增信类型
	 *
	 * @return
	 */
	public Map<String, String> getIncreaseCreditType() {
		ConfigParam<Map<String, String>> configParam = ConfigurationUtils
				.getConfigParam(CONFIG_TYPE, CREDIT_ACCOUNT_INCREASE_CREDIT_TYPE);
		return configParam.getValue() == null ? new HashMap<String, String>() : configParam.getValue();
	}

	/**
	 * 获取信用账户增信状态
	 *
	 * @return
	 */
	public Map<String, String> getIncreaseCreditStatus() {
		ConfigParam<Map<String, String>> configParam = ConfigurationUtils
				.getConfigParam(CONFIG_TYPE, CREDIT_ACCOUNT_INCREASE_CREDIT_STATUS);
		return configParam.getValue() == null ? new HashMap<String, String>() : configParam.getValue();
	}

	/**
	 * 获取信用账户循环时间类型
	 *
	 * @return
	 */
	public Map<String, String> getCircleTimeType() {
		ConfigParam<Map<String, String>> configParam = ConfigurationUtils
				.getConfigParam(CONFIG_TYPE, CREDIT_ACCOUNT_CIRCLE_TIME_TYPE);
		return configParam.getValue() == null ? new HashMap<String, String>() : configParam.getValue();
	}
	
	/**
	 * 获取调账申请审核状态
	 * @return
	 */
	public Map<String, String> getAdjustApplyCheckStatus() {
		ConfigParam<Map<String, String>> configParam = ConfigurationUtils
				.getConfigParam(CONFIG_TYPE, ADJUST_APPLY_CHECK_STATUS);
		return configParam.getValue() == null ? new HashMap<String, String>() : configParam.getValue();
	}
	
	/**
	 * 获取调账申请执行状态
	 * @return
	 */
	public Map<String, String> getAdjustApplyExecuteStatus() {
		ConfigParam<Map<String, String>> configParam = ConfigurationUtils
				.getConfigParam(CONFIG_TYPE, ADJUST_APPLY_EXECUTE_STATUS);
		return configParam.getValue() == null ? new HashMap<String, String>() : configParam.getValue();
	}


}
