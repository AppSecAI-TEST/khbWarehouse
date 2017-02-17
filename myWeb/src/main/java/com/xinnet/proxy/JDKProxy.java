package com.xinnet.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {
	
	private Object target;

	public JDKProxy(Object targer) {
		this.target = targer;
	}
	@Override
	public Object invoke(Object obj, Method method, Object[] aobj)
			throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("开始前");
		Object result = method.invoke(target, aobj);
		System.out.println("结束后");
		return result;
	}
	
	public Object getProxy() {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

}
