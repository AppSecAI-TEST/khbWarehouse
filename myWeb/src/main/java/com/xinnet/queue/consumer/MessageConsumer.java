/**
 * 
 */
/**
 * @author hongbin.kang
 * @date 2017年7月26日下午8:28:54
 */
package com.xinnet.queue.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.xinnet.entity.Book;
import com.xinnet.service.IUserService;
import com.xinnet.utils.ObjectAndByteUtil;
  
/** 
 * Created by wuxing on 2016/9/21. 
 */  
public class MessageConsumer implements MessageListener {  
  
    private Logger logger = LoggerFactory.getLogger(MessageConsumer.class);  
    
  
    @Override  
    public void onMessage(Message message) {
    	Book book = (Book)ObjectAndByteUtil.toObject(message.getBody());
        logger.info("consumer receive message------->:{}", book.getBookId()+"----"+book.getName()); 
    }  
  
} 