/**
 * @author 陈大涛
 * 2016-6-3下午3:57:49
 */
package com.yeepay.g3.boss.activity.utils;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 陈大涛
 * 2016-6-3下午3:57:49
 */
public class UploadFile {

	/**
	 * 保存活动图片
	 */
	  public static String saveFile(MultipartFile filedata,String saveFilePath) {
		  String returnUrl="";
		  if (filedata != null && !filedata.isEmpty()) {
	            // 获取图片的文件名
	            String fileName = filedata.getOriginalFilename();
	            // 获取图片的扩展名
	            String extensionName = fileName
	                    .substring(fileName.lastIndexOf(".") + 1);
	            // 新的图片文件名 = 获取时间戳+"."图片扩展名
	            String newFileName = String.valueOf(System.currentTimeMillis())
	                    + "." + extensionName;
		 
	        /* 构建文件目录 */
	        File fileDir = new File(saveFilePath);
	        if (!fileDir.exists()) {
	            fileDir.mkdirs();
	        }
	        try {
	            FileOutputStream out = new FileOutputStream(saveFilePath + "/"
	                    + newFileName);
	            // 写入文件
	            out.write(filedata.getBytes());
	            out.flush();
	            out.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        returnUrl=saveFilePath + "/"
	                  + newFileName;
}
		  return returnUrl;
		
	  }
	  public static void deleteFile(String url) {
	   /*      构建文件目录 
	        File fileDir = new File(url);
	        if (fileDir.exists()) {
	            //把修改之前的图片删除 以免太多没用的图片占据空间
	            fileDir.delete();
	        }*/

	    }
}
