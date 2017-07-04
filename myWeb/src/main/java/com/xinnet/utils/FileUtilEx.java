package com.xinnet.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtilEx {
	private static final Logger logger = LoggerFactory.getLogger(FileUtilEx.class);
	final static String prefixStr = "	";
	final static String printlnStr = "\r\n";
	final static String quatoStr = "\"";
	final static String endStr = ";";

	public static void writeFile(String file, byte[] data) throws IOException {
		FileUtils.writeByteArrayToFile(new File(file), data);
	}

	public static void appendFile(String file, byte[] data) throws IOException {
		FileUtils.writeByteArrayToFile(new File(file), data, true);
	}

	public static String readFile(String file) throws IOException {
		return FileUtils.readFileToString(new File(file));
	}

	public static String readFile(String file, String encoding) throws IOException {
		return FileUtils.readFileToString(new File(file), encoding);
	}

	public static List<String> readLine(String file) throws IOException {
		return FileUtils.readLines(new File(file));
	}

	public static void remove(String file) throws IOException {
		FileUtils.forceDelete(new File(file));
	}

	public static void copyFile(String oldPath, String newPath) {
		File source = new File(oldPath);
		File destination = new File(newPath);
		byte[] buf = new byte[1024];
		int len;
		try { 
			FileInputStream fi = new FileInputStream(source);
			BufferedInputStream in = new BufferedInputStream(fi);
			FileOutputStream fo = new FileOutputStream(destination);
			BufferedOutputStream out = new BufferedOutputStream(fo);

			len = in.read(buf);
			while (len != -1) {
				out.write(buf, 0, len);
				len = in.read(buf);
			}
		} catch (IOException e) {
			logger.error("copyFile has error !", e);
		}
	}

	public static String creatNewFile(String folder, InputStream iStream) {
		String uuid = "winchannel_" + UUID.randomUUID().toString().replace("-", "") + ".txt";
		String uuidFileName = folder + File.separator + uuid;
		File file = new File(uuidFileName);// 本地生成的文件
		byte[] Buffer = new byte[4096 * 5];
		int size = 0;
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				logger.error("createNewFile has error !", e);
			}
		}
		try (FileOutputStream outputStream = new FileOutputStream(file)) {
			while ((size = iStream.read(Buffer)) != -1) {
				outputStream.write(Buffer, 0, size);
			}
		} catch (Exception e) {
			logger.error("file write has error !", e);
		}
		return uuid;
	}

	public static List<String> getFileNamesStr(String folder) {
		List<String> fileNameList = new ArrayList<String>();
		String test[];
		File f = new File(folder);
		if (!f.exists()) {
			f.mkdirs();
		}
		test = f.list();
		for (int i = 0; i < test.length; i++) {
			fileNameList.add(test[i]);
		}
		return fileNameList;
	}

	public static void createJunitFile(String fullPath, String fileName) throws IOException {
		File f = new File(fullPath);
		if (!f.exists()) {
			StringBuffer fileContent = new StringBuffer();
			fileContent.append("package com.winchannel.junittest;").append(printlnStr);
			fileContent.append("import org.junit.Before;").append(printlnStr);
			fileContent.append("import org.junit.Test;").append(printlnStr);
			fileContent.append("import static org.junit.Assert.assertEquals;").append(printlnStr);
			fileContent.append("import static org.junit.Assert.fail;").append(printlnStr);
			fileContent.append("import com.winchannel.junittest.util.GetPostDataUtil;").append(printlnStr);
			fileContent.append("import com.winchannel.junittest.util.JSONCompareUtil;").append(printlnStr);
			fileContent.append("public class " + fileName + " {").append(printlnStr);
			fileContent.append("}");
			f.createNewFile();
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(fullPath))){
				bw.write(fileContent.toString());
			}catch(IOException e){
				logger.error("createJunitFile has error !", e);
			}
		}

	}

	public static void addTestCase2JunitFile(String file, String testUnitName, String naviURL, String type,
			String requestPara, String content, String mishi, String compareFields, String rule) {
		StringBuffer sb = new StringBuffer(4096);
		String temp = null;
		String temp1 = null;
		int line = 0;
		int tempLine = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(file));
				BufferedReader br1 = new BufferedReader(new FileReader(file));
				BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

			while ((temp = br.readLine()) != null) {
				tempLine++;
			}
			while ((temp1 = br1.readLine()) != null) {
				line++;
				if (line == tempLine)
					continue;
				sb.append(temp1).append(printlnStr);
			}
			sb.append("@Test").append(printlnStr);
			sb.append("public void ").append(testUnitName).append("(){").append(printlnStr);
			sb.append(prefixStr).append("String naviURLStr = ").append(quatoStr).append(naviURL).append(quatoStr)
					.append(endStr).append(printlnStr);
			sb.append(prefixStr).append("String ruleStr = ").append(quatoStr).append(rule).append(quatoStr)
					.append(endStr).append(printlnStr);
			sb.append(prefixStr).append("String typeStr = ").append(quatoStr).append(type).append(quatoStr)
					.append(endStr).append(printlnStr);
			sb.append(prefixStr).append("String requestParaStr = ").append(quatoStr)
					.append(requestPara.replace("\"", "'")).append(quatoStr).append(endStr).append(printlnStr);
			sb.append(prefixStr).append("String contentStr = ").append(quatoStr).append(content.replace("\"", "'"))
					.append(quatoStr).append(endStr).append(printlnStr);
			sb.append(prefixStr).append("String mishiStr = ").append(quatoStr).append(mishi).append(quatoStr)
					.append(endStr).append(printlnStr);
			sb.append(prefixStr).append("String compareFieldsStr = ").append(quatoStr).append(compareFields)
					.append(quatoStr).append(endStr).append(printlnStr);
			sb.append(prefixStr).append("String resp = ").append(quatoStr).append(quatoStr).append(endStr)
					.append(printlnStr);
			String typeStr = type.substring(0, 1);
			if (!typeStr.equals("4")) {
				sb.append(prefixStr)
						.append("resp = GetPostDataUtil.getData(naviURLStr, requestParaStr, typeStr, mishiStr)")
						.append(endStr).append(printlnStr);
			} else {
				sb.append(prefixStr)
						.append("resp = GetPostDataUtil.postData(naviURLStr, requestParaStr, typeStr, mishiStr)")
						.append(endStr).append(printlnStr);
			}

			sb.append(prefixStr)
					.append("assertEquals(true, JSONCompareUtil.compare(compareFieldsStr, contentStr, resp, ruleStr))")
					.append(endStr).append(printlnStr);
			sb.append(prefixStr).append("}").append(printlnStr);
			sb.append("}");
			bw.write(sb.toString());
		} catch (Exception e) {
			logger.error("addTestCase2JunitFile has error !", e);
		}
	}

}
