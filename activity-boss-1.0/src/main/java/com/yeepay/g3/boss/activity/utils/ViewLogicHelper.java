/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.boss.activity.utils;

import com.yeepay.g3.common.enums.CurrencyEnum;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: 页面展示帮助接口</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 14-5-22 11:18
 */
@Component
public class ViewLogicHelper {

	/**
	 * 全部货币类型
	 */
	private Map<String, String> allCurrencyType = new HashMap<String, String>();

	/**
	 * 初始化
	 */
	@PostConstruct
	public void init() {
		for(CurrencyEnum currency : CurrencyEnum.values()){
			allCurrencyType.put(currency.name(), currency.getUnit());
		}
		allCurrencyType.put("CNY", "人民币");
	}

	public Map<String, String> getAllCurrencyType() {
		return allCurrencyType;
	}

}
