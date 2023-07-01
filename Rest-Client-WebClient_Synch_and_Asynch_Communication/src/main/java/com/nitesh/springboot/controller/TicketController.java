package com.nitesh.springboot.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.springboot.binding.PassangerInfo;
import com.nitesh.springboot.binding.TicketInfo;
import com.nitesh.springboot.constants.ConstantVariables;
import com.nitesh.springboot.service.TicketService;

@RestController
public class TicketController {
	
	
	@Autowired
	private TicketService ticket;
	
	
	@GetMapping(value = "/getTickets")
	public ResponseEntity<List<TicketInfo>> getAllTicket(){
		
		List<TicketInfo> allTickets = ticket.getAllTickets();
		
		return new ResponseEntity<List<TicketInfo>>(allTickets, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getTicket/{id}")
	public ResponseEntity<Optional<TicketInfo>> getTicket(@PathVariable("id") Integer id){
		
		Optional<TicketInfo> ticketByid = ticket.getTicketByid(id);
		
		return new ResponseEntity<Optional<TicketInfo>>(ticketByid, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/bookTicket")			
	public ResponseEntity<TicketInfo> bookTicket(@RequestBody PassangerInfo pinf){
		
		TicketInfo tinf = new TicketInfo();
		
		BeanUtils.copyProperties(pinf, tinf);
		
		tinf.setTicketBookingDate(new Date());
		tinf.setTicketPNR(UUID.randomUUID().toString());
		tinf.setTicketPrice(pinf.getTicketCount()*1024.52);		
		
		ConstantVariables cnstVar = new ConstantVariables();
		tinf.setTicketStatus("Confirmed");
		boolean initialPresent = cnstVar.isInitialPresent(pinf.getFirstName().toLowerCase().charAt(0));
		System.out.println("Value of initialPresent :: "+ initialPresent);
		if(!initialPresent){
			tinf.setTicketStatus("Not-Confirmed, Under Waiting");
		}
		
		ticket.upsertTicket(tinf);		
		
		return new ResponseEntity<TicketInfo>(tinf, HttpStatus.CREATED);
	}
	
	
	
	
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<String> deleteTicket(@PathVariable("id") Integer id){
		
		String deleteTicket = ticket.deleteTicket(id);	
		
		return new ResponseEntity<String>(deleteTicket, HttpStatus.OK);
	}
	
	
	
	@PutMapping(value = "/bookTicket")			
	public ResponseEntity<String> updateTicket(@RequestBody TicketInfo tinf){
		
		String upsertTicket = ticket.upsertTicket(tinf);		
		
		return new ResponseEntity<String>(upsertTicket, HttpStatus.CREATED);
	}
	
}
