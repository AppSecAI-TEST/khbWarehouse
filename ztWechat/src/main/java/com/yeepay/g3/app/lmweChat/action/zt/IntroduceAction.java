package com.yeepay.g3.app.lmweChat.action.zt;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.lanmao.fund.facade.fundsales.service.FundTradeFacade;
import com.lanmao.fund.facade.fundsales.service.FundTradeQueryFacade;
import com.yeepay.g3.app.lmweChat.action.BaseAction;
import com.yeepay.g3.app.lmweChat.utils.StringProcessorUtils;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.facade.zt.dto.ZtAssetOrderInfoDTO;
import com.yeepay.g3.facade.zt.dto.ZtSceneDTO;
import com.yeepay.g3.facade.zt.dto.ZtYieldRateDTO;
import com.yeepay.g3.facade.zt.enums.CalculateType;
import com.yeepay.g3.facade.zt.enums.ZtSceneTypeEnum;
import com.yeepay.g3.facade.zt.facade.ZtPolicyOrderFacade;
import com.yeepay.g3.facade.zt.facade.ZtPolicyYieldRateFacade;
import com.yeepay.g3.facade.zt.facade.ZtRetreatRecordFacade;
import com.yeepay.g3.facade.zt.facade.ZtSceneFacade;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.web.emvc.annotation.Param;

@Controller
public class IntroduceAction extends BaseAction {
	
	private static final Logger logger = LoggerFactory
			.getLogger(IntroduceAction.class);
	
	
	protected FundTradeQueryFacade fundTradeQueryFacade = RemoteServiceFactory
			.getService(FundTradeQueryFacade.class);
	protected ZtSceneFacade ztSceneFacade = RemoteServiceFactory
			.getService(ZtSceneFacade.class);
	protected ZtRetreatRecordFacade ztRetreatRecordFacade = RemoteServiceFactory
			.getService(ZtRetreatRecordFacade.class);
	protected FundTradeFacade fundTradeFacade = RemoteServiceFactory
			.getService(FundTradeFacade.class);
	private ZtPolicyYieldRateFacade ztPolicyYieldRateFacadeImpl = RemoteServiceFactory.getService(ZtPolicyYieldRateFacade.class);
	
	private ZtPolicyOrderFacade ztPolicyOrderFacade = RemoteServiceFactory.getService(ZtPolicyOrderFacade.class);
	/**
	 * 品牌宣传
	 * @author hongbin.kang
	 * @date 2016年10月24日 下午5:31:40
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public String brand() throws ServletException, IOException {
		logger.info("进去品牌宣传页");
		HttpServletRequest request = getMvcFacade().getRequest();
		HttpServletResponse response = getMvcFacade().getResponse();
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		if(null != memberDto) {
			List<ZtAssetOrderInfoDTO> assetOrderList = ztPolicyOrderFacade.queryOrderInfoByMemberNo(memberDto.getMemberNo());
			if(null != assetOrderList && assetOrderList.size()>0) {
				String uri = "sceneList";
				RequestDispatcher dispatcher = request.getRequestDispatcher(uri);
				dispatcher.forward(request, response);
			}
		}
		return "success";
	}
	
	/**
	 * 产品宣传页
	 * @author hongbin.kang
	 * @date 2016年10月24日 下午5:52:00
	 * @return
	 */
	public String zTproduct() {
		logger.info("进去产品宣传页");
		return "success";
	}
	/**
	 * ajax 产品宣传页获取收益信息
	 * @author 陈大涛
	 * 2016-11-9上午10:50:29
	 */
	public String queryIncomeForProduct(){
		Map<String,Object> result = new HashMap<String, Object>();
		List<ZtYieldRateDTO> yieldRatePreferredList = new ArrayList<ZtYieldRateDTO>();
		List<ZtYieldRateDTO> yieldRateEodList = new ArrayList<ZtYieldRateDTO>();
		 //查询基金优选股票型基金组合list
		ZtYieldRateDTO ztYieldRatePreferredDto =new ZtYieldRateDTO();
		for(int i=5;i>0;i--){
				ztYieldRatePreferredDto.setCalculateType(CalculateType.FUND_PREFERRED_INVEST);//比较基准
				ztYieldRatePreferredDto.setLastTerm(i);
				yieldRatePreferredList=ztPolicyYieldRateFacadeImpl.queryEntityByParam(ztYieldRatePreferredDto);
				if(yieldRatePreferredList.size()!=0){
					break;
				}
		}
		//查询A股指数行情收益list
		 ZtYieldRateDTO ztYieldRateOnceEodDTO =new ZtYieldRateDTO();
		 ztYieldRateOnceEodDTO.setCalculateType(CalculateType.AINDEX_EOD);
		 ztYieldRateOnceEodDTO.setLastTerm(ztYieldRatePreferredDto.getLastTerm());
		 yieldRateEodList=ztPolicyYieldRateFacadeImpl.queryEntityByParam(ztYieldRateOnceEodDTO);
		 result.put("yieldRatePreferredList", commonUtil(yieldRatePreferredList));
		 result.put("yieldRateEodList", commonUtil(yieldRateEodList));
		 setJsonModel(result);
		 return "json";
	}
	
	private List<List<Double>> commonUtil(List<ZtYieldRateDTO> param){
		List<List<Double>> resultParam = new ArrayList<List<Double>>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
		for(ZtYieldRateDTO items:param){
				List<Double> paramList = new ArrayList<Double>();
				Calendar cal = Calendar.getInstance();
				try {
					cal.setTime(sdf.parse(items.getCurMonth()));
				} catch (ParseException e) {
					logger.error("[commonUtil] 转换时间格式错误");
				}
				long timestamp = cal.getTimeInMillis();
				paramList.add(new BigDecimal(timestamp).doubleValue());
				paramList.add((items.getYieldRate().multiply(new BigDecimal(100) )).doubleValue());//百分比
				resultParam.add(paramList);
		}
		return resultParam;
	}
	/**
	 * 场景列表
	 * @author hongbin.kang
	 * @date 2016年10月24日 下午5:52:09
	 * @return
	 */
	public String sceneList() {
		logger.info("进去场景列表页");
		List<ZtSceneDTO> sceneList =  ztSceneFacade.queryZtSceneList();
		ZtSceneDTO personCustom = null;
		for(int i=0;i<sceneList.size();i++) {
			ZtSceneDTO dto = sceneList.get(i);
			dto.setSceneDesc(StringProcessorUtils.replaceRN(dto.getSceneDesc()));
			if(ZtSceneTypeEnum.PERSONALIZED_CUSTOM.equals(dto.getSceneType())) {
				personCustom = sceneList.remove(i);
			}
		}
		/*HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		if(null != memberDto) {
			addModelObject("loginFalg", "1");
		}*/
		addModelObject("personCustom", personCustom);
		addModelObject("sceneList", sceneList);
		return "success";
	}
	
	/**
	 * 查看图片
	 * @author hongbin.kang
	 * @date 2016年10月24日 下午6:07:24
	 * @param id
	 * @return
	 * @throws IOException 
	 */
	public String scenePicture(@Param("id") String id) throws IOException {
		HttpServletResponse response = getMvcFacade().getResponse();
		if(!StringUtils.isEmpty(id)) {
			ZtSceneDTO ztSceneDto = ztSceneFacade.queryAllSceneInfoById(Long.valueOf(id));
			OutputStream fops = response.getOutputStream(); 
			if(null != ztSceneDto && null != ztSceneDto.getSceneIcon()) {
				fops.write(ztSceneDto.getSceneIcon());  
				fops.flush();  
				fops.close(); 
			}
		}
		return null;
	}
	
}
 