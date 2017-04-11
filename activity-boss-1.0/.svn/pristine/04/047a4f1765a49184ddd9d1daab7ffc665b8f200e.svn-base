/**
 * 
 */
package com.yeepay.g3.boss.activity.controller.flux;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yeepay.g3.boss.activity.controller.ActivityBaseController;
import com.yeepay.g3.boss.activity.controller.activity.ActivityController;
import com.yeepay.g3.facade.activity.dto.ActivityInfoDTO;
import com.yeepay.g3.facade.lmact.dto.FluxPlatDetailDto;
import com.yeepay.g3.facade.lmact.service.LMACTFluxPlatFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;


/**
 * @Description 送流浪管理Controller
 * @author hongbin.kang
 * @CreateTime 2016年6月23日 下午1:04:26
 * @version 1.0
 */
@Controller
@RequestMapping("/flux")
public class FluxController extends ActivityBaseController {
	
	//优惠券信息远程服务接口
//	private LMACTFluxPlatFacade lMACTFluxPlatFacadeImpl = RemoteServiceFactory
//	.getService("http://localhost:8080/lmact-hessian/hessian", RemotingProtocol.HESSIAN, LMACTFluxPlatFacade.class);
	private LMACTFluxPlatFacade lMACTFluxPlatFacadeImpl = RemoteServiceFactory
	.getService(LMACTFluxPlatFacade.class);
	private Logger logger = LoggerFactory.getLogger(FluxController.class);
	/**
	 * 查询所有送流量列表
	 * @return
	 */
	@RequestMapping(value = "/queryFluxList")
	public String queryFluxList() {
		return "flux/queryFluxList";
	}
	
	/**
	 * 跳转修改送流量信息
	 */
	@RequestMapping(value = "/toModifyFlux")
	public String toModifyFlux(Model model,@RequestParam(required = true, value = "id") Long id) {
		FluxPlatDetailDto  fluxPlatDetailDto = lMACTFluxPlatFacadeImpl.queryFluxPlatDetailById(id);
		model.addAttribute("fluxPlatDetailDto",fluxPlatDetailDto);
		return "flux/modifyFluxDetail";
	}
	
	/**
	 * 保存修改送流量信息
	 */
	@RequestMapping(value = "/modifyFlux")
	public String modifyFlux(@ModelAttribute FluxPlatDetailDto fluxPlatDetailDto) {
		try {
			lMACTFluxPlatFacadeImpl.updateFluxPlatDetail(fluxPlatDetailDto);
		} catch (Exception e) {
			logger.error("[modifyFlux] error={}",e);
		}
		
		return "flux/queryFluxList";
	}
	
}
