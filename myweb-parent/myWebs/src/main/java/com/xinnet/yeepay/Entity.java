package com.xinnet.yeepay;


import java.io.Serializable;

public interface Entity<T extends Serializable> extends Serializable {

	public abstract T getId();

	public abstract void setId(T serializable);
}
