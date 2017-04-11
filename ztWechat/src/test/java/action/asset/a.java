package action.asset;

import java.awt.Point;

import net.sf.json.util.JSONUtils;

import com.yeepay.g3.app.dto.UserOpeDTO;





public class a{
public static <E> void main(String[] args) throws InterruptedException {
	 Point a = new Point(0,0);
	 Point b = new Point(1,1);
	System.out.println(a+" "+b);
	swap(a, b);
	System.out.println(a+" "+b);

}
 static void swap(Point a,Point b){
	 a.x=3;
	 Point c=b;
	 b=a;
	 a=c;
}

}
