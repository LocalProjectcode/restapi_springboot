package com.test.api.bean;

public class RequestDO {

	private String firstName;
	private String lastName;
	private String mailId;
	private long cellNo; // optional field
	private String username;
	private String password;
	private String tuSerial;

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

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public long getCellNo() {
		return cellNo;
	}

	public void setCellNo(long cellNo) {
		this.cellNo = cellNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getTuSerial() {
		return tuSerial;
	}

	public void setTuSerial(String tuSerial) {
		this.tuSerial = tuSerial;
	}

	@Override
	public String toString() {
		return "RequestDO [firstName=" + firstName + ",lastName=" + lastName + ",mailId=" + mailId + "username=" + username + ",password=" + password + "]";
	}

}