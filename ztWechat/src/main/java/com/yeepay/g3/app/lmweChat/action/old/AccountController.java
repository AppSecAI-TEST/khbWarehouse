package com.yeepay.g3.app.lmweChat.action.old;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeepay.g3.app.lmweChat.action.BaseAction;
import com.yeepay.g3.facade.lmportal.dto.AccountTradeRecordQueryParamDto;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.facade.lmportal.enumtype.TradeStatusEnum;
import com.yeepay.g3.facade.lmportal.enumtype.TradeTypeEnum;
import com.yeepay.g3.facade.lmportal.service.LPQueryFacade;
import com.yeepay.g3.utils.common.DateUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.web.emvc.annotation.Param;
@Controller 
public class AccountController extends BaseAction {
	
	protected LPQueryFacade lPQueryFacade = RemoteServiceFactory
			.getService(LPQueryFacade.class);
	/**
	 * 跳转老系统账单查询页面
	 */
	public String toRecord(){
		return SUCCESS;
	}
	/**
	 * 查询懒猫账户交易记录
	 */
	public String queryLmRecord(@Param("tradeType") Integer tradeType, @Param("tradeStatus") Integer tradeStatus,
			@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("pageIndex") Integer pageIndex) {
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startDate = null;
			Date endDate = null;
			if (StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)) {
				startDate = DateUtils.addDay(new Date(), -365);
				endDate = new Date();
			} else {
				startDate = dateFormat.parse(startTime);
				endDate = dateFormat.parse(endTime);
			}

			TradeTypeEnum realTradeType;
			switch (tradeType == null ? 0 : tradeType) {
			case 1:
				realTradeType = TradeTypeEnum.LMACCOUNT_IN;
				break;
			case 2:
				realTradeType = TradeTypeEnum.LMACCOUNT_OUT;
				break;
			default:
				realTradeType = null;
			}

			TradeStatusEnum realTradeStatus;
			switch (tradeStatus == null ? 0 : tradeStatus) {
			case 1:
				realTradeStatus = TradeStatusEnum.SUCCESS;
				break;
			case 2:
				realTradeStatus = TradeStatusEnum.FAIL;
				break;
			case 3:
				realTradeStatus = TradeStatusEnum.INPROCESS;
				break;
			default:
				realTradeStatus = null;
			}

			AccountTradeRecordQueryParamDto accountTradeRecordQueryParamDto = new AccountTradeRecordQueryParamDto();
			accountTradeRecordQueryParamDto.setUserNo(memberDto.getMemberNo());
			accountTradeRecordQueryParamDto.setStartDate(startDate);
			accountTradeRecordQueryParamDto.setEndDate(endDate);
			accountTradeRecordQueryParamDto.setTradeType(realTradeType);
			accountTradeRecordQueryParamDto.setTradeStatus(realTradeStatus);
			accountTradeRecordQueryParamDto.setPageIndex(pageIndex == null
					|| pageIndex == 0 ? 1 : pageIndex);
			accountTradeRecordQueryParamDto.setPageSize(PAGE_SIZE);
			setJsonModel(lPQueryFacade
					.queryAccountTradeRecord(accountTradeRecordQueryParamDto));
		} catch (Exception e) {
			setJsonModel("\"NULL_RESULT\"");
		}
		return "json";
	}

}
