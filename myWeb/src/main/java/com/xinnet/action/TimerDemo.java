package com.xinnet.action;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {
	public static void main(String[] args) throws InterruptedException {
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("baozhai");
			}
		}, 1000,3000);
		
		while (true) {
			System.out.println(new Date().getSeconds());
			Thread.sleep(1000);
		}
	}
}
