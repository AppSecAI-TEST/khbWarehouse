package com.xinnet.yeepay;

public class OptimisticLockingException extends RuntimeException {

	public OptimisticLockingException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 3821087967248373161L;
}
