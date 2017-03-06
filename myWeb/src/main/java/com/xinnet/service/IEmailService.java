package com.xinnet.service;


public interface IEmailService {
	
	public void sendEmailCode(String sendAddress) throws Exception;
	
	public void sendEmail(String sendAddress,String tittle ,String content) throws Exception;


}
