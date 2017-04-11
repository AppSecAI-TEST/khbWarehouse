package com.yeepay.g3.boss.activity.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.util.IOUtils;
  
public class ExportExcelUtil {  
    public static void exportExcel(List<Map<String, Object>> dataset, OutputStream out){  
        exportExcel("基金菜单", null, dataset, out, "yyyy-MM-dd");  
    }  
  
    public static void exportExcel(Map<String, Object> headers, List<Map<String, Object>> dataset,  
            OutputStream out) {  
    	exportExcel("基金菜单", headers, dataset, out, null);  
    }  
  
    public static void exportExcel(Map<String, Object> headers, List<Map<String, Object>> dataset,  
            OutputStream out, String pattern){  
    	exportExcel("基金菜单", headers, dataset, out, pattern);  
    }  
  
    @SuppressWarnings("unchecked")  
    public static void exportExcel(String title, Map<String, Object> headers,  
    		List<Map<String, Object>> dataset, OutputStream out, String pattern){ 
    	if(StringUtils.isEmpty(pattern)) {
    		pattern = "yyyy-MM-dd HH:mm:ss";
    	}
        // 声明一个工作薄  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        // 生成一个表格  
        HSSFSheet sheet = workbook.createSheet(title);  
        // 设置表格默认列宽度为15个字节  
        sheet.setDefaultColumnWidth(15);  
        // 生成一个样式  
        HSSFCellStyle style = workbook.createCellStyle();  
        // 设置这些样式  
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        // 生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setColor(HSSFColor.VIOLET.index);  
        font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        // 把字体应用到当前的样式  
        style.setFont(font);  
        // 生成并设置另一个样式  
        HSSFCellStyle style2 = workbook.createCellStyle();  
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);  
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        // 生成另一个字体  
        HSSFFont font2 = workbook.createFont();  
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
        // 把字体应用到当前的样式  
        style2.setFont(font2);  
  
        // 产生表格标题行  
        HSSFRow row = sheet.createRow(0);
        int j = 0;
        for(String key : headers.keySet()) {
        	HSSFCell cell = row.createCell(j);  
            cell.setCellStyle(style);  
            HSSFRichTextString text = new HSSFRichTextString(headers.get(key).toString());  
            cell.setCellValue(text);
            j++;
        }
  
        // 遍历集合数据，产生数据行  
        int index = 0; 
        for(Map<String, Object> map : dataset) {
        	index++;  
        	row = sheet.createRow(index); 
        	int i = 0;
        	for(String key : map.keySet()) {
        		HSSFCell cell = row.createCell(i);  
        		cell.setCellStyle(style2);  
        		try{
        			Object value = map.get(key);  
        			// 判断值的类型后进行强制类型转换  
        			String textValue = null;  
        			if (value instanceof Date) {  
        				Date date = (Date) value;  
        				SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
        				textValue = sdf.format(date);  
        			} else {  
        				// 其它数据类型都当作字符串简单处理  
        				if(null != value) {
        					textValue = value.toString();  
        				} 
        			}  
        			// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成  
        			if (textValue != null){  
        				Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
        				Matcher matcher = p.matcher(textValue);  
        				if (matcher.matches()){  
        					// 是数字当作double处理  
        					cell.setCellValue(Double.parseDouble(textValue));  
        				}else{  
        					HSSFRichTextString richString = new HSSFRichTextString(  
        							textValue);  
        					HSSFFont font3 = workbook.createFont();  
        					font3.setColor(HSSFColor.BLUE.index);  
        					richString.applyFont(font3);  
        					cell.setCellValue(richString);  
        				}  
        			}  
        		}catch (SecurityException e) {  
        			e.printStackTrace();  
        		}finally {  
        			// 清理资源  
        			i++;
        		}  
        		
        	}
        }
        try {
			workbook.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }  
    
    public static void download(String filename,HttpServletResponse response,Map<String, Object> headers,  
    		List<Map<String, Object>> dataset) {  
		// 以流的形式下载文件。  
		response.addHeader("content-disposition", "attachment;filename="+ System.currentTimeMillis()+".xls");
		try {
			exportExcel(headers,dataset,response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
//            IOUtils.copy(new ByteArrayInputStream(buffer), response.getOutputStream());  
    }
}
