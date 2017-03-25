package com.yeepay.g3.hessian.activity.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.yeepay.g3.core.activity.dao.ActivityShareRecordsDao;
import com.yeepay.g3.core.activity.entity.ActivityShareRecords;
import com.yeepay.g3.facade.activity.dto.ActivityShareRecordsDTO;
import com.yeepay.g3.facade.activity.dto.ShareRecordRespDTO;
import com.yeepay.g3.facade.activity.enums.CouponUseResultCodeEnum;
import com.yeepay.g3.facade.activity.enums.ShareBizTypeEnum;
import com.yeepay.g3.facade.activity.facade.ActivityShareRecordsFacade;
import com.yeepay.g3.hessian.activity.util.ApplicationContextUtil;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;


public class ActivityShareRecordsImplTest {

	private ActivityShareRecordsDao activityShareRecordsDaoImpl;
	private ActivityShareRecordsFacade activityShareRecordsFacadeImpl;
	@Before
    public void setUp() throws Exception {
    	
	//	RemoteServiceFactory.init();

        ApplicationContext ctx = ApplicationContextUtil.getInstence().getApplicationContext();
     //   activityShareRecordsDaoImpl = (ActivityShareRecordsDao) ctx.getBean("activityShareRecordsDaoImpl");
        activityShareRecordsFacadeImpl = (ActivityShareRecordsFacade)ctx.getBean("activityShareRecordsFacadeImpl");
    }
	/**
	 * 测试添加记录
	 * @param in -
	 * @return 读取到的固定长度数据
	 */
//	@Test
	public void testAddRecord() throws Exception{
		ActivityShareRecords activityShareRecords = new ActivityShareRecords();
		activityShareRecords.setMemberNo("811234568846");
		activityShareRecords.setMemberTel("15700000036");
		activityShareRecords.setRecommendMemberNo("811234567401");
		activityShareRecords.setRecommendMemberTel("18844195616");
		activityShareRecords.setSrcType("");
		activityShareRecords.setSrcNo("");
		activityShareRecords.setBizType(ShareBizTypeEnum.ALL);
		Long id = activityShareRecordsDaoImpl.insert(activityShareRecords);
	//	activityShareRecordsDaoImpl.add(activityShareRecords);
		
		System.out.println(id);
	}
//	@Test
	public void testselectRecordById() throws Exception{
		ActivityShareRecords activityShareRecords = (ActivityShareRecords) activityShareRecordsDaoImpl.queryOne("selectByPrimaryKey", 1);
		System.out.println(activityShareRecords);
		System.out.println(activityShareRecords.getMemberNo());
	}
//	@Test
	public void testselectByParams() throws Exception{
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("memberNo", "811234568845");
	//	ActivityShareRecords activityShareRecords = activityShareRecordsDaoImpl.selectByPrimaryKey(Long.valueOf("1"));
		List<ActivityShareRecords> list = activityShareRecordsDaoImpl.query("selectByParams", params);
		System.out.println(list.get(0));
		System.out.println(list.get(0).getMemberNo());
	}
	
	@Test
	public void testfacade() throws Exception{
		ActivityShareRecordsDTO activityShareRecordDto = new ActivityShareRecordsDTO();
		activityShareRecordDto.setMemberNo("811234568841");
		activityShareRecordDto.setMemberTel("15700000037");
		activityShareRecordDto.setRecommendMemberNo("811234567401");
		activityShareRecordDto.setRecommendMemberTel("18844195616");
		activityShareRecordDto.setSrcType("");
		activityShareRecordDto.setSrcNo("");
		activityShareRecordDto.setBizType(ShareBizTypeEnum.ALL);
		ShareRecordRespDTO resDto = activityShareRecordsFacadeImpl.addShareRecord(activityShareRecordDto);
		
		System.out.println(resDto);
	}
	
	@Test
	public void testFacadeSelect() throws Exception{
		ActivityShareRecordsDTO activityShareRecordDto = new ActivityShareRecordsDTO();
//		activityShareRecordDto.setMemberNo("811234568848");
		activityShareRecordDto.setMemberTel("15700000036");
		activityShareRecordDto.setRecommendMemberNo("811234567402");
		activityShareRecordDto.setRecommendMemberTel("18844195616");
//		activityShareRecordDto.setSrcType("");
//		activityShareRecordDto.setSrcNo("");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date = sdf.parse("2016-5-11 00:00:00");
    	Date date1 = sdf.parse("2016-5-12 00:00:00");
		activityShareRecordDto.setCreateTimeStart(date);
		activityShareRecordDto.setCreateTimeEnd(date1);
		List<ActivityShareRecordsDTO> list =activityShareRecordsFacadeImpl.selectListByParams(activityShareRecordDto);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		
	}
}
