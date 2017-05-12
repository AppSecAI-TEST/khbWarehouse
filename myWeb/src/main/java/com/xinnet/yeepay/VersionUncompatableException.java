package com.xinnet.yeepay;

public class VersionUncompatableException extends RuntimeException {

	public VersionUncompatableException(String message) {
		super(message);
	}

	public VersionUncompatableException(String message, Throwable throwable) {
		super(message, throwable);
	}

	private static final long serialVersionUID = 3821087967248373161L;
}