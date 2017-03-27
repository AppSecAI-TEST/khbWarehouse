package hessian.client;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.xinnet.facade.dto.OrderDTO;
import com.xinnet.facade.facade.OrderFacade;
import com.xinnet.facade.facade.UserInfoFacade;

public class test {
	public static void main(String[] args) throws MalformedURLException {    
        String url = "http://localhost:8091/hessian-core/hessian/userInfoFacade";    
        HessianProxyFactory factory = new HessianProxyFactory();    
        UserInfoFacade d = (UserInfoFacade) factory.create(UserInfoFacade.class, url);    
        System.out.println(d.selectUserInfoById("1"));//打印从服务器端获取的字符串    
//        d.printHello("Hessian"); //在服务器端控制台打印 "Hello Hessian"   
        String a = "http://localhost:8091/hessian-core/hessian/orderFacade";
        HessianProxyFactory b = new HessianProxyFactory();
        OrderFacade order = (OrderFacade) b.create(OrderFacade.class, a); 
        OrderDTO o = order.selectByPrimaryKey(36);
        System.out.println(o);//打印从服务器端获取的字符串   
        
    }
}
