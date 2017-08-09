/**
 * 
 */
/**
 * @author hongbin.kang
 * @date 2017年7月26日下午8:27:51
 */
package com.xinnet.queue.producer;

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
import org.springframework.amqp.core.AmqpTemplate;  
import org.springframework.stereotype.Service;  
  
import javax.annotation.Resource;  
import java.io.IOException;  
  
/** 
 * Created by wuxing on 2016/9/21. 
 */  
@Service  
public class MessageProducer {  
  
    private Logger logger = LoggerFactory.getLogger(MessageProducer.class);  
  
    @Resource
    private AmqpTemplate amqpTemplate;  
  
    @Resource
    private AmqpTemplate amqpTopic;  
    
    @Resource
    private AmqpTemplate amqpFanout;  
  
    public void sendMessage(Object message) throws IOException {  
//        logger.info("to send message:{}", message);  
        amqpTemplate.convertAndSend("queueTestKey", message);  
        amqpTemplate.convertAndSend("queueTestChris", message);  
  
        amqpTopic.convertAndSend("wuxing.xxxx.wsdwd", message); 
        
        amqpFanout.convertAndSend(message);
    }  
}