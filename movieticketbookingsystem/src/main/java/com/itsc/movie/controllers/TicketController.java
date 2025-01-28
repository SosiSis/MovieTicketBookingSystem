package com.itsc.movie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsc.movie.request.TicketRequest;
import com.itsc.movie.response.TicketResponse;
import com.itsc.movie.services.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@PostMapping("/book")
	public ResponseEntity<Object> ticketBooking(@RequestBody TicketRequest ticketRequest) {
		try {
			TicketResponse result = ticketService.ticketBooking(ticketRequest);
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
