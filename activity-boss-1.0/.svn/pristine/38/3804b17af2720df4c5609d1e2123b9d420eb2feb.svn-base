package com.yeepay.g3.boss.activity.controller.srcFlowRule;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yeepay.g3.boss.activity.controller.ActivityBaseController;
import com.yeepay.g3.facade.activity.dto.ActivitySrcFlowRuleDTO;
import com.yeepay.g3.facade.activity.facade.ActivitySrcFlowRuleFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
/**
 * @Title: 渠道发放流量规则管理controller
 * @Description: 渠道发放流量规则管理
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-7-20 下午2:50:08
 * @version 2016-7-20
 */
@Controller
@RequestMapping(value = "srcFlowRule")
public class SrcFlowRuleController extends ActivityBaseController{
	
	private ActivitySrcFlowRuleFacade activitySrcFlowRuleFacadeImpl = RemoteServiceFactory.getService(ActivitySrcFlowRuleFacade.class);
	
	
	private final static Logger logger = LoggerFactory.getLogger(SrcFlowRuleController.class);
	/**
	 * 查询渠道发放流量规则列表
	 * @param in -
	 * @return 
	 */
	@RequestMapping(value = "querySrcFlowRuleList")
	public String querySrcFlowRuleList(){
		logger.info("SrcFlowRuleController:[querySrcFlowRuleList] info={}","查询渠道发放流量规则列表");
		return "srcFlowRule/querySrcFlowRuleList";
	}
	
	/**
	 * 跳转添加规则页面
	 * @param in -
	 * @return 
	 */
	@RequestMapping(value = "toAddSrcFlowRule")
	public String toAddSrcFlowRule(){
		return "srcFlowRule/addSrcFlowRule";
	}
	
	/**
	 * 添加渠道发放流量规则
	 * @param in -
	 * @return 
	 */
	@RequestMapping(value = "addSrcFlowRule")
	public String addSrcFlowRule(
			@ModelAttribute ActivitySrcFlowRuleDTO activitySrcFlowRuleDTO,
			HttpSession session){
		logger.info("SrcFlowRuleController:[addSrcFlowRule] activitySrcFlowRuleDTO={}",activitySrcFlowRuleDTO);
		String operator = getCurrentUser(session);
		activitySrcFlowRuleDTO.setOperator(operator);
		try{
			activitySrcFlowRuleFacadeImpl.addSrcFlowRule(activitySrcFlowRuleDTO);
		}catch(Exception e){
			logger.error("SrcFlowRuleController:[adSrcFlowRule] ERROR={}",e);
		}
		
		
		return "srcFlowRule/querySrcFlowRuleList";
	}
	
	/**
	 * 跳转修改渠道发放流量规则
	 * @param in - id
	 * @return 
	 */
	@RequestMapping(value = "toUpdateSrcFlowRule")
	public String toUpdateSrcFlowRule(Model model,@RequestParam(required = true, value = "id") Long id){
		logger.info("SrcFlowRuleController:[toUpdateSrcFlowRule] id={}",id);
		ActivitySrcFlowRuleDTO activitySrcFlowRuleDTO = activitySrcFlowRuleFacadeImpl.selectSrcFlowRuleById(id);
		model.addAttribute("activitySrcFlowRuleDTO",activitySrcFlowRuleDTO);
		return "srcFlowRule/updateSrcFlowRule";
	}
	
	/**
	 * 修改渠道发放流量规则
	 * @param in - ActivitySrcFlowRuleDTO
	 * @return 
	 */
	@RequestMapping(value = "updateSrcFlowRule")
	public String updateSrcFlowRule(@ModelAttribute ActivitySrcFlowRuleDTO activitySrcFlowRuleDTO,HttpSession session){
		logger.info("SrcFlowRuleController:[updateSrcFlowRule] activitySrcFlowRuleDTO={}",activitySrcFlowRuleDTO);
		activitySrcFlowRuleDTO.setLastUpdateTime(new Date());
		activitySrcFlowRuleDTO.setOperator(getCurrentUser(session));
		try{
			activitySrcFlowRuleFacadeImpl.updateSrcFlowRule(activitySrcFlowRuleDTO);
		}catch(Exception e){
			logger.error("SrcFlowRuleController:[updateSrcFlowRule] ERROR={}",e);
		}
		
		return "srcFlowRule/querySrcFlowRuleList";
	}
	
	/**
	 * 删除渠道发放流量规则
	 * @param in - id
	 * @return 
	 */
	@RequestMapping(value = "deleteSrcFlow")
	public String deleteSrcFlow(Model model,@RequestParam(required = true, value = "id") Long id){
		logger.info("SrcFlowRuleController:[toUpdateSrcFlowRule] id={}",id);
		try{
			activitySrcFlowRuleFacadeImpl.deleteSrcFlowRule(id);
		}catch(Exception e){
			logger.error("SrcFlowRuleController:[deleteSrcFlow] ERROR={}",e);
		}
		
		return "srcFlowRule/querySrcFlowRuleList";
	}
}
