package com.springremotingclient.commonsUtil;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.caucho.hessian.client.HessianProxyFactory;
import com.springremotingclient.service.IElasticSearchService;
import com.springremotingclient.service.IEmployeeService;
/**
 * Created by cybage on 03/01/17.
 */

//@Configuration
@PropertySource(value = "classpath:client.properties")
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

    public IEmployeeService getRemoteConnection() throws MalformedURLException{
        employee = (IEmployeeService)factory.create(IEmployeeService.class, eployeeServiceUrl);
        return employee;
    }
    
    public IElasticSearchService getElasticSearchServerCon() throws MalformedURLException {
    	 elasticSearch = (IElasticSearchService)factory.create(IElasticSearchService.class, elasticServerUrl);
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
    

  @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }


}
