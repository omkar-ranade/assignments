package com.springremoting.service;

/**
 * Created by cybage on 29/12/16.
 */
public interface IEmployeeService {

    //public List<Employee> getAllEmployee();
    //public Employee getAllEmployee();

    public String getAllEmployee();
    
    public String getEmployee(String id);

    public void deleteEmployeeById(String employeeId);

    public void addEmployee(String json);
}
