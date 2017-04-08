/**
 * 
 */
/**
 * @author hongbin.kang
 * @date 2017年3月31日下午10:30:22
 */
package com.xinnet.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("com.xinnet.mapper")
public class DataSourceConfig {  
//	@ConfigurationProperties(prefix="spring.datasource")
	@Bean
	public DataSource dataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/myweb");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return dataSource;
	}
	
	/**
	 * SqlSessionFactory  PlatformTransactionManager  使用已经注册的source才能使用事务管理
	 * @author hongbin.kang
	 * @date 2017年4月1日 上午3:00:38
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
//      sessionFactory.setTypeAliasesPackage("com.xinnet.mapper"); 
		return sqlSessionFactoryBean.getObject();
	}
	
	/**
	 * SqlSessionFactory  PlatformTransactionManager  使用已经注册的source才能使用事务管理
	 * @author hongbin.kang
	 * @date 2017年4月1日 上午3:00:38
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		DataSourceTransactionManager manager = new DataSourceTransactionManager();  
		manager.setDataSource(dataSource);  
		return manager; 
	}
}