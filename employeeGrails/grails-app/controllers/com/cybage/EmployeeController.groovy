package com.cybage

import grails.converters.JSON

import com.cybage.services.IElasticSearchService
import com.cybage.services.IEmployeeService
import com.cybage.utils.CommonsUtil
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper

class EmployeeController {
	
	CommonsUtil commonsUtil;
	
	public void setCommonsUtil(CommonsUtil commonsUtil) {
		this.commonsUtil = commonsUtil;
	}
	def index() { 

	}
	def save() {
		ObjectMapper mapper = new ObjectMapper();
		List<Employee> employeeLst = null;
        try {
            //Add Employee
        	
        	//System.out.println("Date"+ employee.getDob());
			Employee employee = new Employee(params);
			
		/*	String pattern = "MM/DD/YYYY";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

			Date date = simpleDateFormat.parse(employee.getDobStr());
			
			
			employee.setDob(date);
			
			employee.setDobStr(null);*/
			
           // String  employeeJson = mapper.writeValueAsString(employee);
			//String  employeeJson = commonsUtil.serializeEmployee(employee);

            //System.out.println("Json Employee:"+ employeeJson);

            //IEmployeeService employeeService = kkk.getRemoteConnection();
           // IEmployeeGroovy employeeService = commonsUtil.getGroovyRemoteConnection();
			
			/*def convertor = employee As JSON;
			render(contentType: 'text/json') {
			   empId = employee.empId;
			   empFirstName = employee.empFirstName
			   empLastName = employee.empLastName
			   city = employee.city
			}*/
			
			
			employee.setDob(new Date());
			
				/*grails.converters.JSON.registerObjectMarshaller(NAMEOFYOURCLASS) {
					def lista = [:]
					lista['id'] = it.id
					lista['name'] = it.name
					lista['dateCreated'] = it.date?.format("dd/MM/yyyy HH:mm")
					return lista
				}*/
			
			
			
			
			
			//def result = [employee: employee]
			//render result as JSON
			
			def result = new JSON(employee)
			
			
			/*render(contentType: 'text/json') {[
				'results': results,
				'status': results ? "OK" : "Nothing present"
			]}*/
			
            IEmployeeService employeeService = commonsUtil.getRemoteConnection();
			
			
			
            employeeService.addEmployee(result.toString());//employeeJson);

            String jsonEmployeeLst =employeeService.getAllEmployee();

            employeeLst = prepareEmployeeList(jsonEmployeeLst);
			[employeeLst :employeeLst]
			
           /* mav.addObject("empList", employeeLst);
            mav.addObject("employee", new Employee());*/

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		//render(view: "success")
		//render "Success!"
	}
	def search() {

	}
	def searchProcess() {
		def employee = new Employee(params)
		IElasticSearchService elasticSearchEmployee = commonsUtil.getElasticSearchServerCon();
        	String empString = elasticSearchEmployee.searchEmployee(employee.empId);
        	
        	ObjectMapper mapper = new ObjectMapper();
        	def emp = mapper.readValue(empString, Employee.class);
		[ employee:emp ]
	}
	
	def prepareEmployeeList(String jsonEMployeeLst) throws IOException {
		
				List<Employee> employeeList;
		
				ObjectMapper mapper =new ObjectMapper();
				TypeReference<List<Employee>> listType = new TypeReference<List<Employee>>() { };
				employeeList = mapper.readValue(jsonEMployeeLst, listType);
				return employeeList;
			}
}
