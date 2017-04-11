/** 
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.boss.activity.utils;

import java.beans.PropertyEditorSupport;

import org.springframework.web.util.HtmlUtils;

/**
 * 字符替换
 * 
 * @author：wei.du
 * @since：2014-7-11 上午11:03:53
 * @version:
 */
public class StringEscapeEditor extends PropertyEditorSupport {

	public StringEscapeEditor() {
		super();
	}

	@Override
	public void setAsText(String text) {
		if (text == null) {
			setValue(null);
		} else {
			setValue(HtmlUtils.htmlEscape(text));
		}
	}

	@Override
	public String getAsText() {
		Object value = getValue();
		return value != null ? value.toString() : "";
	}
}