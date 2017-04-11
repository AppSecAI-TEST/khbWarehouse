package com.yeepay.g3.app.lmweChat.action.old;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.yeepay.g3.app.lmweChat.action.BaseAction;
import com.yeepay.g3.app.lmweChat.utils.GetParamUtils;
import com.yeepay.g3.facade.fundbiz.dto.FundPurchaseRedeemPageDTO;
import com.yeepay.g3.facade.fundbiz.service.FundQueryFacade;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.utils.common.DateUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.web.emvc.annotation.Param;
@Controller 
public class ScbController extends BaseAction {
	protected FundQueryFacade fundQueryFacade = RemoteServiceFactory
			.getService(FundQueryFacade.class);
	/**
	 * 查询生财宝交易记录
	 */
	public String queryScbRecord(@Param("tradeType") Integer tradeType, @Param("tradeStatus") Integer tradeStatus,
			@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("pageIndex") Integer pageIndex) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
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

			String realTradeType;
			switch (tradeType == null ? 0 : tradeType) {
			case 1:
				realTradeType = "转入";
				break;
			case 2:
				realTradeType = "转出";
				break;
			default:
				realTradeType = null;
			}

			String realTradeStatus;
			switch (tradeStatus == null ? 0 : tradeStatus) {
			case 1:
				realTradeStatus = "成功";
				break;
			case 2:
				realTradeStatus = "失败";
				break;
			case 3:
				realTradeStatus = "进行中";
				break;
			default:
				realTradeStatus = null;
			}

			FundPurchaseRedeemPageDTO fundPurchaseRedeemPageDTO = fundQueryFacade
					.queryTransactionRecord(GetParamUtils.getScbPlatNo(),
							memberDto.getMemberNo(), realTradeType,
							realTradeStatus, pageIndex == null
									|| pageIndex == 0 ? 1 : pageIndex,
							PAGE_SIZE, startDate, endDate);
			if (fundPurchaseRedeemPageDTO != null) {
				setJsonModel(fundPurchaseRedeemPageDTO);
			} else {
				setJsonModel("\"NULL_RESULT\"");
			}
		} catch (Exception e) {
			setJsonModel("\"NULL_RESULT\"");
		}
		return "json";
	}
}
