package com.xinnet.demo;

public class ThreadDemo2 {
	static boolean flag = true;
	
	public static void main(String[] args) {
		class asd {
			Thread sonThread =  new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if(flag) {
						outPut(10,"子线程：");
					}
					/*for(int i=0;i<10;i++) {
					System.out.println("子线程："+i);
				}*/
				}
			});
		}
		for(int i=0;i<50;i++) {
			new asd().sonThread.start();
			outPut(20,"主线程：");
		}
		
		
//		parentThread.start();
		
		/*for(int i=0;i<10;i++) {
			sonThread.start();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
	}
	public synchronized static void outPut(int size,String message){
		for(int i=0;i<size;i++) {
			System.out.println(message+i);
		} 
		flag = !flag;
	}

}
