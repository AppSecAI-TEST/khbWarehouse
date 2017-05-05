package com.xinnet.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
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

import com.xinnet.entity.Book;
  
/**
 * excle导出类
 * @author hongbin.kang
 * @date 2016年4月2日下午3:27:21
 */
public class ExportExcelUtil {
	
	
	/**
	 * excle导出
	 * @author hongbin.kang
	 * @date 2016年4月2日 下午3:37:30
	 * @param dataset
	 * @param out
	 */
    public static <E> void exportExcel(List<E> dataset, OutputStream out){  
        exportExcel( null, dataset, out, "yyyy-MM-dd");  
    }  
  
    /**
     * excle导出
     * @author hongbin.kang
     * @date 2016年4月2日 下午3:37:37
     * @param headMap
     * @param dataset
     * @param out
     */
    public static <E> void exportExcel(Map<String, Object> headMap, List<E> dataset,  
            OutputStream out) {  
    	exportExcel(headMap, dataset, out, null);  
    }  
  
    /**
     * excle导出
     * @author hongbin.kang
     * @date 2016年4月2日 下午3:37:06
     * @param headMap
     * @param dataset
     * @param out
     * @param pattern
     */
    public static <E> void exportExcel(Map<String, Object> headMap,  
    		List<E> dataset, OutputStream out, String pattern){ 
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
        
        for (Map.Entry<String,Object> entry : headMap.entrySet()) {
        	Object value = entry.getValue();
        	if(null != value){
        		setCellValue(row, j, headFont, value);
        		j++;
        	}
        }
  
        // 遍历集合数据，产生数据行  
        int index = 0; 
        //字体颜色
        HSSFFont font = workbook.createFont();  
		font.setColor(HSSFColor.BLACK.index);
        for(E obj : dataset) {
        	index++;  
        	row = sheet.createRow(index); 
        	int i = 0;
        	for(String fieldName : headMap.keySet()) {
        		try{
        			Object value = forceGetFieldValue(obj, fieldName);
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
        			setCellValue(row, i, font, textValue);
        			
        		}catch (SecurityException | IllegalAccessException e) {  
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
			e.printStackTrace();
		}
    }  
    
    
    /**
     * 下载excle
     * @author hongbin.kang
     * @date @date 2016年4月2日下午3:27:21
     * @param filename
     * @param response
     * @param headMap
     * @param dataset
     */
    public static <E> void download(String filename,HttpServletResponse response,Map<String, Object> headMap,  
    		List<E> dataset) {  
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
  
    /** 
     * 获取指定对象的指定属性值（去除private,protected的限制） 
     *  若是map直接获取属性
     * @param obj 
     *            属性名称所在的对象 
     * @param fieldName 
     *            属性名称 
     * @author hongbin.kang
     * @return 
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     * @throws Exception 
     */  
    @SuppressWarnings("rawtypes")
	public static Object forceGetFieldValue(Object obj, String fieldName) throws IllegalAccessException {
    	if(obj instanceof Map) {
    		return ((Map) obj).get(fieldName) == null ? "" : ((Map) obj).get(fieldName);
    	} else {
    		Field field;
			try {
				field = obj.getClass().getDeclaredField(fieldName);
			} catch (NoSuchFieldException | SecurityException e) {
				field = null;
			}  
    		Object object; 
    		if(null != field) {
    			boolean accessible = field.isAccessible();  
    			if (!accessible) {  
    				// 如果是private,protected修饰的属性，需要修改为可以访问的  
    				field.setAccessible(true);  
    				object = field.get(obj);  
    				// 还原private,protected属性的访问性质  
    				field.setAccessible(accessible);  
    				return object;  
    			}  
    			object = field.get(obj);  
    			return object;  
    		}
    		return "";
    	}
    } 
    
    /**
     * 表格写入值
     * @author hongbin.kang
     * @date 2017年5月5日 下午5:18:14
     * @param row 行
     * @param coluNumber 列号
     * @param headFont 字体
     * @param value 写入的值
     */
    public static void setCellValue(HSSFRow row , int coluNumber,HSSFFont headFont,Object value){
    	HSSFCell cell = row.createCell(coluNumber);
    	HSSFRichTextString richString = new HSSFRichTextString(value.toString());  
    	richString.applyFont(headFont);
    	cell.setCellValue(richString); 
    } 
    
    public static void main(String[] args) {  
    	Map<String, Object> headMap = new HashMap<String, Object>();
    	headMap.put("haoma", "出版时间");
    	headMap.put("shuliang", "数量");
    	headMap.put("type", "类型");
    	headMap.put("sttue", "leno");
    	headMap.put("yeshu", "页数");
    	headMap.put("chubanshe", "出版社");
    	headMap.put("name", "名称");
    	headMap.put("author", "作者");
    	List<Map<String, Object>> dataset2 = new ArrayList<>(); 
    	List<Book> bookList = new ArrayList<>();
    	try{  
    		Map<String, Object> map = new HashMap<>();
    		map.put("shuliang", 1);
    		map.put("type", "jsp");
    		map.put("sttue", "leno");
    		map.put("haoma", "2016-12-13 23:45:56");
    		map.put("chubanshe", "清华出版社");
    		map.put("yeshu", 300.34);
    		Map<String, Object> map1 = new HashMap<>();
    		map1.put("shuliang", 2);
    		map1.put("yeshu", "3020.33f");
    		map1.put("type", "java编程思想");
    		map1.put("sttue", "brucl");
    		map1.put("haoma", new Date());
    		map1.put("chubanshe", "阳光出版社");
    		
    		dataset2.add(map);
    		dataset2.add(map1);
    		bookList.add(new Book(0, "java编程思想", "程成华"));
    		bookList.add(new Book(1, "spring", "程成华的儿子"));
    		
    		OutputStream out2 = new FileOutputStream("E://c.xls");  
    		exportExcel(headMap, bookList, out2);  
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
