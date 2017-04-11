/**
 * 
 */
package com.yeepay.g3.boss.activity.controller.invforproRule;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import com.yeepay.g3.boss.activity.controller.ActivityBaseController;
import com.yeepay.g3.facade.activity.dto.ActivityInvForProRuleXTDTO;
import com.yeepay.g3.facade.activity.facade.ActivityInvForProRuleFacade;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;

/**
 * @Description 投资换产品规则管理Controller
 * @author hongbin.kang
 * @CreateTime 2016年7月27日 下午1:04:26
 * @version 1.0
 */
@Controller
@RequestMapping("/invForProRule")
public class InvForProRuleController extends ActivityBaseController {
	
	//投资换产品规则信息远程服务接口
//	private ActivityInvForProRuleFacade activityInvForProRuleFacadeImpl = RemoteServiceFactory
//			.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityInvForProRuleFacade.class);
	private ActivityInvForProRuleFacade activityInvForProRuleFacadeImpl = RemoteServiceFactory
			.getService(ActivityInvForProRuleFacade.class);
	

	/**
	 * 跳转至投资换产品规则新增页面
	 * @return
	 */
	@RequestMapping(value = "/toAddInvForProRule")
	public String toAddInvForProRule() {
		return "invForProRule/addInvForProRule";
	}
	
	/**
	 * 保存投资换产品规则
	 * @return
	 */
	@RequestMapping(value = "/addInvForProRule")
	public String addInvForProRule(@ModelAttribute ActivityInvForProRuleXTDTO invForProRuleDto,
			HttpSession session,HttpServletRequest requset , HttpServletResponse response) {
		if(null != invForProRuleDto) {
			invForProRuleDto.setCreater(this.getCurrentUser(session));
			invForProRuleDto.setCreateTime(new Date());
			activityInvForProRuleFacadeImpl.addActivityInvForProRule(invForProRuleDto);
		}
		return "invForProRule/queryInvForProRuleList";
	}
	
	/**
	 * 查询所有投资换产品规则列表
	 * @return
	 */
	@RequestMapping(value = "/queryInvForProRuleList")
	public String queryInvForProRuleList() {
		return "invForProRule/queryInvForProRuleList";
	}
	
	/**
	 * 查看投资换产品规则的详情
	 */
	@RequestMapping(value = "/invForProRuleDetail")
	public String InvForProRuleInfoDetail(@RequestParam(required = true , value = "id") String id , Model model) {
		ActivityInvForProRuleXTDTO InvForProRuleInfoDto = new ActivityInvForProRuleXTDTO();
		InvForProRuleInfoDto = activityInvForProRuleFacadeImpl.selectInvForProRuleById(Long.valueOf(id));
		model.addAttribute("InvForProRuleInfoDto", InvForProRuleInfoDto);
		return "invForProRule/invForProRuleDetail";
	}
	
	
	/**
	 * 查询待审核列表
	 * @return
	 */
	@RequestMapping(value = "/queryCheckingInvForProRuleList")
	public String queryCheckingInvForProRuleList() {
		return "invForProRule/checkingInvForProRuleList";
	}
	
	/**
	 * 投资换产品规则审核
	 * @param id
	 * @param InvForProRuleInfoStatus
	 * @param version
	 * @param session
	 */
	@RequestMapping(value = "/invForProRuleCheck")
	@ResponseBody
	public String InvForProRuleCheck (@RequestParam(required = true , value = "id") String id ,
			@RequestParam (required = true , value = "version") String version,
			@RequestParam (required = true , value = "status") String status ,
			HttpSession session){
		ActivityInvForProRuleXTDTO InvForProRuleInfoDto = new ActivityInvForProRuleXTDTO();
		//id、version为必传项
		InvForProRuleInfoDto.setId(Long.valueOf(id));
		InvForProRuleInfoDto.setVersion(Long.valueOf(version));
		activityInvForProRuleFacadeImpl.updateActivityInvForProRuleById(InvForProRuleInfoDto);
		return "SUCCESS";
	}
	
	/**
	 * 去修改投资换产品规则页面
	 */
	@RequestMapping(value = "/toModifyInvForProRuleInfo")
    public String toModifyInvForProRuleInfo(@RequestParam(required = true , value = "id") String id , Model model) {
		ActivityInvForProRuleXTDTO InvForProRuleInfoDto = new ActivityInvForProRuleXTDTO();
		if(null != id) {
			InvForProRuleInfoDto = activityInvForProRuleFacadeImpl.selectInvForProRuleById(Long.valueOf(id));
		}
		model.addAttribute("InvForProRuleInfoDto", InvForProRuleInfoDto);
		return "invForProRule/modifyInvForProRule";
	}
	
	
	/**
	 * 修改投资换产品规则
	 */
	@RequestMapping(value = "/modifyInvForProRuleInfo")
	public String modifyInvForProRuleInfo(@ModelAttribute ActivityInvForProRuleXTDTO InvForProRuleInfoDto,
			MultipartRequest request) {
		if(null != InvForProRuleInfoDto.getId() && null != InvForProRuleInfoDto.getVersion()) {
			activityInvForProRuleFacadeImpl.updateActivityInvForProRuleById(InvForProRuleInfoDto);
		}
		return "invForProRule/checkingInvForProRuleList";
	}
	
}
