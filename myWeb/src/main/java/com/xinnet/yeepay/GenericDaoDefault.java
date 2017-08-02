package com.xinnet.yeepay;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings({"unchecked","rawtypes"})
public abstract class GenericDaoDefault<T extends Serializable> extends SqlSessionDaoSupport implements GenericDao<T> {
	@Autowired
	protected SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	@Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {  
        super.setSqlSessionFactory(sqlSessionFactory);  
    }
	protected Class<T> entityClass;
	
	public GenericDaoDefault() {
		entityClass = GenericUtils.getGenericClass(getClass());
	}
	
	@Override
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
	
	public void delete(String ql, Object... args) {
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
	
//	public void delete(T T) {
//		delete(T.getId());
//	}
	
	public T get(Serializable id) {
		return (T) super.getSqlSession().selectOne(getStatementId("get"),
				id);
	}
	
	public List getAll() {
		return super.getSqlSession().selectList(getStatementId("getAll"));
	}
	
	public  List query(String ql, Object... arg) {
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
	
	public  List query(String sql, int offset, int limit, Object... arg) {
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
	
	public  Object queryOne(String ql, Object... arg) {
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
	
	public void update(T T) {
		int row = super.getSqlSession()
				.update(getStatementId("update"), T);
		if ((T instanceof EntityVersion) && row == 0)
			throw new OptimisticLockingException(
					"\u4E50\u89C2\u9501\u5F02\u5E38");
		else
			return;
	}
	
	public void add(T T) {
		super.getSqlSession().insert(getStatementId("insert"), T);
	}
	
	public void add(String sql, T T) {
		super.getSqlSession().insert(getStatementId(sql), T);
	}
	
	public  void update(String ql, Object... arg) {
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
	
	public  Map getMap(String ql, Object... arg) {
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
	
	private SqlSession getBatchSession() {
		return BatchSqlSessionUtils.getSqlSession(sqlSessionFactory,
				ExecutorType.BATCH);
	}
	
	public void batchInsert(String sql, List entities) {
		SqlSession batchSqlSession = getBatchSession();
		try {
			T e;
			for(Iterator i$ = entities.iterator(); i$.hasNext(); batchSqlSession.insert(getStatementId(sql), e))
				e = (T)i$.next();
			
			batchSqlSession.commit();
			BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
		} catch (Exception e) {
			BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
			throw new RuntimeException(e); 
		}
	}
	
	public void batchUpdate(List entities) {
		SqlSession batchSqlSession = getBatchSession();
		try {
			T e;
			for(Iterator i$ = entities.iterator(); i$.hasNext(); batchSqlSession.update(getStatementId("update"), e))
				e = (T)i$.next();
			
			batchSqlSession.commit();
			BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
		} catch (Exception e) {
			BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
			throw new RuntimeException(e); 
		}
	}
	
	/**
	 * @deprecated Method batchInsert is deprecated
	 */
	
	public void batchInsert(List entities) {
		SqlSession batchSqlSession = getBatchSession();
		try {
			T e;
			for(Iterator i$ = entities.iterator(); i$.hasNext(); batchSqlSession.insert(getStatementId("insert"), e))
				e = (T)i$.next();
			
			batchSqlSession.commit();
			BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
		} catch (Exception e) {
			BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
			throw new RuntimeException(e); 
		}
	}
	
	public void batchDelete(List entities) {
		SqlSession batchSqlSession = getBatchSession();
		try {
			Entity e;
			for(Iterator i$ = entities.iterator(); i$.hasNext(); batchSqlSession.insert(getStatementId("delete"), e.getId()))
				e = (Entity)i$.next();
			
			batchSqlSession.commit();
			BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
		} catch (Exception e) {
			BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
			throw new RuntimeException(e); 
		}
	}
	
	public void batchDeleteById(List ids) {
		SqlSession batchSqlSession = getBatchSession();
		try {
			Serializable id;
			for(Iterator i$ = ids.iterator(); i$.hasNext(); batchSqlSession.insert(getStatementId("delete"), id))
				id = (Serializable)i$.next();
			
			batchSqlSession.commit();
			BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
		} catch (Exception e) {
			BatchSqlSessionUtils.closeSqlSession(batchSqlSession);
			throw new RuntimeException(e); 
		}
	}
	
	
}
