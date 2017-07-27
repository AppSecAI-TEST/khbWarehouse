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

import com.xinnet.entity.Book;
  
/** 
 * Created by wuxing on 2016/9/21. 
 */  
public class ChrisConsumer implements MessageListener {  
    private Logger logger = LoggerFactory.getLogger(ChrisConsumer.class);  
  
    @Override  
    public void onMessage(Message message) {  
        logger.info("chris receive message------->:{}", message.getBody());  
    }  
} 