package com.xinnet.yeepay;

/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.


import java.io.Serializable;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.yeepay.g3.utils.persistence:
//            T

public interface GenericDao<T extends Serializable> {
	
	public abstract void add(T T);
	
	public abstract void add(String s, T T);
	
	public abstract void update(T T);
	
	public abstract void delete(Serializable serializable);
	
	public abstract void delete(String s, Object... aobj);
	
//	public abstract void delete(T T);
	
	public abstract List getAll();
	
	public abstract T get(Serializable serializable);
	
	public abstract List query(String s, Object... aobj);
	
	public abstract List query(String s, int i, int j, Object... aobj);
	
	public abstract Object queryOne(String s, Object... aobj);
	
	public abstract void update(String s, Object... aobj);
	
	public abstract Map getMap(String s, Object... aobj);
	
	/*public abstract void batchUpdate(List list);
	
	public abstract void batchInsert(String s, List list);
	
	 *//**
	 * @deprecated Method batchInsert is deprecated
	 *//*
	
	public abstract void batchInsert(List list);
	
	public abstract void batchDelete(List list);
	
	public abstract void batchDeleteById(List list);*/
}

