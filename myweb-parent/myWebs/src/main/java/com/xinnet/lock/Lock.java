package com.xinnet.lock;

  
public interface Lock {

	public abstract boolean lock();

	public abstract boolean tryLock(int i);

	public abstract void unlock();
}
