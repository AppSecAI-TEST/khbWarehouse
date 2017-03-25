package com.yeepay.g3.hessian.activity.test;
///**
// * 
// */
//package com.yeepay.g3.hessian.offimport.test;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//
//import com.yeepay.g3.facade.accounting.enums.FeeTypeEnum;
//import com.yeepay.g3.facade.offimport.dto.OffimportBankflowDTO;
//import com.yeepay.g3.facade.offimport.dto.OffimportCusAccountDTO;
//import com.yeepay.g3.facade.offimport.dto.OffimportOrderDTO;
//import com.yeepay.g3.facade.offimport.enums.AccountStatus;
//import com.yeepay.g3.facade.offimport.enums.OrderStatus;
//import com.yeepay.g3.facade.offimport.facade.OffimportOrderFacade;
//import com.yeepay.g3.hessian.offimport.util.AccountingBaseTest;
//import com.yeepay.g3.hessian.offimport.util.ApplicationContextUtil;
//import com.yeepay.g3.utils.common.DateUtils;
//import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
//import com.yeepay.g3.utils.rmi.RemotingProtocol;
//
///**
// * @Description 
// * @author zhenping.zhou
// * @CreateTime 2015年12月7日 上午10:42:15
// * @version 1.0
// */
//public class OffimportOrderFacadeImplTest extends AccountingBaseTest {
//
////	@Autowired
//	private OffimportOrderFacade offimportOrderFacadeImpl;
//	
//    @Before
//    public void setUp() throws Exception {
//    	
//		RemoteServiceFactory.init();
//
//        ApplicationContext ctx = ApplicationContextUtil.getInstence().getApplicationContext();
//        offimportOrderFacadeImpl = (OffimportOrderFacade) ctx.getBean("offimportOrderFacadeImpl");
////        offimportOrderFacadeImpl = RemoteServiceFactory.getService(RemotingProtocol.HESSIAN, OffimportOrderFacade.class);
//    }
//
//    /**
//     * 创建线下汇入订单
//     * @throws Exception
//     */
////    @Test
//    public void TestSaveOffimportOrder() throws Exception {
//    	OffimportOrderDTO orderDto = new OffimportOrderDTO();
//    	orderDto.setRequestFlowId("BBBBBBBBBBBBBB");
//    	orderDto.setBizFlowId(""); //银行备注
//    	orderDto.setReceiveDate(DateUtils.parseDate("2015-12-22", "yyyy-MM-dd")); //收款日期
//    	orderDto.setReceiveAmount(new BigDecimal(25000));
//    	orderDto.setBizMemo("懒猫线下汇入资金");
//    	orderDto.setRemittanceName("易宝支付"); //汇款方名称
//    	orderDto.setRemittanceCardNo("1234567890"); //汇款方账号
//    	orderDto.setRecipientBankCode("ECITIC"); //收款银行编码
//    	orderDto.setRecipientBankName("中信银行");
//    	orderDto.setRecipientCardNo("6206880199878377");//收款账户
//    	orderDto.setProductCode("7011001001001A"); //零售产品代码
//    	orderDto.setFee(new BigDecimal(0));
//    	orderDto.setFeeType(FeeTypeEnum.INNER.toString());
//    	
//    	List<OffimportCusAccountDTO> list = new ArrayList<OffimportCusAccountDTO>();
//    	OffimportCusAccountDTO accountDTO = new OffimportCusAccountDTO();
//    	accountDTO.setRequestFlowId(orderDto.getRequestFlowId());
//    	accountDTO.setAccAccountNo("12349999");
//    	accountDTO.setAccCustomerNo("100118374844");
//    	accountDTO.setAccAmount(new BigDecimal(25000));
//    	accountDTO.setFee(new BigDecimal(1));
//    	accountDTO.setFeeType(FeeTypeEnum.INNER.toString());
//    	
//    	list.add(accountDTO);
//    	
//    	orderDto.setCusAccountDTOList(list);
//    	
//    	offimportOrderFacadeImpl.saveOffimportOrder(orderDto);
//    }
//    
//    /**
//     * 结算人工复核，商户入账
//     * @throws Exception
//     */
////    @Test
//    public void testDoAccountToCustomer() throws Exception {
//    	
//    	OffimportOrderDTO orderDto = new OffimportOrderDTO();
//    	orderDto.setId(2L);
////    	orderDto.setOrderStatus(OrderStatus.ACCOUNTING);
//    	
//    	OffimportBankflowDTO bankflowDto = new OffimportBankflowDTO();
//    	bankflowDto.setId(1L);
//    	bankflowDto.setRequestFlowId("123");
////    	offimportOrderFacadeImpl.doAccountToCustomer(orderDto,bankflowDto);
//    }
//    
//    /**
//     * 结算人工复核，商户入账
//     * @throws Exception
//     */
//    @Test
//    public void testQueryBankAccountSuccess() throws Exception {
//    	
//    	offimportOrderFacadeImpl.queryBankAccountSuccess("BBBBBBBBBBBBBB");
//    }
//    
//    /**
//     * 结算人工复核，商户入账
//     * @throws Exception
//     */
////    @Test
//    public void testRepalOrder() throws Exception {
//    	OffimportOrderDTO orderDto = new OffimportOrderDTO();
//    	orderDto.setRequestFlowId("aaaaaaaaaaaaaa");
//    	offimportOrderFacadeImpl.repealOrder(orderDto);
//    }
//}
