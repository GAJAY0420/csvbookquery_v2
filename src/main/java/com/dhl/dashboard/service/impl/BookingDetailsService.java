package com.dhl.dashboard.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.dhl.dashboard.dto.BookingQueueDto;
import com.dhl.dashboard.dto.EventQueueDto;
import com.dhl.dashboard.repository.BookingDetailsRepository;

@Service
public class BookingDetailsService {
	
	@Autowired
	@Qualifier("apemJdbcTemplate")
	private JdbcTemplate apemJdbcTemplate;
	
	@Autowired
	@Qualifier("euJdbcTemplate")
	private JdbcTemplate euJdbcTemplate;
	
	@Autowired
	@Qualifier("amJdbcTemplate")
	private JdbcTemplate amJdbcTemplate;
	
	@Autowired
	private BookingDetailsRepository bookingDetailsRepo;
	
	public List<BookingQueueDto> getBookingDetails(BookingQueueDto bookingQueueDto) {
		List<BookingQueueDto> result = new ArrayList<BookingQueueDto>();
		try {
			JdbcTemplate dataSource = this.apemJdbcTemplate;
			switch (bookingQueueDto.getDbSource()) {
			case "APEM":
				dataSource = apemJdbcTemplate;
				System.out.println("data source is APEM");
				break;
			case "EU":
				dataSource = euJdbcTemplate;
				System.out.println("data source is EU");
				break;
			case "AM":
				dataSource = amJdbcTemplate;
				System.out.println("data source is AM");
				break;	
			default:
				break;
			}
			result = new ArrayList<BookingQueueDto>();
			result = bookingDetailsRepo.getBookingsDetails(dataSource, bookingQueueDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<EventQueueDto> getEventDetails(EventQueueDto eventQueueDto) {
		List<EventQueueDto> result = new ArrayList<EventQueueDto>();
		try {
			JdbcTemplate dataSource = this.apemJdbcTemplate;
			switch (eventQueueDto.getDataSource()) {
			case "APEM":
				dataSource = apemJdbcTemplate;
				break;
			case "EU":
				dataSource = euJdbcTemplate;
				break;
			case "AM":
				dataSource = amJdbcTemplate;
				break;	
			default:
				break;
			}
			
			result = new ArrayList<EventQueueDto>();
			result = bookingDetailsRepo.getEventDetails(dataSource, eventQueueDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
