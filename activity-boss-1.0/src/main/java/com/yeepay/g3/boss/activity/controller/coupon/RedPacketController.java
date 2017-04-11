/**
 * 
 */
package com.yeepay.g3.boss.activity.controller.coupon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yeepay.g3.boss.activity.controller.ActivityBaseController;
import com.yeepay.g3.facade.activity.dto.ImportRedPacketDTO;
import com.yeepay.g3.facade.activity.enums.UsercouponStatusEnum;
import com.yeepay.g3.facade.activity.facade.ActivityUserPacketFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;

/**
 * 
 * @Description 红包发放、审核Controller
 * @author zhenping.zhou
 * @CreateTime 2016年8月2日 下午3:40:52
 * @version 1.0
 */
@Controller
@RequestMapping("/packet")
public class RedPacketController extends ActivityBaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(RedPacketController.class);

	//优惠券信息远程服务接口
//	private ActivityCouponFacade activityCouponFacadeImpl = RemoteServiceFactory
//			.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityCouponFacade.class);
//	private ActivityUserPacketFacade activityUserPacketFacadeImpl = RemoteServiceFactory
//			.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityUserPacketFacade.class);
	private ActivityUserPacketFacade activityUserPacketFacadeImpl = RemoteServiceFactory
			.getService(ActivityUserPacketFacade.class);
	
	/**
	 * 批量导入红包批次列表
	 * @return
	 */
	@RequestMapping(value = "/batchList")
	public String batchList() {
		return "packet/batchList";
	}
	
	/**
	 * 批次审核
	 * @return
	 */
	@RequestMapping(value = "/batchCheck")
	@ResponseBody
	public String batchCheck(@RequestParam(value = "batchId") String batchId,
			@RequestParam(value = "status") String status) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(batchId)) {
			
			params.put("batchId", batchId);
			if(UsercouponStatusEnum.EFFECTIVE.toString().equals(status)) {
				params.put("status", UsercouponStatusEnum.EFFECTIVE);
			} else if(UsercouponStatusEnum.CANCEL.toString().equals(status)) {
				params.put("status", UsercouponStatusEnum.CANCEL);
			}
			
			activityUserPacketFacadeImpl.userPacketCheck(params);
		}
		
		return "SUCCESS";
	}
	
	/**
	 * 当前批次红包列表查看
	 * @return
	 */
	@RequestMapping(value = "/batchRedPachetList")
	public String batchRedPachetList() {
		return "packet/batchRedPachetList";
	}
	
	/**
	 * 跳转至批量导入红包页面
	 * @return
	 */
	@RequestMapping(value = "/toBatchRedPachet")
	public String toBatchRedPachet() {
		return "packet/addGrantPacket";
	}
	
	/**
	 * 批量导入红包
	 * @return
	 */
	@RequestMapping(value = "/batchRedPacket")
	public String batchRedPacket(@RequestParam(value = "uploadFile") MultipartFile uploadFile,
			HttpSession session,HttpServletRequest request) {
		
		String uploadFileFileName = uploadFile.getOriginalFilename();
		String targetDirectory = request.getSession().getServletContext().getRealPath(File.separator);
		logger.info("batchRedPacket, targetDirectory={}" , targetDirectory);
//		File target = new File(targetDirectory, uploadFileFileName);
		
		//如果文件存在删除原有文件
//		if (target.exists()) {
//			target.delete();
//		}
//		//复制file对象,实现上传
//		try {
//			uploadFile.transferTo(target);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
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
			}
			Sheet sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			ImportRedPacketDTO redPacketDto = null;
			List<ImportRedPacketDTO> redPacketDtoList = new ArrayList<ImportRedPacketDTO>();
			String creator = this.getCurrentUser(session);

			for(int i = 1; i <= rowNum; i++) {
				Row curRow = sheet.getRow(i);
				redPacketDto = new ImportRedPacketDTO();
				redPacketDto.setCreator(creator); //操作员信息
				redPacketDto.setMemberNo(curRow.getCell(0).getStringCellValue()); //必须字符串，否则报错
				redPacketDto.setPacketName(curRow.getCell(1).getStringCellValue()); //必须字符串，否则报错
				redPacketDto.setPacketAmount(curRow.getCell(2) == null ? 
						new BigDecimal(0) : 
							new BigDecimal(curRow.getCell(2).getNumericCellValue()).setScale(2, RoundingMode.HALF_UP));//必须数字，否则报错
				redPacketDto.setValidityDays(curRow.getCell(3) == null ? 
						365 : new Double(curRow.getCell(3).getNumericCellValue()).intValue());
				redPacketDtoList.add(redPacketDto); //必须数字，否则报错
				logger.info(redPacketDto.toString());
			}
			if(redPacketDtoList != null && redPacketDtoList.size() > 0) {
				activityUserPacketFacadeImpl.batchAddUserPacket(redPacketDtoList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "packet/batchList";
	}
	
}
