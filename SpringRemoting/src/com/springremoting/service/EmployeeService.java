package com.springremoting.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springremoting.amqp.sender.SpringAMQPRabbitSender;
import com.springremoting.bean.Employee;
import com.springremoting.dao.MongoDbDao;

/**
 * Created by cybage on 29/12/16.
 */
public class EmployeeService implements IEmployeeService{

    @Autowired
    MongoDbDao dao;
    
    @Autowired
    SpringAMQPRabbitSender sender;

    public String getAllEmployee() {
        List<Employee> empLst = dao.getAllEmployee();
        String jsonEmployeeLst = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonEmployeeLst = mapper.writeValueAsString(empLst);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        /*for(Employee emp : empLst){
            System.out.println(emp.getEmpId());
        }
        Employee dbEmployee = empLst.get(0);
        Employee emp = new Employee();
        emp.setEmpId("Cyb111");
        emp.setEmpFirstName("TestEmployee");
        emp.setEmpLastName("LastName");*/
        //empLst.add(emp);
        return jsonEmployeeLst;
    }

    public void deleteEmployeeById(String employeeId) {
        dao.deleteEmployee(employeeId);
    }

    public void addEmployee(String json) {
        System.out.println("Service : "+json);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Employee employee = mapper.readValue(json, Employee.class);
            dao.addEmployee(employee);
            System.out.println("AMQP CALL");
            sender.putMessage(employee.getEmpId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }

	@Override
	public String getEmployee(String id) {
		Employee e = dao.getEmployee(id);
		ObjectMapper mapper = new ObjectMapper();
		String empString =null;
		try {
			empString = mapper.writeValueAsString(e);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return empString;
	}

}
