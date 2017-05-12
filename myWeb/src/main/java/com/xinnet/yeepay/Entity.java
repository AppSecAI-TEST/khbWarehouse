package com.xinnet.yeepay;


import java.io.Serializable;

public interface Entity extends Serializable {

	public abstract Serializable getId();

	public abstract void setId(Serializable serializable);
}
