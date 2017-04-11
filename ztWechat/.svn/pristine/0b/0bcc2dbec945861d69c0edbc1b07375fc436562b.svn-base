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
import com.yeepay.g3.facade.lmlc.trust.dto.OrderQueryParamDto;
import com.yeepay.g3.facade.lmlc.trust.enumtype.OrderTypeEnum;
import com.yeepay.g3.facade.lmlc.trust.enumtype.TradeOrderStatusEnum;
import com.yeepay.g3.facade.lmlc.trust.service.FiQueryFacade;
import com.yeepay.g3.facade.lmlc.trust.service.TradeFacade;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.utils.common.DateUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.web.emvc.annotation.Param;
@Controller 
public class FixedController extends BaseAction {
	
	protected TradeFacade tradeFacade = RemoteServiceFactory
            .getService(TradeFacade.class);
	protected FiQueryFacade fiQueryFacade = RemoteServiceFactory
            .getService(FiQueryFacade.class);
	 /**
     * 购买未付产品
     */
    public String purchaseUnPayProduct(String orderNo) {
        if (StringUtils.isEmpty(orderNo)) {
        	setJsonModel("\"FAILED\"");
        }
        try {
            tradeFacade.trading(orderNo);
        } catch (Exception e) {
        	setJsonModel("\"FAILED\"");
        }
        setJsonModel("\"SUCCESS\"");
        return "json";
    }
    
    /**
     * 查询固收理财交易记录
     */
    public String queryFixedTrade(@Param("tradeType") Integer tradeType, @Param("tradeStatus") Integer tradeStatus,
    		@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("pageIndex") Integer pageIndex) {
    	HttpSession session = getMvcFacade().getHttpSession();
        MemberDto memberDto = (MemberDto) session.getAttribute("member");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate;
            Date endDate;
            if (StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)) {
                startDate = DateUtils.addDay(new Date(), -365);
                endDate = new Date();
            } else {
                startDate = dateFormat.parse(startTime);
                endDate = dateFormat.parse(endTime);
            }

            OrderTypeEnum realTradeType;
            switch (tradeType == null ? 0 : tradeType) {
                case 1:
                    realTradeType = OrderTypeEnum.SALES;
                    break;
                case 2:
                    realTradeType = OrderTypeEnum.PAYMENTS;
                    break;
                default:
                    realTradeType = null;
            }

            TradeOrderStatusEnum realTradeStatus;
            switch (tradeStatus == null ? 0 : tradeStatus) {
                case 1:
                    realTradeStatus = TradeOrderStatusEnum.SUCCESS;
                    break;
                case 2:
                    realTradeStatus = TradeOrderStatusEnum.CLOSED;
                    break;
                case 3:
                    realTradeStatus = TradeOrderStatusEnum.PAYING;
                    break;
                default:
                    realTradeStatus = null;
            }

            OrderQueryParamDto orderQueryParamDto = new OrderQueryParamDto();
            orderQueryParamDto.setUserNo(memberDto.getMemberNo());
            orderQueryParamDto.setPayStart(startDate);
            orderQueryParamDto.setPayEnd(endDate);
            orderQueryParamDto.setStatus(realTradeStatus);
            orderQueryParamDto.setType(realTradeType);
            orderQueryParamDto
                    .setPageIndex(pageIndex == null || pageIndex == 0 ? 1
                            : pageIndex);
            orderQueryParamDto.setPageSize(PAGE_SIZE);
            setJsonModel(fiQueryFacade.queryOrders(orderQueryParamDto));
        } catch (Exception e) {
        	setJsonModel("\"null\"");
        }
        return "json";
    }
}
