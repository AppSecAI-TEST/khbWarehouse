package com.yeepay.g3.app.lmweChat.action.fundActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.lanmao.fund.facade.fundsales.service.LMFundInfoQueryServiceFacade;
import com.lanmao.fund.facade.queryservice.dto.FundInfoQueryResultPageDTO;
import com.lanmao.fund.facade.queryservice.dto.FundQueryParamDTO;
import com.lanmao.fund.facade.queryservice.dto.LMFundBasicInfoDTO;
import com.lanmao.fund.facade.queryservice.dto.LMFundDetailInfoDTO;
import com.lanmao.fund.facade.queryservice.enumtype.FundTypeEnum;
import com.lanmao.fund.facade.queryservice.enumtype.ListFundEnum;
import com.yeepay.g3.app.lmweChat.action.BaseAction;
import com.yeepay.g3.app.lmweChat.utils.GetParamUtils;
import com.yeepay.g3.app.lmweChat.utils.SecretUtils;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.web.emvc.annotation.Param;

/**
 * 基金的相关活动
 * @author hongbin.kang
 * @date 2016年10月13日上午10:45:07
 */
@Controller
public class FundActivityAction extends BaseAction {
	Logger logger = LoggerFactory.getLogger(FundActivityAction.class);
	
	protected LMFundInfoQueryServiceFacade lMFundInfoQueryServiceFacade = RemoteServiceFactory
			.getService(LMFundInfoQueryServiceFacade.class);
	
	/**
	 * 基金10元起投活动
	 * @author hongbin.kang
	 * @date 2016年10月13日 上午10:57:45
	 * @return
	 */
	public String fundInvset() {
		logger.info("[fundActivity] fundActivity={}", "开始基金10元起投之旅");
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getMvcFacade().getRequest();
		HttpServletResponse response = getMvcFacade().getResponse();
		List<LMFundBasicInfoDTO> fundList = new ArrayList<LMFundBasicInfoDTO>();//基金产品列表
		//STEP.1 获取要展示的基金的code的列表
		ConfigParam configParam = ConfigurationUtils.getConfigParam("config_type_text_resources", "LM_10RMB_FUNDCODE");
		List<String> fundCodeList = (List<String>) configParam.getValue();
		logger.info("[fundActivity] fundCodeList={}",fundCodeList);
		
		//STEP.2 查询基金的详情
		FundQueryParamDTO fundQueryParamDTO = new FundQueryParamDTO();
		fundQueryParamDTO.setMerNo(GetParamUtils.getFundPlatNo());
		if (StringUtils.isEmpty(fundQueryParamDTO.getHeat())) {
			fundQueryParamDTO.setHeat(null);
		}
		fundQueryParamDTO.setFundType(FundTypeEnum.ALL);
		//排序默认设为年涨幅
		if (null == fundQueryParamDTO.getListFundEnum()) {
			fundQueryParamDTO.setListFundEnum(ListFundEnum.YEAR_INCREASE);
		}
		for(String code : fundCodeList) {
			fundQueryParamDTO.setSearchCondi(code);
			FundInfoQueryResultPageDTO fundInfoQueryResultPageDto = lMFundInfoQueryServiceFacade.queryFundList(fundQueryParamDTO);
			logger.info("[fundActivity] fundInfoQueryResultPageDto={}",fundInfoQueryResultPageDto);
			LMFundBasicInfoDTO lMFundBasicInfoDto = new LMFundBasicInfoDTO();
			if(null != fundInfoQueryResultPageDto && fundInfoQueryResultPageDto.getFundBasicInfoDto().size() != 0) {
				lMFundBasicInfoDto = fundInfoQueryResultPageDto.getFundBasicInfoDto().get(0);
				//格式化为两位小数
				BigDecimal decimal = null;
				if(null != lMFundBasicInfoDto.getYearIncrease()) {
					decimal = new BigDecimal(lMFundBasicInfoDto.getYearIncrease().toString());  
				} else {
					decimal = new BigDecimal(0);  
				}
		        decimal = decimal.setScale(2, RoundingMode.HALF_UP); 
		        lMFundBasicInfoDto.setYearIncrease(decimal);
			}
			fundList.add(lMFundBasicInfoDto);
		}
		
		//STEP.3 用户的编号和基金的地址
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		String ascMemberNo = null ;
		if(null != memberDto) {
			ascMemberNo =  SecretUtils.secretData(memberDto.getMemberNo());
		}
		String fundSalesUrl = GetParamUtils.getfundSalesUrl();
		
		addModelObject("fundSalesUrl", fundSalesUrl);// 基金的域名地址
		addModelObject("ascMemberNo", ascMemberNo);// 加密的用户编码
		addModelObject("fundList", fundList);
		return SUCCESS;
	}
}
