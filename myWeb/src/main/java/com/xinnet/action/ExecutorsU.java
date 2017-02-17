package com.xinnet.action;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorsU {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		Future<Integer> f = pool.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				return 111;
			}
		});
		f.get();
		System.out.println(f.get());
		Future<Integer> f1 = pool.submit(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 1 ; i<3;i++) {
					System.out.println("bb");
				}
			}
		}, 33);
		System.out.println(f1.get());
pool.submit(new Runnable() {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 1 ; i<3;i++) {
			System.out.println("bb");
		}
	}
});
	}
}
