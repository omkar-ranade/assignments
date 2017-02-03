package com.springremotingclient.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springremotingclient.bean.Employee;
import com.springremotingclient.commonsUtil.CommonsUtil;
import com.springremotingclient.service.IElasticSearchService;
import com.springremotingclient.service.IEmployeeService;

/**
 * Created by cybage on 29/12/16.
 */

@Controller
public class EmployeeController {

    //@Autowired
    CommonsUtil commonsUtil;

    public void setCommonsUtil(CommonsUtil commonsUtil) {
        this.commonsUtil = commonsUtil;
    }

    @RequestMapping(value ="employee", method = RequestMethod.POST )
    public ModelAndView getAllEmployee(@ModelAttribute("employee") Employee em){

    ModelAndView mav = new ModelAndView();

        IEmployeeService employee ;

        try {
            employee = commonsUtil.getRemoteConnection();//(IEmployeeService)factory.create(IEmployeeService.class, url);
            //Groovy Service
           // IEmployeeGroovy employee = commonsUtil.getGroovyRemoteConnection();
        	
            String jsonEmployeeLst =employee.getAllEmployee();

            List<Employee> employeeLst = prepareEmployeeList(jsonEmployeeLst);

            mav.addObject("empList", employeeLst);
            mav.setViewName("employee");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  mav;
}

    private List<Employee> prepareEmployeeList(String jsonEMployeeLst) throws IOException {

        List<Employee> employeeList;

        ObjectMapper mapper =new ObjectMapper();
        TypeReference<List<Employee>> listType = new TypeReference<List<Employee>>() { };
        employeeList = mapper.readValue(jsonEMployeeLst, listType);
        return employeeList;
    }
 
    @RequestMapping(value="deleteEmployee")
    public ModelAndView deleteEmployee(@ModelAttribute("employee") Employee employee,@RequestParam(value = "employeeId") String employeeId){
        ModelAndView mav = new ModelAndView();
       // List<Employee> employeeLst = new ArrayList<Employee>();
        try {
            //Groovy Service
           // IEmployeeGroovy employeeService = commonsUtil.getGroovyRemoteConnection();

        	IEmployeeService employeeService = commonsUtil.getRemoteConnection();
            employeeService.deleteEmployeeById(employeeId);



            String jsonEmployeeLst =employeeService.getAllEmployee();

            List<Employee> employeeLst = prepareEmployeeList(jsonEmployeeLst);

            mav.addObject("empList", employeeLst);
            mav.addObject("employee", new Employee());
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*mav.setViewName("employee");*/
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping(value="addEmployee", method = RequestMethod.POST)
    public ModelAndView addEmployee(@ModelAttribute("employee") Employee employee, BindingResult bindingResult, Map<String, String> model){
        ModelAndView mav = new ModelAndView();
        //List<Employee> employeeLst = new ArrayList<Employee>();

        ObjectMapper mapper = new ObjectMapper();

        try {
            //Add Employee
        	
        	System.out.println("Date"+ employee.getDob());

            String  employeeJson = mapper.writeValueAsString(employee);

            System.out.println("Json Employee:"+ employeeJson);

            //IEmployeeService employeeService = commonsUtil.getRemoteConnection();
           // IEmployeeGroovy employeeService = commonsUtil.getGroovyRemoteConnection();
            IEmployeeService employeeService = commonsUtil.getRemoteConnection();
            employeeService.addEmployee(employeeJson);

            String jsonEmployeeLst =employeeService.getAllEmployee();

            List<Employee> employeeLst = prepareEmployeeList(jsonEmployeeLst);

            mav.addObject("empList", employeeLst);
            mav.addObject("employee", new Employee());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

       /* mav.setViewName("employee");*/
        mav.setViewName("index");
        return mav;

    }

    @RequestMapping(value = "welcome")
    public ModelAndView welcomePage(@ModelAttribute("employee") Employee employee){
        ModelAndView mav =new ModelAndView();
        //mav.setViewName("welcome");
        mav.setViewName("index");
        return mav;
    }

    
    @RequestMapping(value="getEmployeeById", method = RequestMethod.POST)
    public ModelAndView getEmployeeById(@ModelAttribute("employee") Employee employee,@RequestParam(value = "employeeId") String employeeId){
        ModelAndView mav = new ModelAndView();
       // List<Employee> employeeLst = new ArrayList<Employee>();
        try {
            //Groovy Service
           // IEmployeeGroovy employeeService = commonsUtil.getGroovyRemoteConnection();

        	//IEmployeeService employeeService = commonsUtil.getRemoteConnection();
        	//String empString = employeeService.getEmployee(employeeId);
        	
        	IElasticSearchService elasticSearchEmployee = commonsUtil.getElasticSearchServerCon();
        	String empString = elasticSearchEmployee.searchEmployee(employeeId);
        	
        	ObjectMapper mapper = new ObjectMapper();
        	employee = mapper.readValue(empString, Employee.class);
        	
        	mav.addObject("employee", employee);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       /* mav.setViewName("searchEmployee");*/
        mav.setViewName("search");
        return mav;
    }
    

    @RequestMapping(value="search", method = RequestMethod.POST)
    public ModelAndView searchPage(@ModelAttribute("employee") Employee employee,@RequestParam(value = "employeeId") String employeeId){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("search");
        return mav;
    }

    
    
    
   /* @Autowired
    TestGroovyService groovyS;

    @RequestMapping("groovy")
    public void testGroovyService(){
        groovyS.testGroovy();
    }
*/
}
