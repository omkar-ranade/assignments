package com.springremotingclient.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Employee {
	private String empId;
	private String empFirstName;
	private String empLastName;
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date dob;
	//private String strDateFormat;
	private String city;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpFirstName() {
		return empFirstName;
	}

	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}

	public String getEmpLastName() {
		return empLastName;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/*public String getStrDateFormat() {
		return strDateFormat;
	}

	public void setStrDateFormat(String strDateFormat) {
		this.strDateFormat = strDateFormat;
	}
*/
}
