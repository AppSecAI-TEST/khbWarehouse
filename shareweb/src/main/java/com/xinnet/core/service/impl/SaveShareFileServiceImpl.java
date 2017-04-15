/**
 * @author hongbin.kang
 * @date 2016-6-1
 * @time 下午4:26:30
 */
package com.xinnet.core.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.xinnet.core.annotation.HessianService;
import com.xinnet.core.dto.FileDTO;
import com.xinnet.core.service.ISaveShareFileService;
import com.xinnet.core.utils.PropertiesUtils;

/**
 * @author hongbin.kang
 *
 */
@HessianService
public class SaveShareFileServiceImpl implements ISaveShareFileService {
	
	@Override
	public String saveShareFile(MultipartFile upLoadFile) throws IOException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String sharedir =  PropertiesUtils.getPropertyValue("sharefile") + request.getContextPath();
		String newFilename = UUID.randomUUID().toString().replaceAll("\\-", "");
		//原始的文件名
		String filename = upLoadFile.getOriginalFilename();
		//获取扩展名
		String extensionName = StringUtils.split(filename, ".")[1]; 
		File file = new File(sharedir +"/"+ newFilename+"."+extensionName);
		//file.createNewFile();
		//判断目标文件所在的目录是否存在  
        if(!file.getParentFile().exists()) {  
            //如果目标文件所在的目录不存在，则创建父目录  
            System.out.println("目标文件所在目录不存在，准备创建它！");  
            if(!file.getParentFile().mkdirs()) {  
                System.out.println("创建目标文件所在目录失败！");  
            }  
        }  
	 	upLoadFile.transferTo(file);
	 	String path = request.getContextPath();  
	 	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	 	String url = basePath + "showFile?id=" + newFilename; 
		return url;
	}

	@Override
	public FileDTO showFile(String id) throws IOException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String sharedir =  PropertiesUtils.getPropertyValue("sharefile") + request.getContextPath();
		File file = new File(sharedir);
		File[] fileList = file.listFiles();
		String fileName = null;
		String str = null;
		for(File fi : fileList) {
			str = fi.getName();
			if(id.equals(str.substring(0, str.indexOf(".")))) {
				fileName = fi.getName();
			}
		}
		if(null == fileName) {
			return null;
		}
//		FileInputStream fis= new FileInputStream(f);  
		FileInputStream in = new FileInputStream(sharedir + "/" + fileName);;
		
		FileChannel fc= in.getChannel();  
        Long fileSize = fc.size();
		return new FileDTO(in,fileName,fileSize);
	}
	
}
