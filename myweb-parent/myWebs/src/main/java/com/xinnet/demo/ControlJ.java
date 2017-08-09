package com.xinnet.demo;

public class ControlJ {
	private int[] j = new int[]{};
	public synchronized void set(int i) {
		j[j.length] = i;
	}
	public synchronized int get() {
		if(j.length>0) {
			int i = j[0];
			int[] arr = new int[]{};
			for(int k=1;k<j.length;k++) {
				arr[k-1] = j[k];
			}
			return i;
		}
		return (Integer) null;
	}
}
