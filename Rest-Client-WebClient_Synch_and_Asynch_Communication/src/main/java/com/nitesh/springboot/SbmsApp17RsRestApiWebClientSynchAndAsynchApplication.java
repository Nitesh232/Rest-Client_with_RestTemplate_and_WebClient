package com.nitesh.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.nitesh.springboot.RestClient.ClientController;

@SpringBootApplication
public class SbmsApp17RsRestApiWebClientSynchAndAsynchApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(SbmsApp17RsRestApiWebClientSynchAndAsynchApplication.class, args);
		
		ClientController bean = run.getBean(ClientController.class);
		
		//bean.getInvokeTicket();
		
		for(int i = 0; i<5; i++) {
			bean.postInvokeTicket();
		}
		
		bean.getInvokeTicket();
		
	}

}
