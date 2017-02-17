package com.xinnet.action;

public class ThreadDemo {
	
	public static void main(String[] args) {
		Thread thread = new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					output("kanghongbin");
//					System.out.println(Thread.currentThread().getName());
				}
			};
		};
		thread.start();
		
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					output("sunjinyu");
//					System.out.println(Thread.currentThread().getName());
				}
			}
		});
		
		thread1.start();
		
	}
	
	public static synchronized void output(String name){
		for(int i=0; i<name.length();i++) {
			System.out.print(name.charAt(i));
		}
		System.out.println();
	}

}
