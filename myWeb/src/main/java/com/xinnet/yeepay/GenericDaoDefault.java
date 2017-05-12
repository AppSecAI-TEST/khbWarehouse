package com.xinnet.yeepay;

import java.io.Serializable;
import java.util.*;

import org.apache.ibatis.session.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

@SuppressWarnings({"unchecked","rawtypes"})
public class GenericDaoDefault extends SqlSessionDaoSupport implements GenericDao {
	
	protected SqlSessionFactory sqlSessionFactory;
	protected Class entityClass;
	
	public GenericDaoDefault() {
		entityClass = GenericUtils.getGenericClass(getClass());
	}
	
	protected void checkDaoConfig() {
		super.checkDaoConfig();
		SqlSession sqlSession = super.getSqlSession();
		if (!(sqlSession instanceof SqlSessionTemplate)) {
			throw new VersionUncompatableException(
					"the version of this components[ yeepay-persistence ] is not compatable with mybatis-spring.jar version");
		} else {
			sqlSessionFactory = ((SqlSessionTemplate) sqlSession)
					.getSqlSessionFactory();
			return;
		}
	}
	
	public void delete(Serializable id) {
		super.getSqlSession().delete(getStatementId("delete"), id);
	}
	
	public void delete(String ql, Object args[]) {
		if (args == null || args.length == 0)
			super.getSqlSession().delete(getStatementId(ql));
		else if (args.length == 1) {
			super.getSqlSession().delete(getStatementId(ql), args[0]);
		} else {
			Map map = new HashMap();
			for (int i = 0; i < args.length; i++)
				map.put((new StringBuilder()).append("").append(i).toString(),
						args[i]);
			
			super.getSqlSession().delete(getStatementId(ql), map);
		}
	}
	
	public void delete(Entity entity) {
		delete(entity.getId());
	}
	
	public Entity get(Serializable id) {
		return (Entity) super.getSqlSession().selectOne(getStatementId("get"),
				id);
	}
	
	public List getAll() {
		return super.getSqlSession().selectList(getStatementId("getAll"));
	}
	
	public  List query(String ql, Object arg[]) {
		List result = null;
		if (arg == null || arg.length == 0)
			result = super.getSqlSession().selectList(getStatementId(ql));
		else if (arg.length == 1) {
			result = super.getSqlSession().selectList(getStatementId(ql),
					arg[0]);
		} else {
			Map map = new HashMap();
			for (int i = 0; i < arg.length; i++)
				map.put((new StringBuilder()).append("").append(i).toString(),
						arg[i]);
			
			result = super.getSqlSession().selectList(getStatementId(ql), map);
		}
		return ((List) (result == null ? new ArrayList() : result));
	}
	
	public  List query(String sql, int offset, int limit, Object arg[]) {
		List result = null;
		RowBounds rb = new RowBounds(offset, limit);
		if (arg == null || arg.length == 0)
			result = getSqlSession().selectList(getStatementId(sql), null, rb);
		else if (arg.length == 1) {
			result = getSqlSession()
					.selectList(getStatementId(sql), arg[0], rb);
		} else {
			Map map = new HashMap();
			for (int i = 0; i < arg.length; i++)
				map.put((new StringBuilder()).append("").append(i).toString(),
						arg[i]);
			
			result = getSqlSession().selectList(getStatementId(sql), map, rb);
		}
		return ((List) (result == null ? new ArrayList() : result));
	}
	
	public  Object queryOne(String ql, Object arg[]) {
		Object ob = null;
		if (arg == null || arg.length == 0)
			ob = super.getSqlSession().selectOne(getStatementId(ql));
		else if (arg.length == 1) {
			ob = super.getSqlSession().selectOne(getStatementId(ql), arg[0]);
		} else {
			Map map = new HashMap();
			for (int i = 0; i < arg.length; i++)
				map.put((new StringBuilder()).append("").append(i).toString(),
						arg[i]);
			
			ob = super.getSqlSession().selectOne(getStatementId(ql), map);
		}
		return ob;
	}
	
	protected String getStatementId(String postfix) {
		return (new StringBuilder()).append(entityClass.getSimpleName())
				.append(".").append(postfix).toString();
	}
	
	public void update(Entity entity) {
		int row = super.getSqlSession()
				.update(getStatementId("update"), entity);
		if ((entity instanceof EntityVersion) && row == 0)
			throw new OptimisticLockingException(
					"\u4E50\u89C2\u9501\u5F02\u5E38");
		else
			return;
	}
	
	public void add(Entity entity) {
		super.getSqlSession().insert(getStatementId("insert"), entity);
	}
	
	public void add(String sql, Entity entity) {
		super.getSqlSession().insert(getStatementId(sql), entity);
	}
	
	public  void update(String ql, Object arg[]) {
		if (arg == null || arg.length == 0)
			super.getSqlSession().update(getStatementId(ql));
		else if (arg.length == 1) {
			super.getSqlSession().update(getStatementId(ql), arg[0]);
		} else {
			Map map = new HashMap();
			for (int i = 0; i < arg.length; i++)
				map.put((new StringBuilder()).append("").append(i).toString(),
						arg[i]);
			
			super.getSqlSession().update(getStatementId(ql), map);
		}
	}
	
	public  Map getMap(String ql, Object arg[]) {
		Map result = null;
		if (arg == null || arg.length == 0)
			result = (Map) super.getSqlSession().selectOne(getStatementId(ql));
		else if (arg.length == 1) {
			result = (Map) super.getSqlSession().selectOne(getStatementId(ql),
					arg[0]);
		} else {
			Map map = new HashMap();
			for (int i = 0; i < arg.length; i++)
				map.put((new StringBuilder()).append("").append(i).toString(),
						arg[i]);
			
			result = (Map) super.getSqlSession().selectOne(getStatementId(ql),
					map);
		}
		return result;
	}
	
	/*private SqlSession getBatchSession() {
		return BatchSqlSessionUtils.getSqlSession(sqlSessionFactory,
				ExecutorType.BATCH);
	}*/
	
	/*public void batchInsert(String sql, List entities)
	{
		SqlSession batchSqlSession = getBatchSession();
		Entity e;
		for(Iterator i$ = entities.iterator(); i$.hasNext(); batchSqlSession.insert(getStatementId(sql), e))
			e = (Entity)i$.next();
		
		batchSqlSession.commit();
		BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
		break MISSING_BLOCK_LABEL_74;
		Exception exception;
		exception;
		BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
		throw exception;
	}
	
	public void batchUpdate(List entities)
	{
		SqlSession batchSqlSession = getBatchSession();
		Entity e;
		for(Iterator i$ = entities.iterator(); i$.hasNext(); batchSqlSession.update(getStatementId("update"), e))
			e = (Entity)i$.next();
		
		batchSqlSession.commit();
		BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
		break MISSING_BLOCK_LABEL_72;
		Exception exception;
		exception;
		BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
		throw exception;
	}
	
	*//**
	 * @deprecated Method batchInsert is deprecated
	 *//*
	
	public void batchInsert(List entities)
	{
		SqlSession batchSqlSession = getBatchSession();
		Entity e;
		for(Iterator i$ = entities.iterator(); i$.hasNext(); batchSqlSession.insert(getStatementId("insert"), e))
			e = (Entity)i$.next();
		
		batchSqlSession.commit();
		BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
		break MISSING_BLOCK_LABEL_72;
		Exception exception;
		exception;
		BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
		throw exception;
	}
	
	public void batchDelete(List entities)
	{
		SqlSession batchSqlSession = getBatchSession();
		Entity e;
		for(Iterator i$ = entities.iterator(); i$.hasNext(); batchSqlSession.delete(getStatementId("delete"), e.getId()))
			e = (Entity)i$.next();
		
		batchSqlSession.commit();
		BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
		break MISSING_BLOCK_LABEL_77;
		Exception exception;
		exception;
		BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
		throw exception;
	}
	
	public void batchDeleteById(List ids)
	{
		SqlSession batchSqlSession = getBatchSession();
		Serializable id;
		for(Iterator i$ = ids.iterator(); i$.hasNext(); batchSqlSession.delete(getStatementId("delete"), id))
			id = (Serializable)i$.next();
		
		batchSqlSession.commit();
		BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
		break MISSING_BLOCK_LABEL_72;
		Exception exception;
		exception;
		BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
		throw exception;
	}*/
	
	
}
