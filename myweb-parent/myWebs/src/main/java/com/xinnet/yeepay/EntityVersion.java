package com.xinnet.yeepay;


public interface EntityVersion<T extends Long> extends Entity<T> {

	public abstract void setVersion(T t);

	public abstract T getVersion();
}
