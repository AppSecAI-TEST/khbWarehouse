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
//        int array[] = null; //声明数组
//        array = new int[3]; //为数组开辟空间，大小为3
//        for(int i=0;i<array.length;i++){
//        System.out.println("array["+i+"]="+i);
//        }
//        //访问数组没有开辟的下标,这时会报异常
//        System.out.println("array[3]="+array[3]);
    }  
  
} 