/**
 * 
 */
/**
 * @author hongbin.kang
 * @date 2017年7月26日下午8:28:54
 */
package com.xinnet.queue.consumer;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.xinnet.entity.Book;
import com.xinnet.utils.DateUtil;
import com.xinnet.utils.ObjectAndByteUtil;
  
/** 
 * Created by wuxing on 2016/9/21. 
 */  
public class ChrisConsumer implements MessageListener {  
    private Logger logger = LoggerFactory.getLogger(ChrisConsumer.class);  
  
    @Override  
    public void onMessage(Message message) {  
        Book book = (Book)ObjectAndByteUtil.toObject(message.getBody());
//        try {  
//            //暂停一下，好让消息消费者去取消息打印出来  
//            Thread.sleep(13000);  
//        } catch (InterruptedException e) {  
//            e.printStackTrace();  
//        }
        logger.info("chris receive message------->:{}", book.getBookId()+"----"+book.getName()+"-----"+DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS)); 
    }  
} 