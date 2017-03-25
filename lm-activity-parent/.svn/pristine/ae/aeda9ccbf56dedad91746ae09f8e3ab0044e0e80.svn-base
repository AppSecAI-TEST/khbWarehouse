/**
 * 
 */
package com.yeepay.g3.core.activity.utils;

import java.io.CharArrayWriter;
import java.util.Map;

import freemarker.template.Template;

/**
 * @Description 用户消息内容工具类
 * @author zhenping.zhou
 * @CreateTime 2016年6月5日 上午10:09:04
 * @version 1.0
 */
public class UserMessageContentUtil {
	
	/**
	 * 创建用户消息内容
	 * @param sendedParams
	 * @param ftl
	 * @return
	 * @throws Exception
	 */
	public static String generateUserMsgContent(Map<String, Object> sendedParams, Template ftl) throws Exception {
		
        CharArrayWriter writer = null;
        String msgContent = null;
        try {
            writer = new CharArrayWriter(1024);
            // 使用一个输出流，将输出流写入String
            ftl.process(sendedParams, writer);
            msgContent = writer.toString();
        } finally {
            if (writer != null)
                writer.close();
        }
        return msgContent;
    }
}
