import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.springremotingclient.bean.Employee;
import com.springremotingclient.service.IEmployeeService;


public class RemotingClient {
	
	public static void main(String[] args) {
		RemotingClient rc = new RemotingClient();
		try {
			rc.callEmployeeService();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void callEmployeeService() throws MalformedURLException{
		String url = "http://localhost:8890/SpringRemoting/EmployeeService";
		HessianProxyFactory factory = new HessianProxyFactory();
		IEmployeeService basic = (IEmployeeService) factory.create(IEmployeeService.class, url);
		String jsonEmployeeLst =basic.getAllEmployee();
		System.out.println("hello(): " + jsonEmployeeLst);
	}

}
