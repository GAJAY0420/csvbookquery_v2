package com.dhl.dashboard.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "csv_user")
@DynamicInsert
public class CsvUserModel {

	@Column(unique = true, nullable = false)
	@Id
	private String empId;
	@Column(nullable = false)
	private String firstName;
	private String lastName;
	private Long mobileNo;
	private String email;
	@Column(unique = true, nullable = false)
	private String password;
	@Column(nullable = false)
	private String confirmPassword;

	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "CsvUserModel [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", mobileNo="
				+ mobileNo + ", email=" + email + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
