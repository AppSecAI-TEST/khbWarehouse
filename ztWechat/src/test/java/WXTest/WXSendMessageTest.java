/**
 * @author 陈大涛
 * 2016-6-1下午2:47:27
 */
package WXTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import com.yeepay.g3.facade.activity.dto.ActivityWXSendMessageDTO;
import com.yeepay.g3.facade.activity.enums.ActivityWXSendMessageEnum;
import com.yeepay.g3.facade.activity.facade.ActivityWXSendMessageFacade;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;

/**
 * @author 陈大涛
 * 不能用
 * 2016-6-1下午2:47:27
 */
public class WXSendMessageTest {
	
	public static void sendMessage(){
		ActivityWXSendMessageFacade activityWXSendMessageFacadeImpl = RemoteServiceFactory
					.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityWXSendMessageFacade.class);
		ActivityWXSendMessageDTO dataDto = new ActivityWXSendMessageDTO();
		dataDto.setFirst("测试领取抽奖机会");
		dataDto.setOpenId("ocJuwtzlmzg08SZS-iZkxiujHVUs");
		dataDto.setUrl("www.baidu.com");
		dataDto.setRemark("测试领取抽奖机会remark");
		dataDto.setToName("陈大涛");
		dataDto.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		dataDto.setGift("抽奖机会一次");
		activityWXSendMessageFacadeImpl.sendWxMessage(ActivityWXSendMessageEnum.GET_CHANCE,dataDto);
	}
	
	public static void main(String[] args) {
		sendMessage();
	}
}
