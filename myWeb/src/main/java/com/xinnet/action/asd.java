package com.xinnet.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import com.xinnet.entity.User;
import com.xinnet.entity.UserP;
import com.xinnet.entity.UserS;

public class asd {
	public static void main(String[] args) throws Exception{
		NumberFormat nf = new DecimalFormat("#,###.##");
		String testStr = nf.format(6789);
		System.out.println(testStr);
		
		String a = "knag";
		String b = "knag";
		if(a==b) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
		int type = 5;
		switch (type) {  
        case 1:  
            System.out.println(1);  
        default:  
        	System.out.println(4);  
        case 2:  
            System.out.println(2);  
        case 3:  
            System.out.println(3);  
        } 
		
		/*try{  
            throw new Exception("1");  
        }catch (IOException e){  
            throw new Exception("2");  
        }catch (Exception e) {  
            throw new Exception("3");  
        }finally {  
            throw new Exception("4");  
        }*/
		
		
        List<Map<String, Object>> dataset2 = new ArrayList<Map<String, Object>>();  
         
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("shuliang", 1);
        map.put("type", "jsp");
        map.put("sttue", "leno");
        map.put("yeshu", 300.34);
        map.put("haoma", "2016-12-13 23:45:56");
        map.put("chubanshe", "清华出版社");
        System.out.println(map);
        for(String key : map.keySet()) {
        	System.out.println(key+"------"+map.get(key));
        }
        
        for(Map.Entry<String, Object> entry : map.entrySet()) {
        	System.out.println(entry.getKey()+"------"+entry.getValue());
        }
        
        
        
        
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("shuliang", 2);
        map1.put("type", "java编程思想");
        map1.put("sttue", "brucl");
        map1.put("yeshu", "3020.33f");
        map1.put("haoma", new Date());
        map1.put("chubanshe", "阳光出版社");
        
        dataset2.add(map);
        dataset2.add(map1);
        Iterator it = dataset2.iterator();
        while(it.hasNext()) {
        	Map<String, Object> is =  (Map<String, Object>) it.next();
        	System.out.println(is);
        	for(Map.Entry<String, Object> entry : is.entrySet()) {
            	System.out.println(entry.getKey()+"------"+entry.getValue());
            }
        }
        
        
        UserP u = new UserP();
        u.setAge(10);
        u.setSex("nan");
        
		System.out.println(sss(u));
		
		
		
	}
	
	public static String sss(User user) {
		System.out.println(user.getClass());
		if(user instanceof UserP) {
			UserP p = (UserP)user;
			System.out.println("P");
			return p.getResult();
		} else if(user instanceof UserS) {
			UserS s = (UserS)user;
			System.out.println("S");
			return s.getResult();
		} else {
			return user.toString();
		}
		
		
		
		
	}
}
