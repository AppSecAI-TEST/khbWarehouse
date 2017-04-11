/**
 * 
 */
package com.yeepay.g3.boss.activity.controller.rule;

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
import com.yeepay.g3.boss.activity.controller.event.EventController;
import com.yeepay.g3.facade.activity.dto.ActivityEventDTO;
import com.yeepay.g3.facade.activity.dto.ActivityRuleDTO;
import com.yeepay.g3.facade.activity.enums.CouponRuleStatusEnum;
import com.yeepay.g3.facade.activity.facade.ActivityEventFacade;
import com.yeepay.g3.facade.activity.facade.ActivityRuleFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;

/**
 * @Description 活动规则管理Controller
 * @author zhenping.zhou
 * @CreateTime 2016年3月31日 下午4:17:55
 * @version 1.0
 */
@Controller
@RequestMapping("/rule")
public class RuleController extends ActivityBaseController {
	
//	private ActivityRuleFacade activityRuleFacadeImpl = RemoteServiceFactory
//			.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityRuleFacade.class);
//
//	private ActivityEventFacade activityEventFacadeImpl = RemoteServiceFactory
//			.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityEventFacade.class);
	
	private ActivityRuleFacade activityRuleFacadeImpl = RemoteServiceFactory
			.getService(ActivityRuleFacade.class);
	
	private ActivityEventFacade activityEventFacadeImpl = RemoteServiceFactory
			.getService(ActivityEventFacade.class);
	
	private Logger logger = LoggerFactory.getLogger(EventController.class);

	/**
	 * 查询待审核列表
	 * @return
	 */
	@RequestMapping(value = "/queryCheckingRuleList")
	public String queryCheckingRuleList() {
		return "rule/queryCheckingRuleList";
	}
	
	/**
	 * 查询所有活动事件列表
	 * @return
	 */
	@RequestMapping(value = "/queryRuleList")
	public String queryRuleList() {
		return "rule/queryRuleList";
	}

	/**
	 * 跳转至活动事件新增页面
	 * @return
	 */
	@RequestMapping(value = "/toAddRule")
	public String toAddRule(Model model) {
		List<ActivityEventDTO> eventList = activityEventFacadeImpl.getAllEventList();
		model.addAttribute("eventList", eventList);
		return "rule/addRule";
	}
	
	/**
	 * 保存活动事件
	 * @return
	 */
	@RequestMapping(value = "/addRule")
	public String addRule(@ModelAttribute ActivityRuleDTO ruleDto,
			@RequestParam(required = true, value = "events") String events,
			HttpSession session) {
		ruleDto.setCreator(this.getCurrentUser(session));
		activityRuleFacadeImpl.addRule(ruleDto, events);
		return "rule/queryRuleList";
	}

	@RequestMapping(value = "/ruleCheck")
	@ResponseBody
	public String ruleCheck(
			@RequestParam(required = true, value = "id") String id,
			@RequestParam(required = true, value = "ruleStatus") String ruleStatus,
			@RequestParam(required = true, value = "version") String version,
			HttpSession session) {
		
		ActivityRuleDTO ruleDto = new ActivityRuleDTO();
		ruleDto.setId(Long.valueOf(id));
		ruleDto.setVersion(Long.valueOf(version));
		//只有审批通过、退回、作废（暂时保留）才能调此方法
		if(CouponRuleStatusEnum.EFFECTIVE.toString().equals(ruleStatus)) {
			ruleDto.setRuleStatus(CouponRuleStatusEnum.EFFECTIVE);
		} else if(CouponRuleStatusEnum.RETURN_BACK.toString().equals(ruleStatus)) {
			ruleDto.setRuleStatus(CouponRuleStatusEnum.RETURN_BACK);
		}
		String user = this.getCurrentUser(session);
		ruleDto.setCheckor(user);
		ruleDto.setCheckedTime(new Date());
		
		activityRuleFacadeImpl.updateActivityRuleById(ruleDto);
		
		return "SUCCESS";
	}

	/**
	 * 规则查看
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/ruleDetail")
	public String ruleDetail(
			@RequestParam(required = true, value = "id") String id,
			Model model) {
		ActivityRuleDTO ruleDto = activityRuleFacadeImpl.selectRuleById(Long.valueOf(id));
		model.addAttribute("ruleDto", ruleDto);

		List<ActivityEventDTO> eventDtoList = activityEventFacadeImpl.selectByRuleId(Long.valueOf(id));
		model.addAttribute("eventDtoList", eventDtoList);
		logger.info("[ruleDetail] ruleDto={},eventDtoList={}",ruleDto,eventDtoList);
		return "rule/ruleDetail";
	}
	/**
	 * 跳转修改规则页面
	 * @param in -
	 * @return 读取到的固定长度数据
	 */
	@RequestMapping(value = "/toUpdateRule")
	public String toUpdateRule(@RequestParam(required = true, value = "id")String id,Model model){
		ActivityRuleDTO ruleDto = activityRuleFacadeImpl.selectRuleById(Long.valueOf(id));
		model.addAttribute("ruleDto",ruleDto);
		List<ActivityEventDTO> eventDtoList = activityEventFacadeImpl.selectByRuleId(Long.valueOf(id));
		StringBuilder eventList = new StringBuilder();
		for(int i = 0; i < eventDtoList.size(); i ++){
			eventList.append(eventDtoList.get(i).getId()).append(";");
		}
		model.addAttribute("eventList", eventList);
		List<ActivityEventDTO> allEventList = activityEventFacadeImpl.getAllEventList();
		model.addAttribute("allEventList", allEventList);
		
		logger.info("[toUpdateRule] ruleDto={},eventList={},allEventList={}",ruleDto,eventList,allEventList);
		return "rule/updateRule";
	}
	@RequestMapping(value = "/updateRule")
	public String updateRule(@ModelAttribute ActivityRuleDTO activityRuleDto,@RequestParam(required = true, value = "events") String events,HttpSession session){
		logger.info("[updateRule] activityRuleDto={},events={}",activityRuleDto,events);
		activityRuleDto.setCreator(this.getCurrentUser(session));
		activityRuleDto.setCreateTime(new Date());
		activityRuleDto.setCheckor("");
		activityRuleDto.setCheckedTime(null);
		//facade，修改规则
		activityRuleFacadeImpl.updateRuleById(activityRuleDto, events);
		return "rule/queryRuleList";
	}
	
}
