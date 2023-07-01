package com.nitesh.springboot.service;

import java.util.List;
import java.util.Optional;

import com.nitesh.springboot.binding.TicketInfo;

public interface TicketService {
	
	public List<TicketInfo> getAllTickets();
	
	public String upsertTicket(TicketInfo tinf);
	
	public String deleteTicket(Integer id);
	
	public Optional<TicketInfo> getTicketByid(Integer id);

}
