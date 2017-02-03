import java.net.MalformedURLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LoadContext {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:springRemotingClient-servlet.xml");
		
		RemotingClient rc = (RemotingClient)context.getBean("remotingClient");
		
		try {
			rc.callEmployeeService();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
