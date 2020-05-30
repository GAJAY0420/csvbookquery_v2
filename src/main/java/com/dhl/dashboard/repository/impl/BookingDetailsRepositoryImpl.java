package com.dhl.dashboard.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.dhl.dashboard.dto.BookingQueueDto;
import com.dhl.dashboard.dto.EventQueueDto;
import com.dhl.dashboard.repository.BookingDetailsRepository;

@Component
public class BookingDetailsRepositoryImpl implements BookingDetailsRepository {

	@Value("${booking_query}")
	private String bookingSql;

	@Value("${events_query}")
	private String eventsSql;

	public List<BookingQueueDto> getBookingsDetails(@NonNull JdbcTemplate jdbcTemplate,
			BookingQueueDto bookingQueueDto) {
		String bookingNo = bookingQueueDto.getBookingNo();
		StringBuilder query = new StringBuilder(bookingSql);
		if (StringUtils.isNumeric(bookingNo) && bookingNo.length() <= 6) {
			bookingNo = bookingNo.replaceFirst("^0+(?!$)", "");
			query.append(" where ( book_refnum = " + bookingNo + " or external_ref = " + bookingNo + " )");
		} else {
			query.append(" where  global_refnum = '" + bookingNo.trim() + "' ");
		}

		query.append(" and pickup_country ='" + bookingQueueDto.getCountry()
				+ "' and to_date(to_char(pickup_date,'%d-%m-%Y'),'%d-%m-%Y') >= to_date('"
				+ bookingQueueDto.getFromDate().trim()
				+ "','%d-%m-%Y') and to_date(to_char(pickup_date,'%d-%m-%Y'),'%d-%m-%Y') <= to_date('"
				+ bookingQueueDto.getToDate() + "','%d-%m-%Y')");

		return jdbcTemplate.query(query.toString(), new BookingDetailsRowMapper());
	}

	private class BookingDetailsRowMapper implements RowMapper<BookingQueueDto> {

		@Override
		public BookingQueueDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			BookingQueueDto result = new BookingQueueDto();
			result.setGlobalRefNo(rs.getString("global_refnum"));
			result.setPickupDate(rs.getDate("pickupDate"));
			result.setBookRefNo(rs.getInt("book_refnum"));
			result.setLatestEvent(rs.getString("latest_event"));
			result.setPickupStation(rs.getString("pickup_station"));
			result.setAccountNo(rs.getInt("account_number"));
			result.setUserName(rs.getString("user_name"));
			result.setGenApplication(rs.getString("gen_application"));
			return result;
		}

	}

	public List<EventQueueDto> getEventDetails(@NonNull JdbcTemplate jdbcTemplate, EventQueueDto eventQueueDto) {
		String query = eventsSql.concat(" where  global_refnum ='" + eventQueueDto.getGlobalRefNo() + "'");
		System.out.println("sql: " + query);
		return jdbcTemplate.query(query, new EventDetailsRowMapper());
	}

	private class EventDetailsRowMapper implements RowMapper<EventQueueDto> {

		@Override
		public EventQueueDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			EventQueueDto result = new EventQueueDto();
			result.setGlobalRefNo(rs.getString("globalRefNum"));
			result.setBookRevision(rs.getInt("book_revision"));
			result.setPickupStation(rs.getString("pickup_station"));
			result.setEventDate(rs.getDate("event_date"));
			result.setEventType(rs.getString("event_type"));
			result.setPickupRefNo(rs.getInt("pickup_refnum"));
			return result;
		}

	}
}
