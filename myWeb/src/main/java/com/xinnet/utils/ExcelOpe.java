package com.xinnet.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelOpe {
	public static void main(String[] args) {
		writeToFile();
		readFromFile();
	}
	
	
	//readFromFile
	public static void readFromFile(){
		File file = new File("d:/test.xls");
		try {
			Workbook workBook = Workbook.getWorkbook(file);
			try{
				Sheet readsheet = workBook.getSheet(0);   
				//获取Sheet表中所包含的总列数   
				int rsColumns = readsheet.getColumns();   
				//获取Sheet表中所包含的总行数   
				int rsRows = readsheet.getRows();   
				//获取指定单元格的对象引用   
				for (int i = 0; i < rsRows; i++){   
					for (int j = 0; j < rsColumns; j++){   
						Cell cell = readsheet.getCell(j, i);   
						System.out.print(cell.getContents() + " ");   
					}   
					System.out.println();   
				}
			}finally{
				if(workBook != null){
					workBook.close();
				}
			}
		} catch (BiffException e) {
			System.err.println(e+"");
		} catch (IOException e) {
			System.err.println(e+"文件读取错误");
		}
	}//end readFromFile
	
	//witeToFile
	public static void writeToFile(){
		File file = new File("d:/test.xls");
		try {
			
			WritableWorkbook book = Workbook.createWorkbook(file);
			//创建一个工作区。(默认的excel文件有三个sheet,在excel的左下角可以看到sheet1/sheet2/sheet3）
			WritableSheet sheet = book.createSheet("第一页", 0);
			//在工作区上面添加内容
			try {
				for(int i = 0; i < 10 ; i ++ ){
					for(int j = 0 ; j < 10 ; j++){
						Label newLabel;
						if(0 == i){
							//第一个参数代表列，第二个参数代表行(默认起始值都为0),第三个参数是要在单元格里面填写的内容发
							newLabel = new Label(j,i,String.valueOf(j));
						}else if(0 == j){
							newLabel = new Label(j,i,String.valueOf(i));
						}else{
							newLabel = new Label(j,i,String.valueOf(i*j));
						}
						//在单元格上面添加注释
						WritableCellFeatures cellFeatures = new WritableCellFeatures();
						cellFeatures.setComment("这里是"+i+"*"+j+"的值");
						newLabel.setCellFeatures(cellFeatures);
						sheet.addCell(newLabel);
					}
				}
			} catch (RowsExceededException e) {
				System.err.println(e+"行或列参数错误！");
			} catch (WriteException e) {
				System.err.println(e+"写入失败");
			}finally{
				if(book != null){
					book.write();
					try {
						book.close();
					} catch (WriteException e) {
						System.err.println(e+"文件关闭失败！");
					}
				}
			}
			
		} catch (IOException e) {
			System.err.println(e+"创建文件失败！");
		}
	}
	
	
}