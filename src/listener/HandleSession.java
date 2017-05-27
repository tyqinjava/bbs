package listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class HandleSession implements HttpSessionListener {

	private int count=0;
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		++count;
		System.out.println("当前共计有："+count+"个用户在线");
		System.out.println("sessionCreate");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		if(count>0){
			--count;
		}
		System.out.println("sessionDestroyed");
	}
	
}
