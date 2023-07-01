package com.nitesh.springboot.RestClient;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.nitesh.springboot.binding.PassangerInfo;
import com.nitesh.springboot.binding.TicketInfo;

@Controller
public class ClientController {
	
	
	@GetMapping("/")
	public String getPage(Model md) {
		
		//String msg = "Welcom to Rest Client App.";
		
		md.addAttribute("msg", "Welcome to Rest Client App");
		
		return "index";
	}
	
	@GetMapping("/client")
	public String getRestClient(Model md) {
		
		//String msg = "Welcome to Rest Client App.";
		
		RestTemplate rt = new RestTemplate();
		
		String url = "http://localhost:8080/getTicket/2";
		
		ResponseEntity<TicketInfo> forEntity = rt.getForEntity(url, TicketInfo.class);
		
		TicketInfo body = forEntity.getBody();
		
		//md.addAttribute("msg", "Welcome to Rest Client App");
		
		md.addAttribute("ticketByid", body);
		
		return "success";
	}
	
	@GetMapping("/deleteBook")
	public String deleteTicket(@RequestParam("id") Integer id, Model md) {
		
		//String msg = "Welcome to Rest Client App.";
		
		RestTemplate rt = new RestTemplate();
		
		String url = "http://localhost:8080/book/"+id;
		
		ResponseEntity<String> forEntity = rt.getForEntity(url, String.class);
		
		String body = forEntity.getBody();
		
		//md.addAttribute("msg", "Welcome to Rest Client App");
		
		md.addAttribute("msg", body);
		
		return "index";
	}

	
	@GetMapping("/postBook")
	public String postTicket(Model md) {
		
		String msg = "Welcome to Rest Client App.";
		md.addAttribute("msg", msg);
		
		PassangerInfo psinfo = new PassangerInfo();
		psinfo.setAge(26);
		psinfo.setContactNumber(431232232343l);
		psinfo.setFirstName("Nitesh");
		psinfo.setLastName("Mishra");
		psinfo.setFromStation("Deoria");
		psinfo.setToStation("Gwalior");
		psinfo.setTicketCount(2);
		psinfo.setJourneyDate("14th-May-2023");
		
		RestTemplate rt = new RestTemplate();
		
		String url = "http://localhost:8080/bookTicket";
		
		ResponseEntity<String> forEntity = rt.postForEntity(url, psinfo, String.class);
		
		String body = forEntity.getBody();		
		
		return "index";
	}
	
}
