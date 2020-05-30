package com.dhl.dashboard.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BookingQueueDto {
	private String bookingNo;
	private String fromDate;
	private String toDate;
	private String country;
	private String globalRefNo;
	private Object pickupDate;
	private Integer bookRefNo;
	private String latestEvent;
	private String pickupStation;
	private Integer accountNo;
	private String userName;
	private String genApplication;
	private String dbSource;

	public String getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGlobalRefNo() {
		return globalRefNo;
	}

	public void setGlobalRefNo(String globalRefNo) {
		this.globalRefNo = globalRefNo;
	}

	public Object getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Object pickupDate) {
		this.pickupDate = pickupDate;
	}

	public Integer getBookRefNo() {
		return bookRefNo;
	}

	public void setBookRefNo(Integer bookRefNo) {
		this.bookRefNo = bookRefNo;
	}

	public String getLatestEvent() {
		return latestEvent;
	}

	public void setLatestEvent(String latestEvent) {
		this.latestEvent = latestEvent;
	}

	public String getPickupStation() {
		return pickupStation;
	}

	public void setPickupStation(String pickupStation) {
		this.pickupStation = pickupStation;
	}

	public Integer getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGenApplication() {
		return genApplication;
	}

	public void setGenApplication(String genApplication) {
		this.genApplication = genApplication;
	}

	public String getDbSource() {
		return dbSource;
	}

	public void setDbSource(String dbSource) {
		this.dbSource = dbSource;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BookingQueueDto [bookingNo=").append(bookingNo).append(", fromDate=").append(fromDate)
				.append(", toDate=").append(toDate).append(", country=").append(country).append(", globalRefNo=")
				.append(globalRefNo).append(", pickupDate=").append(pickupDate).append(", bookRefNo=").append(bookRefNo)
				.append(", latestEvent=").append(latestEvent).append(", pickupStation=").append(pickupStation)
				.append(", accountNo=").append(accountNo).append(", userName=").append(userName)
				.append(", genApplication=").append(genApplication).append("]");
		return builder.toString();
	}
}
