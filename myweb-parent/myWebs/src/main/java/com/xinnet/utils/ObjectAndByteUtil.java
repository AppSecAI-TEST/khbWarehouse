package com.xinnet.utils;

import java.io.ByteArrayInputStream;   
import java.io.ByteArrayOutputStream;   
import java.io.IOException;   
import java.io.ObjectInputStream;   
import java.io.ObjectOutputStream; 

import com.xinnet.entity.Book;

/**
 * 
 * @author hongbin.kang
 * @date 2017年7月27日上午10:19:47
 */
public class ObjectAndByteUtil { 
	/**  
	 * 对象转数组  
	 * @param obj  
	 * @return  
	 */  
	public static byte[] toByteArray (Object obj) {      
		byte[] bytes = null;      
		ByteArrayOutputStream bos = new ByteArrayOutputStream();      
		try {        
			ObjectOutputStream oos = new ObjectOutputStream(bos);         
			oos.writeObject(obj);        
			oos.flush();         
			bytes = bos.toByteArray ();      
			oos.close();         
			bos.close();        
		} catch (IOException ex) {        
			ex.printStackTrace();   
		}      
		return bytes;    
	}   
	
	/**  
	 * 数组转对象  
	 * @param bytes  
	 * @return  
	 */  
	public static Object toObject (byte[] bytes) {      
		Object obj = null;      
		try {        
			ByteArrayInputStream bis = new ByteArrayInputStream (bytes);        
			ObjectInputStream ois = new ObjectInputStream (bis);        
			obj = ois.readObject();      
			ois.close();   
			bis.close();   
		} catch (IOException ex) {        
			ex.printStackTrace();   
		} catch (ClassNotFoundException ex) {        
			ex.printStackTrace();   
		}      
		return obj;    
	}   
	
	public static void main(String[] args) {   
		Book tb = null;   
		
		ObjectAndByteUtil oa = new ObjectAndByteUtil();   
		byte[] b = oa.toByteArray(tb);   
		System.out.println(new String(b));   
		
		System.out.println("=======================================");   
		
		Book teb = (Book) oa.toObject(b);   
	}
}
