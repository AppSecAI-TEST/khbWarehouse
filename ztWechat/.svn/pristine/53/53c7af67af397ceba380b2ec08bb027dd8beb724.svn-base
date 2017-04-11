package com.yeepay.g3.app.lmweChat.action.seckillActivity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yeepay.g3.app.dto.LoginResultDTO;
import com.yeepay.g3.app.enums.LoginResultEnum;
import com.yeepay.g3.app.lmweChat.action.BaseAction;
import com.yeepay.g3.app.lmweChat.biz.UserBiz;
import com.yeepay.g3.app.lmweChat.biz.impl.UserBizImpl;
import com.yeepay.g3.facade.lmlc.trust.dto.SeckillHistoryListDto;
import com.yeepay.g3.facade.lmlc.trust.dto.SeckillProductHistoryParamDto;
import com.yeepay.g3.facade.lmlc.trust.dto.SeckillProductHistoryResultDto;
import com.yeepay.g3.facade.lmlc.trust.dto.product.ProductDetailResultDto;
import com.yeepay.g3.facade.lmlc.trust.service.FiQueryFacade;
import com.yeepay.g3.facade.lmportal.dto.AccountInfoQueryResultDto;
import com.yeepay.g3.facade.lmportal.dto.BankCardInfoDto;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.facade.lmportal.service.LPQueryFacade;
import com.yeepay.g3.facade.lmportal.service.LanmaoDemandFacade;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.web.emvc.annotation.Param;
/**
 * @Title: 秒杀活动
 * @Description: 秒杀活动相关action
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016年10月23日 下午12:05:01
 * @version 2016年10月23日
 */
@Controller
public class SeckillActivityAction extends BaseAction {
	
	private Logger logger = LoggerFactory.getLogger(SeckillActivityAction.class);
	
	private FiQueryFacade fiQueryFacade = RemoteServiceFactory.getService(FiQueryFacade.class);
	
	protected LanmaoDemandFacade lanmaoDemandFacade = RemoteServiceFactory.getService(LanmaoDemandFacade.class);
	
	protected LPQueryFacade lPQueryFacade = RemoteServiceFactory.getService(LPQueryFacade.class);
	
	@Autowired
	private UserBiz userBizImpl;

	/*public void setUserBizImpl(UserBizImpl userBizImpl) {
		this.userBizImpl = userBizImpl;
	}*/
	
	public String toSeckillPro(@Param("loginName") String loginName,
			@Param("srcNo") String srcNo,@Param("userSessionKey")String userSessionKey){
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		BigDecimal accountAmount = BigDecimal.ZERO;
		try{
			//如果session里没有就从redis里取
			if(null==memberDto&&StringUtils.isNotEmpty(loginName)&&StringUtils.isNotEmpty(srcNo)&&StringUtils.isNotEmpty(userSessionKey)){
				LoginResultDTO loginResultDto=userBizImpl.obtainLogin(loginName, srcNo, userSessionKey);
				logger.info("[toSeckillPro] loginResultDto={}",loginResultDto);
				if(null!=loginResultDto&&LoginResultEnum.SUCCESS.equals(loginResultDto.getResultMsg())){
					memberDto=loginResultDto.getMemberDto();
					session.setAttribute("member", memberDto);
				}
			}
		}catch(Exception e){
			logger.info("[toSeckillPro] info={},ERROR={}","验证app登陆失败",e);
		}
		
		logger.info("[toSeckillPro] memberDto={}",memberDto);
		//1.根据产品id查询产品信息，将产品信息传入前端页面
		ProductDetailResultDto pdr =  fiQueryFacade.obtainSeckillProductDetail();
		logger.info("[toSeckillPro] productDetailResultDto={}",pdr);
		addModelObject("pdr", pdr);
		//2.计算账户余额
		if (null != memberDto) {
			addModelObject("noLogin", "NO");
			// 查询账户余额
			AccountInfoQueryResultDto aiqrDto = lanmaoDemandFacade.queryAccount(memberDto.getMemberNo(), new Date());
			if(aiqrDto != null){
				accountAmount = aiqrDto.getAmount();
				if(accountAmount == null){
					accountAmount = BigDecimal.ZERO;
				}
			}
			addModelObject("accountAmount",accountAmount);
			//APP端已登录用户判断是否绑卡
			//1-未绑卡跳去原生绑卡页面，然后去h5充值页面 
			//2-已绑卡的和微信端一样跳转去充值页面
			String platform=(String) session.getAttribute("platform");
			logger.info("[toSeckillPro] platform={}",platform);
			if(null!=memberDto&&StringUtils.isNotEmpty(platform)&&"APP".equals(platform)){
				try {
					BankCardInfoDto bankCardInfoDto = lPQueryFacade.obtainDefaultBankCardInfo(memberDto.getMemberNo());
					// 说明未绑卡
					if (bankCardInfoDto == null) {
						addModelObject("isBankCard", "NO");
					}
					logger.info("[toSeckillPro] bankCardInfoDto={}",bankCardInfoDto);
				} catch (Exception e) {
					logger.error("[toSeckillPro] info={},ERROR={}", "去产品信息页查询绑卡信息时异常",
							e.getMessage());
				}
			}
		}else{
			//未登录
			addModelObject("noLogin", "YES");
		}
		//3.产品剩余投资额度
		BigDecimal surplusAmount = pdr.getSaleAbleAmount().subtract(pdr.getSaledAmount());
		addModelObject("surplusAmount", surplusAmount);
		
		/*if(pdr != null){
			addModelObject("pdr", pdr);
			//获取目前的状态
			String status = GetParamUtils.readSeckillFlag();
			//倒计时
			if("status1".equals(status)){
				return "seckillper";
			}
			//秒杀中或者秒杀完
			else if("status2".equals(status)){
				//售完
				if(ProductStatusEnum.SALE_FINISH.equals(pdr.getStatus())){
					return "seckillfinish";
				}
				return "seckilling";
			}
		}else{
			//售完
			return "seckillfinish";
		}*/
		return SUCCESS;
	}
	
	public String toSeckillRecord(){
		/*SeckillProductHistoryParamDto seckillParamDto = new SeckillProductHistoryParamDto();
		seckillParamDto.setPageIndex(1);
		seckillParamDto.setPageSize(5);
		SeckillProductHistoryResultDto resultDto = fiQueryFacade.querySeckillProductHistoryList(seckillParamDto);
		logger.info("[toSeckillRecord] resultDto={}",resultDto.getSeckillHistoryListDto());
		if(resultDto == null || resultDto.getSeckillHistoryListDto() == null || resultDto.getSeckillHistoryListDto().size() == 0){
			resultDto = new SeckillProductHistoryResultDto();
			resultDto.setTotalNum(0);
			resultDto.setTotalAmount(BigDecimal.ZERO);
		}
		addModelObject("resultDto", resultDto);*/
		return SUCCESS;
	}
	
	public String querySeckillRecord(@Param("pageIndex") int pageIndex){
		SeckillProductHistoryParamDto seckillParamDto = new SeckillProductHistoryParamDto();
		seckillParamDto.setPageIndex(pageIndex);
		seckillParamDto.setPageSize(PAGE_SIZE);
		SeckillProductHistoryResultDto resultDto = fiQueryFacade.querySeckillProductHistoryList(seckillParamDto);
		logger.info("[toSeckillRecord] resultDto={}",resultDto.getSeckillHistoryListDto());
		if(resultDto == null || resultDto.getSeckillHistoryListDto() == null || resultDto.getSeckillHistoryListDto().size() == 0){
			resultDto = new SeckillProductHistoryResultDto();
			resultDto.setTotalNum(0);
			resultDto.setTotalAmount(BigDecimal.ZERO);
			resultDto.setSeckillHistoryListDto(new ArrayList<SeckillHistoryListDto>());
		}
		//测试
		/*private String productId;
		private String productName;
		private String periodNo;
		private Date saleStart;
		private Date saleEnd;
		private BigDecimal seckillAmount;
		private int seckillNum;*/
		/*List<SeckillHistoryListDto> seckillHistoryList = new ArrayList<SeckillHistoryListDto>();
		SeckillHistoryListDto shl = new SeckillHistoryListDto();
		shl.setProductId("181");
		shl.setProductName("秒客盈");
		shl.setPeriodNo("2016-10-10");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date saleStart = null;
		Date saleEnd = null;
		try {
			saleStart = sdf.parse("2016-11-16 00:00:00");
			saleEnd = sdf.parse("2016-11-16 23:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shl.setSaleStart(saleStart);
		shl.setSaleEnd(saleEnd);
		shl.setSeckillAmount(new BigDecimal(1000));
		shl.setSeckillNum(23);
		seckillHistoryList.add(shl);
		resultDto.setSeckillHistoryListDto(seckillHistoryList);*/
		
		setJsonModel(resultDto);
		return "json";
	}
}
