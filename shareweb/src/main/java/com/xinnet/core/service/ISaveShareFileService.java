package com.xinnet.core.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.xinnet.core.dto.FileDTO;


public interface ISaveShareFileService {

	/**
	 * 保存共享文件
	 * @author hongbin.kang
	 * @date 2017年4月11日 下午3:49:02
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public String saveShareFile(MultipartFile upLoadFile) throws IOException;

	public FileDTO showFile(String fileName) throws Exception;
}
