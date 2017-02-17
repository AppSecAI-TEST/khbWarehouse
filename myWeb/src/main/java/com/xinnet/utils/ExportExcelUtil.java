package com.xinnet.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
  
public class ExportExcelUtil {  
    public static void exportExcel(List<Map<String, Object>> dataset, OutputStream out){  
        exportExcel( null, dataset, out, "yyyy-MM-dd");  
    }  
  
    public static void exportExcel(Map<String, Object> headMap, List<Map<String, Object>> dataset,  
            OutputStream out) {  
    	exportExcel(headMap, dataset, out, null);  
    }  
  
    public static void exportExcel(Map<String, Object> headMap,  
    		List<Map<String, Object>> dataset, OutputStream out, String pattern){ 
    	if(StringUtils.isEmpty(pattern)) {
    		pattern = "yyyy-MM-dd HH:mm:ss";
    	}
        // 声明一个工作薄  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        // 生成一个表格  
        HSSFSheet sheet = workbook.createSheet();  
        sheet.setDefaultColumnWidth(15);
        // 产生表格标题行  
        HSSFRow row = sheet.createRow(0); 
        //表头字体
        HSSFFont headFont  = workbook.createFont();
        headFont.setFontName("宋体");
        headFont.setFontHeightInPoints((short) 11);//设置字体大小
        int j = 0;
        HSSFRichTextString richString = null;
        for(String key : headMap.keySet()) {
        	if(null != headMap.get(key)){
        		HSSFCell cell = row.createCell(j);
        		richString = new HSSFRichTextString(headMap.get(key).toString());  
				richString.applyFont(headFont);
                cell.setCellValue(richString);  
                j++;
        	}
        }
  
        // 遍历集合数据，产生数据行  
        int index = 0; 
        //字体颜色
        HSSFFont font = workbook.createFont();  
		font.setColor(HSSFColor.BLUE.index);
        for(Map<String, Object> map : dataset) {
        	index++;  
        	row = sheet.createRow(index); 
        	int i = 0;
        	for(String key : map.keySet()) {
        		HSSFCell cell = row.createCell(i);  
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
        			richString = new HSSFRichTextString(textValue);  
        			richString.applyFont(font);  
        			cell.setCellValue(richString);  
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
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  
    
    public static void download(String filename,HttpServletResponse response,Map<String, Object> headMap,  
    		List<Map<String, Object>> dataset) {  
		// 以流的形式下载文件。  
		response.addHeader("content-disposition", "attachment;filename="+ System.currentTimeMillis()+".xls");
		try {
			exportExcel(headMap,dataset,response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
//            IOUtils.copy(new ByteArrayInputStream(buffer), response.getOutputStream());  
    }
  
    public static void main(String[] args) {  
        Map<String, Object> headMap = new HashMap<String, Object>();
        headMap.put("shuliang", "数量");
        headMap.put("type", "类型");
        headMap.put("sttue", "leno");
        headMap.put("yeshu", "页数");
        headMap.put("haoma", "出版时间");
        headMap.put("chubanshe", "出版社");
        List<Map<String, Object>> dataset2 = new ArrayList<Map<String, Object>>();  
        try  
        {  
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("shuliang", 1);
        	map.put("type", "jsp");
        	map.put("sttue", "leno");
        	map.put("yeshu", 300.34);
        	map.put("haoma", "2016-12-13 23:45:56");
        	map.put("chubanshe", "清华出版社");
        	Map<String, Object> map1 = new HashMap<String, Object>();
        	map1.put("shuliang", 2);
        	map1.put("type", "java编程思想");
        	map1.put("sttue", "brucl");
        	map1.put("yeshu", "3020.33f");
        	map1.put("haoma", new Date());
        	map1.put("chubanshe", "阳光出版社");
        	
        	dataset2.add(map);
        	dataset2.add(map1);
  
            OutputStream out2 = new FileOutputStream("E://c.xls");  
            exportExcel(headMap, dataset2, out2);  
            out2.close();  
            JOptionPane.showMessageDialog(null, "导出成功!");  
            System.out.println("excel导出成功！");  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
