package com.dhl.dashboard.dto;

import java.sql.Date;

public class EventQueueDto {

	private String globalRefNo;
	private Integer bookRevision;
	private String pickupStation;
	private Date eventDate;
	private String eventType;
	private Integer pickupRefNo;
	private String country;
	private String dataSource;

	public String getGlobalRefNo() {
		return globalRefNo;
	}

	public void setGlobalRefNo(String globalRefNo) {
		this.globalRefNo = globalRefNo;
	}

	public Integer getBookRevision() {
		return bookRevision;
	}

	public void setBookRevision(Integer bookRevision) {
		this.bookRevision = bookRevision;
	}

	public String getPickupStation() {
		return pickupStation;
	}

	public void setPickupStation(String pickupStation) {
		this.pickupStation = pickupStation;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Integer getPickupRefNo() {
		return pickupRefNo;
	}

	public void setPickupRefNo(Integer pickupRefNo) {
		this.pickupRefNo = pickupRefNo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public String toString() {
		return "EventQueueDto [globalRefNo=" + globalRefNo + ", bookRevision=" + bookRevision + ", pickupStation="
				+ pickupStation + ", eventDate=" + eventDate + ", eventType=" + eventType + ", pickupRefNo="
				+ pickupRefNo + ", country=" + country + ", dataSource=" + dataSource + "]";
	}

}
