/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.hessian.activity.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.yeepay.g3.utils.common.BeanUtils;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;

/**
 * 账务处理单元测试父类
 *
 * @author：wang.bao
 * @since：2012-10-10 下午03:42:28
 * @version:
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@TransactionConfiguration(transactionManager = "acctrxTxManager", defaultRollback = false)
//@Transactional
//@ContextConfiguration(locations = { "classpath:/testContext.xml" })
public class AccountingBaseTest {

	static {
		// 不要禁用远程调用组件，否则在linux环境下可能会首先加载此类，
		// 导致需要mockrmi为true的测试场景无法运行通过
		// （如果mvn test设置为在一个线程内运行所有单元测试，则这个初始化只会进行一次）
		// System.setProperty("disablermi","true");
//		System.setProperty("mockrmi", "true");
//		System.setProperty("mockconfig", "true");
		System.setProperty("disablemsg", "true");
//		System.setProperty("dbconfigpath",
//				"/Users/yp-tc-m-2553/develop/tomcat-7.0.19/commoncfg/dbconf");
		ConfigurationUtils.init();
//		RemoteServiceFactory.init();
	}

//	private static ScriptRunnerWrapper scriptRunnerWrapper;

//	@Autowired
//	protected JdbcTemplate jdbcTemplate;


//	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}

//	@Before
//	public void initTestData() throws IOException, SQLException {
//		String runscript = System.getProperty("runscript");
//		if (!StringUtils.equals("false", runscript)) {
//			ApplicationContext context = new ClassPathXmlApplicationContext(
//					"/testContext.xml");
//			scriptRunnerWrapper = (ScriptRunnerWrapper) context
//					.getBean("scriptRunnerWrapper");
//			scriptRunnerWrapper.initTestData();
//		}
//	}

	protected Date getDateByStr(String str) {
		try {
			if (str.indexOf(":") != -1) {
				return (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).parse(str);
			} else {
				return (new SimpleDateFormat("yyyy-MM-dd")).parse(str);
			}
		} catch (ParseException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	protected void checkEquals(Object rule, Object entity,
			String... exceptProperties) {
		Map<String, Object> propertieyMap1 = BeanUtils.getProperties(rule);
		Map<String, Object> propertieyMap2 = BeanUtils.getProperties(entity);
		List<String> excepts = exceptProperties == null ? new ArrayList<String>()
				: Arrays.asList(exceptProperties);
		for (String key : propertieyMap1.keySet()) {
			if (excepts.contains(key))
				continue;
			if (!propertieyMap2.containsKey(key)) {
				continue;
			}
			Assert.assertEquals(key, propertieyMap1.get(key),
					propertieyMap2.get(key));
		}
	}

	@After
	public void afterDaoTest() {
//		if (scriptRunnerWrapper != null) {
//			scriptRunnerWrapper.release();
//		}
	}
}
