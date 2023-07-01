package com.nitesh.springboot.RestClient;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nitesh.springboot.binding.TicketInfo;
import com.nitesh.springboot.constants.ConstantVariables;

@Service
public class ClientController {
	
	//REST Client with WebClient Synchronous communication example
	
	
	/*
	public void getInvokeTicket() {
		
		String url = "http://localhost:8080/getTickets";
		
		WebClient client = WebClient.create();
		
		String block = client.get().uri(url).retrieve().bodyToMono(String.class).block();	
		
		System.out.println(block);
	}
	*/
	
	
	/*
	public void getInvokeTicket() {
		
		String url = "http://localhost:8080/getTickets";
		
		WebClient client = WebClient.create();
		
		TicketInfo[] block = client.get().uri(url).retrieve().bodyToMono(TicketInfo[].class).block();	
		
		for(TicketInfo tinf : block) {
			System.out.println(tinf);
		}
	}
	*/
	
	
	//REST Client with WebClient Asynchronous communication example
	
	
	public void getInvokeTicket() {
		
		String url = "http://localhost:8080/getTickets";
		
		WebClient client = WebClient.create();
		
		client.get().uri(url).retrieve().bodyToMono(TicketInfo[].class).subscribe(ClientController::respHandler);	
		
		System.out.println("**************Asynch-Application********");
	}
	
	
	public static void respHandler(TicketInfo[] tinf) {
		
		for(TicketInfo inf : tinf) {
			System.out.println(inf);
		}
		
	}

	
	
	public void postInvokeTicket() {
		
		String url = "http://localhost:8080/bookTicket";
		
		TicketInfo tinf = new TicketInfo();
		tinf.setAge(24);
		tinf.setContactNumber(34323423323l);
		tinf.setFirstName("Lovlesh");
		tinf.setLastName("Tiwari");
		tinf.setFromStation("Chandigarh");
		tinf.setToStation("Varanasi");
		tinf.setJourneyDate("23rd-May-2022");
		tinf.setTicketCount(3);
		
		
		
		tinf.setTicketBookingDate(new Date());
		tinf.setTicketPNR(UUID.randomUUID().toString());
		tinf.setTicketPrice(tinf.getTicketCount()*1024.52);		
		
		ConstantVariables cnstVar = new ConstantVariables();
		tinf.setTicketStatus("Confirmed");
		boolean initialPresent = cnstVar.isInitialPresent(tinf.getFirstName().toLowerCase().charAt(0));
		System.out.println("Value of initialPresent :: "+ initialPresent);
		if(!initialPresent){
			tinf.setTicketStatus("Not-Confirmed, Under Waiting");
		}
		
			
		
		
		WebClient client = WebClient.create();
		
		String block = client.post().uri(url).bodyValue(tinf).retrieve().bodyToMono(String.class).block();	
		
		System.out.println(block);
	}
	
}
