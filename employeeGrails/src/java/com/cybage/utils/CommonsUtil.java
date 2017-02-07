package com.cybage.utils;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.caucho.hessian.client.HessianProxyFactory;
import com.cybage.Employee;
import com.cybage.services.IElasticSearchService;
import com.cybage.services.IEmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Created by cybage on 03/01/17.
 */

//@Configuration
//@Component
@PropertySource(value = "/WEB-INF/client.properties")
public class CommonsUtil {

		
    @Value("${employeeService.url}")
    private String eployeeServiceUrl;

    @Value("${groovyService.url}")
    private String groovyServiceUrl;
    
    @Value("${elasticServer.url}")
    private String elasticServerUrl;

    IEmployeeService employee;
    IElasticSearchService elasticSearch; 
    //IEmployeeGroovy groovyEmployee;
    HessianProxyFactory factory = new HessianProxyFactory();
    ObjectMapper mapper = new ObjectMapper();
    
    public IEmployeeService getRemoteConnection() throws MalformedURLException{
        employee = (IEmployeeService)factory.create(IEmployeeService.class, "http://192.168.62.148:8890/SpringRemoting/EmployeeService");
        return employee;
    }
    
    public IElasticSearchService getElasticSearchServerCon() throws MalformedURLException {
    	 elasticSearch = (IElasticSearchService)factory.create(IElasticSearchService.class, "http://192.168.62.130:8080/SearchEmployeeService/SearchEmployee");
         return elasticSearch;
    }


 /*   public IEmployeeGroovy getGroovyRemoteConnection() throws MalformedURLException{
        groovyEmployee = (IEmployeeGroovy)factory.create(IEmployeeGroovy.class, groovyServiceUrl);
        return groovyEmployee;
    }*/

    
    
    public Date formatDate(String date){
        //formatter to format the date in "yyyy-MM-dd" format and returns the Date object
         SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
         Date dt = null;
         try {
             dt = formatter.parse(date);
         } catch (ParseException e) {
             e.printStackTrace();
         }
         return dt;
     }
    

    private String serializeEmployee(Employee employee){
    	String json = null;
    	try {
			json = mapper.writeValueAsString(employee);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return json;
    }
    
  @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }


}
