/**
 * 
 */
package com.yeepay.g3.boss.activity.controller.raffletickets;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeepay.g3.boss.activity.controller.ActivityBaseController;
import com.yeepay.g3.boss.activity.controller.activity.ActivityController;
import com.yeepay.g3.facade.activity.dto.ActivityRaffleTicketDTO;
import com.yeepay.g3.facade.activity.enums.CouponStatusEnum;
import com.yeepay.g3.facade.activity.enums.RaffleTicketStatusEnum;
import com.yeepay.g3.facade.activity.facade.ActivityRaffleTicketFacade;
import com.yeepay.g3.facade.activity.facade.ActivityUserRaffleTicketFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;

/**
 * @Description 抽奖券管理Controller
 * @author hongbin.kang
 * @CreateTime 2016年5月16日 下午1:04:26
 * @version 1.0
 */
@Controller
@RequestMapping("/raffle")
public class RaffleTicketsController extends ActivityBaseController {
	
	//抽奖券信息远程服务接口
//	@Resource
	/*private ActivityRaffleTicketFacade activityRaffleTicketFacadeImpl = RemoteServiceFactory
			.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityRaffleTicketFacade.class);
	private ActivityUserRaffleTicketFacade activityUserRaffleTicketFacadeImpl = RemoteServiceFactory
			.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityUserRaffleTicketFacade.class);*/
	private ActivityRaffleTicketFacade activityRaffleTicketFacadeImpl = RemoteServiceFactory
			.getService(ActivityRaffleTicketFacade.class);
	private ActivityUserRaffleTicketFacade activityUserRaffleTicketFacadeImpl = RemoteServiceFactory
			.getService(ActivityUserRaffleTicketFacade.class);
	
		private Logger logger = LoggerFactory.getLogger(ActivityController.class);
	/**
	 * 跳转抽奖券的页面
	 * 
	 */
	@RequestMapping(value = "/toAddRaffleTicket")
	public String toAddRaffleTicket() {
		return "raffleTicket/addRaffleTicket";
	}
	
	/**
	 * 保存抽奖券
	 * @return
	 */
	@RequestMapping(value = "/addRaffleTicket")
	public String addRaffleTicket(
			@ModelAttribute ActivityRaffleTicketDTO activityRaffleTicketDto,
			HttpSession session) {
		activityRaffleTicketDto.setCreator(this.getCurrentUser(session));
		activityRaffleTicketFacadeImpl.addActivityRaffleTicket(activityRaffleTicketDto);

		return "raffleTicket/queryRaffleTicketList";
	}
	
	
	/**
	 * 查询所有优惠券列表
	 * @return
	 */
	@RequestMapping(value = "/queryRaffleTicketList")
	public String queryRaffleTicketList() {
		return "raffleTicket/queryRaffleTicketList";
	}
	
	/**
	 * 查询待审核列表
	 * @return
	 */
	@RequestMapping(value = "/queryCheckingRaffleTicketList")
	public String queryCheckingRaffleTicketList() {
		return "raffleTicket/checkingRaffleTicketList";
	}
	
	/**
	 * 查看抽奖券的详情
	 */
	@RequestMapping(value = "/raffleTicketDetail")
	public String raffleTicketDetail(@RequestParam(required = true, value = "id") String id , Model model) {
		ActivityRaffleTicketDTO raffleTicketDto = activityRaffleTicketFacadeImpl.selectRaffleTicketById(Long.valueOf(id));
		model.addAttribute("raffleTicketDto", raffleTicketDto);
		
		return "raffleTicket/raffleTicketDetail";
	}
	
	
	
	
	/**
	 * 抽奖券审核功能
	 * @param id
	 * @param couponStatus
	 * @param version
	 * @param operateType
	 * @param ruleDesc
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/raffleTicketCheck")
	@ResponseBody
	public String raffleTicketCheck(
			@RequestParam(required = true, value = "id") String id,
			@RequestParam(required = true, value = "couponStatus") String couponStatus,
			@RequestParam(required = true, value = "version") String version,
			@RequestParam(value = "operateType") String operateType,
			@RequestParam(value = "ruleDesc") String ruleDesc,
			HttpSession session) {
		
		ActivityRaffleTicketDTO raffleTicketDto = new ActivityRaffleTicketDTO();
		raffleTicketDto.setId(Long.valueOf(id));
		raffleTicketDto.setVersion(Long.valueOf(version));
		//只有审批通过、退回、作废（暂时保留）才能调此方法
		if(CouponStatusEnum.EFFECTIVE.toString().equals(couponStatus)) {
			raffleTicketDto.setCouponStatus(RaffleTicketStatusEnum.EFFECTIVE);
		} else if(CouponStatusEnum.RETURN_BACK.toString().equals(couponStatus)) {
			raffleTicketDto.setCouponStatus(RaffleTicketStatusEnum.RETURN_BACK);
		}
		String user = this.getCurrentUser(session);
		raffleTicketDto.setCheckor(user);
		raffleTicketDto.setCheckedTime(new Date());
		
		activityRaffleTicketFacadeImpl.updateActivityRaffleTicketById(raffleTicketDto);
		
		return "SUCCESS";
	}
	
	/**
	 * 去修改抽奖券页面
	 */
	@RequestMapping(value = "/toModifyRaffleTicket")
	public String toModifyRaffleTicket(@RequestParam(required = true, value = "id") String id , Model model) {
		ActivityRaffleTicketDTO raffleTicketDto = activityRaffleTicketFacadeImpl.selectRaffleTicketById(Long.valueOf(id));
		model.addAttribute("raffleTicketDto", raffleTicketDto);
		
		return "raffleTicket/modifyRaffleTicket";
	}
	
	/**
	 * 修改抽奖券
	 */
	@RequestMapping(value = "/modifyRaffleTicket")
	public String modifyRaffleTicket(@ModelAttribute ActivityRaffleTicketDTO raffleTicketDto) {
		if(null != raffleTicketDto.getId() && null != raffleTicketDto.getVersion()) {
			activityRaffleTicketFacadeImpl.updateActivityRaffleTicketById(raffleTicketDto);
		}
		return "raffleTicket/queryRaffleTicketList";
	}

	/**
	 * 查询会员操作记录列表
	 * @return
	 */
	@RequestMapping(value = "/queryMemberRecordList")
	public String queryMemberRecordList() {
		return "raffleTicket/queryMemberRecordList";
	}
	
	/**
	 * 手工触发补发抽奖券
	 */
	@RequestMapping(value = "/userReceiveRaffleTicket")
	@ResponseBody
	public String userReceiveRaffleTicket(@RequestParam(required = true, value = "id") String id) {
		
		Long userRaffleTicketId = Long.valueOf(id);
		activityRaffleTicketFacadeImpl.updateUserRaffleTicket(userRaffleTicketId);
		
		return "SUCCESS";
	}
	
	/**
	 * 去批量发送抽奖券页面
	 * @author 陈大涛
	 * 2016-7-20下午2:41:25
	 */
	@RequestMapping(value="/toSendRaffleTicketList")
	public String toSendRaffleTicketList(Model model){
		ActivityRaffleTicketDTO activityRaffleTicketDto =new ActivityRaffleTicketDTO();
		activityRaffleTicketDto.setCouponStatus(RaffleTicketStatusEnum.EFFECTIVE);
		List<ActivityRaffleTicketDTO>  result = activityRaffleTicketFacadeImpl.selectEffRaffleTicketList(activityRaffleTicketDto);
		model.addAttribute("result", result);
		return "raffleTicket/sendRaffleTicketList";
	}
	/**
	 * 批量发放抽奖券
	 * @author 陈大涛
	 * 2016-7-21上午9:15:50
	 */
	@RequestMapping(value = "/sendRaffleTicketList")
	@ResponseBody
	public String sendRaffleTicketList(HttpSession session, @RequestParam(required = true, value = "memberNoList") String memberNoList,@RequestParam(required = true, value = "num") Integer num
			,@RequestParam(required = true, value = "raffleTicketId") Long raffleTicketId,@RequestParam(required = true, value = "version") Long version,@RequestParam(required = true, value = "grantCount") Integer grantCount
			,@RequestParam(required = true, value = "actionCode") String actionCode,@RequestParam(required = true, value = "activityCode") String activityCode){
		try {
			logger.info("[drawPrize] info={}","抽奖内定操作人员====="+this.getCurrentUser(session));
			activityUserRaffleTicketFacadeImpl.addActivityUserRaffleTicketList(memberNoList, num, raffleTicketId, version, grantCount, actionCode, activityCode);
		} catch (Exception e) {
			if("orgExceptionType:class java.lang.Exception;orgExceptionInfo:NOACTIVITY".equals(e.getMessage())){
				return "NOACTIVITY";
			}else if("orgExceptionType:class java.lang.Exception;orgExceptionInfo:NOMEMBER".equals(e.getMessage())){
				return "NOMEMBER";
			}else{
				logger.error("[sendRaffleTicketList] info={}",e.getMessage());
				return "SYSTEM";
			}
		}
		
		return "SUCCESS";
	}
}
