package com.xinnet.demo;

public class AddAndDesc {
	static ControlJ ad = new ControlJ();
	public static void main(String[] args) {
		
		for(int i=0;i<20;i++) {
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
//					ad.add();
//					System.out.println(ad.getJ());
				}
			}).start();
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
//					ad.desc();
//					System.out.println(ad.getJ());
					
				}
			}).start();
			
		}
	}
	

}
