package com.dhl.dashboard.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;

import com.dhl.dashboard.dto.BookingQueueDto;
import com.dhl.dashboard.dto.EventQueueDto;

public interface BookingDetailsRepository {
	
	public List<BookingQueueDto> getBookingsDetails(@NonNull JdbcTemplate jdbcTemplate, BookingQueueDto bookingQueueDto);
	
	public List<EventQueueDto> getEventDetails(@NonNull JdbcTemplate jdbcTemplate, EventQueueDto eventQueueDto);

}
