package com.yeepay.g3.app.dto;

import java.io.Serializable;
/**获取app最新版本号
 * @Description: 版本号
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-8-15 上午10:44:15
 * @version 2016-8-15
 */
public class AppVersionDTO extends UserOpeDTO implements Serializable{

	private static final long serialVersionUID = 635021474265127372L;
	
	/**
	 * app最新版本号
	 */
	private String appVersion;
	/**
	 * apk下载地址
	 */
	private String apkUrl;

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getApkUrl() {
		return apkUrl;
	}

	public void setApkUrl(String apkUrl) {
		this.apkUrl = apkUrl;
	}

	@Override
	public String toString() {
		return "AppVersionDTO:[" + "appVersion=" + appVersion + ",apkUrl=" + apkUrl + "]";
	}
	
	
}
