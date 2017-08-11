package com.xinnet.task;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Component
public class TaskTest {
	public final Logger log = Logger.getLogger(this.getClass());
 
	public void run() {
		log.info("任务名称 = run----------执行成功，开始时间--["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"]");
		Thread th=Thread.currentThread();

		System.out.println("任务名称 = run----------Tread name:"+th.getName());
		for (int i = 0; i < 1; i++) {
			log.debug(i+" run......................................" + (new Date()));
		}
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void run1() {
		log.info("任务名称 = run1----------执行成功，开始时间--["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"]");
		Thread th=Thread.currentThread();

		System.out.println("任务名称 = run1----------Tread name:"+th.getName());
		for (int i = 0; i < 1; i++) {
			log.debug(i+" run1......................................" + (new Date()));
		}
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run1(String params) {
		JSONObject json = JSONObject.parseObject(params);
		System.out.println("age="+json.get("age"));
		log.info("任务名称 = run1----------执行成功，开始时间--["+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"]");
		Thread th=Thread.currentThread();

		System.out.println("任务名称 = run1----------Tread name:"+th.getName());
		for (int i = 0; i < 1; i++) {
			log.debug(i+" run1......................................" + (new Date()));
		}
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String c=null;
	    Map<String, Charset> charsets = Charset.availableCharsets();
	    for (Map.Entry<String, Charset> entry : charsets.entrySet()) {
	       System.out.println(entry.getKey());
	    }

	}
}
