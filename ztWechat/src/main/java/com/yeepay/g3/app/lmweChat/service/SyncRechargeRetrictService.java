package com.yeepay.g3.app.lmweChat.service;

import com.yeepay.g3.facade.lmportal.dto.RechargeRestrictDto;

public interface SyncRechargeRetrictService {


	RechargeRestrictDto queryRechargeRetrictByBankCode(String bankCode);

	void syncRechargeRetrict();


}
