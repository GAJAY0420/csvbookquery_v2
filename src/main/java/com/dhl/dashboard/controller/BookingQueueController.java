package com.dhl.dashboard.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhl.dashboard.dto.BookingQueueDto;

@RestController
@CrossOrigin
public class BookingQueueController {
	
	@PostMapping("/greeting")
	@RequestMapping
	public String getBookingData( BookingQueueDto bookingQueueDto, Model model ) {
		return  "Ping Success !!";
	}

}
