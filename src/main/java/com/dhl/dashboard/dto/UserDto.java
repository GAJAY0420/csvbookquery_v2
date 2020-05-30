package com.dhl.dashboard.dto;

//@FieldMatch.List({
//    @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
//    @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
//})
public class UserDto {
	private String empID;
	private String firstName;
	private String lastName;
	private String password;
	private String confirmPassword;
	private String email;
	private long mobileNo;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
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

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return String.format(
				"UserDto [empID=%s, firstName=%s, lastName=%s, password=%s, confirmPassword=%s, email=%s, mobileNo=%s]",
				empID, firstName, lastName, password, confirmPassword, email, mobileNo);
	}

}
