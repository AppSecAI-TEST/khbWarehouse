package com.xinnet.core.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xinnet.core.dto.FileDTO;
import com.xinnet.core.service.ISaveShareFileService;

@Controller
public class UploadAction {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadAction.class);
	
	@Autowired
	ISaveShareFileService saveShareFileServiceImpl;
	
	@RequestMapping("upload")
	public String upload(HttpServletRequest request,HttpServletResponse response) throws IOException {
		if (!(request instanceof MultipartHttpServletRequest)) {  
			String errorMsg = "your post form is not support ENCTYPE='multipart/form-data' ";  
			logger.error(errorMsg);  
			
			throw new RuntimeException(errorMsg);  
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		MultipartFile multipartFiles = multipartRequest.getFile("upload");  
		String url = saveShareFileServiceImpl.saveShareFile(multipartFiles);
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter out = response.getWriter();
			JSONObject josn = new JSONObject();
			//解决跨域问题
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			// 设置响应数据的类型json  
            /*response.setContentType("application/json; charset=utf-8");  
            // 写回  
            response.getWriter().write(josn.toString()); */
			josn.put("path", url);
			out.print(josn);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	@RequestMapping("file")
	public String file(HttpServletRequest request,HttpServletResponse response) {
		return "file";
	}
	
	@RequestMapping("showFile/{fileName}/{exName}")
	public void showFile(HttpServletRequest request,HttpServletResponse response,
			@PathVariable("fileName")String fileName,
			@PathVariable("exName")String exName) throws Exception {
		String file = fileName+"."+exName;
		FileDTO dto = saveShareFileServiceImpl.showFile(file);
		InputStream in = dto.getIn();
		OutputStream out = response.getOutputStream();
	    // 判断输入或输出是否准备好    
	    if (in != null && out != null) {  
	        int temp = 0;  
	        // 开始拷贝  
	        while ((temp = in.read()) != -1) {  
	            // 边读边写  
	            out.write(temp);  
	        }  
	        // 关闭输入输出流  
	        in.close();  
	        out.close();
	    }
	}
	
	@RequestMapping("loadFile/{fileName}/{exName}")
	public void loadFile(HttpServletRequest request,HttpServletResponse response,
			@PathVariable("fileName")String fileName,
			@PathVariable("exName")String exName) throws Exception {
		String file = fileName+"."+exName;
		FileDTO dto = saveShareFileServiceImpl.showFile(file);
		// GET请求中，参数中包含中文需要自己动手来转换。
		// 浏览器收到这个文件名后，会使用iso-8859-1来解码
		response.addHeader("content-disposition", "attachment;filename=" + dto.getFileName());
		response.addHeader("Content-Length", "" + dto.getSize());
		//大文件
		InputStream in = dto.getIn();
		OutputStream out = response.getOutputStream();
		// 判断输入或输出是否准备好    
		if (in != null && out != null) {  
			int temp = 0;  
			// 开始拷贝  
			while ((temp = in.read()) != -1) {  
				// 边读边写  
				out.write(temp);  
			}  
			// 关闭输入输出流  
			in.close();  
			out.close();
		}
		
	    //小文件
//		IOUtils.copy(in, response.getOutputStream());
	}
}
