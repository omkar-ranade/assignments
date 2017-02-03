package com.springremoting.amqp.sender;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAMQPRabbitSender {

  
	
		@Autowired
        AmqpTemplate amqpTemplate; //= (AmqpTemplate)(new ClassPathXmlApplicationContext(SENDER_XML))
                //.getBean("amqpSendTemplate");

        // Sending sample employee id in employeeSaveQueue 
		
	public void putMessage(String empId){	
        amqpTemplate.convertAndSend("employeeSavedKey", empId);
	}
}
