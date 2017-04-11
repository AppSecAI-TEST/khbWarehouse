/**
 * @author 陈大涛
 * 2016-8-31下午4:35:00
 */
package com.yeepay.g3.boss.activity.controller.goods;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yeepay.g3.boss.activity.controller.coupon.RedPacketController;
import com.yeepay.g3.facade.activity.dto.ActivityGoodsDTO;
import com.yeepay.g3.facade.activity.dto.ActivityGoodsDetailDTO;
import com.yeepay.g3.facade.activity.dto.ImportRedPacketDTO;
import com.yeepay.g3.facade.activity.enums.GoodsStatusEnum;
import com.yeepay.g3.facade.activity.facade.ActivityGoodsDetailFacade;
import com.yeepay.g3.facade.activity.facade.ActivityGoodsFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;

/**
 * @Description
 * @author 陈大涛
 * 兑换码管理
 * 2016-8-31下午4:35:00
 */
@Controller
@RequestMapping("/goodsDetail")
public class GoodsDetailController {
	private static final Logger logger = LoggerFactory.getLogger(RedPacketController.class);
//	private ActivityGoodsDetailFacade activityGoodsDetailFacadeImpl =
//			RemoteServiceFactory.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityGoodsDetailFacade.class);
	private ActivityGoodsDetailFacade activityGoodsDetailFacadeImpl = RemoteServiceFactory.getService(ActivityGoodsDetailFacade.class);
	
//	private ActivityGoodsFacade activityGoodsFacadeImpl =
//			RemoteServiceFactory.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityGoodsFacade.class);
	private ActivityGoodsFacade activityGoodsFacadeImpl = RemoteServiceFactory.getService(ActivityGoodsFacade.class);
	
	/**
	 * 批量导入兑付码列表
	 * @return
	 */
	@RequestMapping(value = "/queryRedeemCodeList")
	public String queryRedeemCodeList() {
		return "goodsDetail/redeemCodeList";
	}
	
	/**
	 * 跳转至批量导入兑付码页面
	 * @return
	 */
	@RequestMapping(value = "/toAddRedeemCodeList")
	public String toAddRedeemCodeList(Model model) {
		//查询所有有效商品列表
		ActivityGoodsDTO goodsDto= new ActivityGoodsDTO();
		goodsDto.setGoodsStatus(GoodsStatusEnum.EFFECTIVE);
		List<ActivityGoodsDTO> activityGoodsDTOList =  activityGoodsFacadeImpl.selectEffGoodsList(goodsDto);
		model.addAttribute("activityGoodsDTOList",activityGoodsDTOList);
		return "goodsDetail/addRedeemCodeList";
	}
	
	/**
	 * 批量导入兑付码
	 * @return
	 */
	@RequestMapping(value = "/addRedeemCodeList")
	public String addRedeemCodeList(@RequestParam(value = "uploadFile") MultipartFile uploadFile,
			@RequestParam(value = "goods") Long goods,
			HttpSession session,HttpServletRequest request) {
		
		String uploadFileFileName = uploadFile.getOriginalFilename();
		String targetDirectory = request.getSession().getServletContext().getRealPath(File.separator);
		logger.info("addRedeemCodeList, targetDirectory={}" , targetDirectory);
		String suffix = uploadFileFileName.substring(uploadFileFileName.lastIndexOf(".")+1);
	    Workbook wb = null;
		FileInputStream fi;
		try {
//			fi = new FileInputStream(target);
			fi = (FileInputStream) uploadFile.getInputStream();
			if("xls".equals(suffix.toLowerCase())) {// 2003
				wb = new HSSFWorkbook(fi);
			} else if("xlsx".equals(suffix.toLowerCase())){// 2007
				wb = new XSSFWorkbook(fi);		
			}else{
				logger.info("[addRedeemCodeList] 文本格式不正确 此文本格式是{}",suffix);
				return "goodsDetail/redeemCodeList";
			}
			Sheet sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			List<ActivityGoodsDetailDTO> goodsDetailDtoList = new ArrayList<ActivityGoodsDetailDTO>();

			for(int i = 1; i <= rowNum; i++) {
				Row curRow = sheet.getRow(i);
				ActivityGoodsDetailDTO activityGoodsDetailDto = new ActivityGoodsDetailDTO();
				activityGoodsDetailDto.setGoodId(goods);
				activityGoodsDetailDto.setRedeemCode(curRow.getCell(0).toString());
				goodsDetailDtoList.add(activityGoodsDetailDto); 
				logger.info(activityGoodsDetailDto.toString());
			}
			if(goodsDetailDtoList != null && goodsDetailDtoList.size() > 0) {
				activityGoodsDetailFacadeImpl.addGoodsDetailList(goodsDetailDtoList);
			}
			
		} catch (Exception e) {
			logger.error("[addRedeemCodeList] 批量导入兑付码异常e={}",e);
		}
		
		return "goodsDetail/redeemCodeList";
	}
}
