package com.xinnet.yeepay;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class BatchSqlSessionUtils {

	private static String BatchSqlSessionUtils;

	public BatchSqlSessionUtils() {
	}

	public static SqlSession getSqlSession(SqlSessionFactory sessionFactory,
			ExecutorType executorType) {
		DataSource dataSource = sessionFactory.getConfiguration()
				.getEnvironment().getDataSource();
		boolean transactionAware = dataSource instanceof TransactionAwareDataSourceProxy;
		Connection conn;
		try {
			conn = transactionAware ? dataSource.getConnection()
					: DataSourceUtils.getConnection(dataSource);
		} catch (SQLException e) {
			throw new CannotGetJdbcConnectionException(
					"Could not get JDBC Connection for SqlSession", e);
		}
		if (logger.isDebugEnabled())
			logger.debug("Creating SqlSession from SqlSessionFactory");
		SqlSession session = sessionFactory.openSession(executorType, conn);
		if (TransactionSynchronizationManager.isSynchronizationActive()
				&& !(sessionFactory.getConfiguration().getEnvironment()
						.getTransactionFactory() instanceof SpringManagedTransactionFactory)
				&& DataSourceUtils.isConnectionTransactional(conn, dataSource))
			throw new TransientDataAccessResourceException(
					"SqlSessionFactory must be using a SpringManagedTransactionFactory in order to use Spring transaction synchronization");
		else
			return session;
	}

	public static void closeSqlSession(SqlSession session) {
		if (session != null)
			try {
				session.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
	}

	private static final Logger logger = LoggerFactory.getLogger(BatchSqlSessionUtils);

}
