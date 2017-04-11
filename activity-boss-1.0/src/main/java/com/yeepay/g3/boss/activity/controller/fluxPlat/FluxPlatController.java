package com.yeepay.g3.boss.activity.controller.fluxPlat;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yeepay.g3.boss.activity.controller.ActivityBaseController;
import com.yeepay.g3.facade.activity.dto.ActivitySrcFlowPlatDTO;
import com.yeepay.g3.facade.activity.facade.ActivitySrcFlowPlatFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;

/**
 * @Description 流量通道规则Controller
 * @author ping.zhu
 * @CreateTime 2016年7月21日 下午17:05:14
 * @version 1.0
 */

@Controller
@RequestMapping("/fluxPlat")
public class FluxPlatController extends ActivityBaseController{
	
	private Logger logger = LoggerFactory.getLogger(FluxPlatController.class);

	
//	private ActivitySrcFlowPlatFacade activitySrcFlowPlatFacadeImpl = RemoteServiceFactory
//			.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivitySrcFlowPlatFacade.class);
	private ActivitySrcFlowPlatFacade activitySrcFlowPlatFacadeImpl = RemoteServiceFactory
	.getService(ActivitySrcFlowPlatFacade.class);
	
	//2016-07-25 11:27:46,959 - com.yeepay.g3.utils.rmi.ClientRemoteProxyHandler -53917 [http-bio-8004-exec-5] ERROR  - 远程调用失败:orgExceptionType:class com.caucho.hessian.client.HessianConnectionException;orgExceptionInfo:HessianProxy cannot connect to 'http://172.19.60.87:8080/activity-hessian/hessian/ActivitySrcFlowPlatFacade
	/**
	 * 去新增流量通道规则页面
	 * @return
	 */
	@RequestMapping(value="/toAddFluxPlat")
	public String toAddFluxPlat(){
		return "fluxPlat/toAddFluxPlat";
	}
	
	/**
	 * 新增流量通道规则
	 * @param flowPlatDto
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/addSrcFlowPlat")
	public String addSrcFlowPlat(@ModelAttribute ActivitySrcFlowPlatDTO flowPlatDto,HttpSession session){
		try {
			flowPlatDto.setOperator(this.getCurrentUser(session));
			activitySrcFlowPlatFacadeImpl.addSrcFlowPlat(flowPlatDto);
		} catch (Exception e) {
			logger.error("[addSrcFlowPlat] error={}",e);
		}
		return "fluxPlat/toQueryFluxPlat";
	}
	
	/**
	 * 去查询所有流量通道规则列表
	 * @return
	 */
	@RequestMapping(value="toQuerySrcFlowPlatList")
	public String toQuerySrcFlowPlatList(){
		return "fluxPlat/toQueryFluxPlat";
	}
	
	/**
	 * 去查询流量通道规则详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="toQuerySrcFlowPlatDetail")
	public String toQuerySrcFlowPlatDetail(@RequestParam(required = true , value = "id") String id , Model model){
		try {
			ActivitySrcFlowPlatDTO srcFlowPlatDTO=new ActivitySrcFlowPlatDTO();
			if(null!=id){
			srcFlowPlatDTO=activitySrcFlowPlatFacadeImpl.selectSrcFlowPlat(Long.valueOf(id));
			}
			model.addAttribute("srcFlowPlatDTO",srcFlowPlatDTO);
		} catch (Exception e) {
			logger.error("[toQuerySrcFlowPlatDetail] error={}",e);
		}
		return "fluxPlat/toQueryFluxPlat";
	}
	
	/**
	 * 去修改流量通道规则页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="toModifySrcFlowPlat")
	public String toModifySrcFlowPlat(@RequestParam(required = true , value = "id") String id , Model model){
		try {
			ActivitySrcFlowPlatDTO srcFlowPlatDTO=new ActivitySrcFlowPlatDTO();
			if(null!=id){
			srcFlowPlatDTO=activitySrcFlowPlatFacadeImpl.selectSrcFlowPlat(Long.valueOf(id));
			}
			model.addAttribute("srcFlowPlatDTO",srcFlowPlatDTO);
		} catch (Exception e) {
			logger.error("[toModifySrcFlowPlat] error={}",e);
		}
		return "fluxPlat/toModifyFluxPlat";
	}
	
	/**
	 * 修改流量通道规则
	 * @return
	 */
	@RequestMapping(value="modifySrcFlowPlat")
	public String modifySrcFlowPlat(@ModelAttribute ActivitySrcFlowPlatDTO flowPlatDto,HttpSession session){
		try {
			flowPlatDto.setOperator(this.getCurrentUser(session));
			flowPlatDto.setLastUpdateTime(new Date());
			activitySrcFlowPlatFacadeImpl.updateSrcFlowPlat(flowPlatDto);
		} catch (Exception e) {
			logger.error("[modifySrcFlowPlat] error={}",e);
		}
		return "fluxPlat/toQueryFluxPlat";
	}
	
	/**
	 * 删除流量通道规则
	 * @return
	 */
	@RequestMapping(value="deleteSrcFlowPlat")
	public String deleteSrcFlowPlat(@RequestParam(required = true , value = "id") String id ){
		try {
			if(null!=id){
			activitySrcFlowPlatFacadeImpl.deleteSrcFlowPlat(Long.valueOf(id));
			}
		} catch (Exception e) {
			logger.error("[deleteSrcFlowPlat] error={}",e);
		}
		return "fluxPlat/toQueryFluxPlat";
	}
}
