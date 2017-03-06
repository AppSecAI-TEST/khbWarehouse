package com.xinnet.service;


public interface IEmailService {
	
	public void sendEmail(String sendAddress,String content) throws Exception;
	
	public void sendEmail(String sendAddress,String tittle ,String content) throws Exception;


}
