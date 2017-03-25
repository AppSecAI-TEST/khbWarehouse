package com.yeepay.g3.hessian.activity.util;
///**
// * Copyright: Copyright (c)2011
// * Company: 易宝支付(YeePay)
// */
//package com.yeepay.g3.hessian.offimport.util;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import com.yeepay.g3.facade.offimport.dto.OffimportCusAccountDTO;
//import com.yeepay.g3.facade.offimport.dto.OffimportOrderDTO;
//import com.yeepay.g3.facade.offimport.enums.AccountStatus;
//import com.yeepay.g3.facade.offimport.enums.OrderStatus;
//import com.yeepay.g3.facade.offimport.facade.OffimportOrderFacade;
//import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
//import com.yeepay.g3.utils.rmi.RemotingProtocol;
//
//public class AccountingHessianTest {
////	private String serviceUri = "http://10.151.30.80:18069/offimport-hessian";
//	private String serviceUri = "http://10.151.30.91:8069/offimport-hessian";
////	private String serviceUri = "http://localhost:8080/offimport-hessian";
//
//	@BeforeClass
//	public static void before() {
//
////		System.setProperty("disablemsg", "true");
////		ApplicationContext ctx = ApplicationContextUtil.getInstence().getApplicationContext();
////		ConfigurationUtils.init();
//		RemoteServiceFactory.init();
//	}
//
//	@Test
//	public void testSaveOffimportOrder() {
//		OffimportOrderFacade offimportOrderFacade = RemoteServiceFactory
//				.getService(serviceUri, RemotingProtocol.HESSIAN,
//						OffimportOrderFacade.class);
//		
//		OffimportOrderDTO orderDto = new OffimportOrderDTO();
//		orderDto.setBizFlowId("2");
//    	orderDto.setBizMemo("");
//    	orderDto.setOrderStatus(OrderStatus.REPEAL);
//    	orderDto.setReceiveAmount(new BigDecimal(2.0));
//    	orderDto.setReceiveDate(new Date());
//    	orderDto.setCreateTime(new Date());
//    	orderDto.setRecipientBankCode("employee-boss-portal");
////    	orderDto.setBankFlowId("1");
//    	orderDto.setRecipientCardNo("3");
//    	orderDto.setRemittanceCardNo("4");
//    	orderDto.setRemittanceName("6");
//    	orderDto.setRequestFlowId("10");
//    	
//    	List<OffimportCusAccountDTO> list = new ArrayList<OffimportCusAccountDTO>();
//    	OffimportCusAccountDTO accountDTO = new OffimportCusAccountDTO();
//    	accountDTO.setAccAccountNo("1234");
//    	accountDTO.setAccCustomerNo("222");
//    	accountDTO.setAccAmount(new BigDecimal(1.0));
//    	accountDTO.setRequestFlowId(orderDto.getRequestFlowId());
//    	accountDTO.setAccStatus(AccountStatus.ACCOUNT_INIT);
//    	list.add(accountDTO);
//    	
//    	orderDto.setCusAccountDTOList(list);
//		
//		offimportOrderFacade.saveOffimportOrder(orderDto);
////    	orderDto.setOrderRepealMemo("fuck");
////		offimportOrderFacade.repealOrder(orderDto);
//	}
//
//}
