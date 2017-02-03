package com.springremoting.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.springremoting.bean.Employee;

/**
 * Created by cybage on 30/12/16.
 */

@Repository
public class MongoDbDao {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Employee> getAllEmployee(){
        List<Employee> employee  = mongoTemplate.findAll(Employee.class);
        return employee;
    }

    public void deleteEmployee(String empId){
        Query query = new Query(Criteria.where("empId").is(empId));
        mongoTemplate.remove(query,Employee.class);

    }


    public void addEmployee(Employee e){
        System.out.println(e.toString());
        mongoTemplate.insert(e);
    }
    
    public Employee getEmployee(String id){
    	 Query query = new Query(Criteria.where("empId").is(id));
    	 Employee e = mongoTemplate.findOne(query, Employee.class);
    	 return e;
    }

}
