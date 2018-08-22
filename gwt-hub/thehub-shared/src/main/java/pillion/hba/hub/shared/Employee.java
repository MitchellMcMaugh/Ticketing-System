package pillion.hba.hub.shared;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
	private String employeeName;
	private String employeeCompany;
	private String employeeCity;
	private String employeeEmail;
	private String employeePhone;
	private String employeeManager;
	
	public String getEmployeeName() {
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public String getEmployeeCompany() {
		return employeeCompany;
	}
	
	public void setEmployeeCompany(String employeeCompany) {
		this.employeeCompany = employeeCompany;
	}
	
	public String getEmployeeCity() {
		return employeeCity;
	}
	
	public void setEmployeeCity(String employeeCity) {
		this.employeeCity = employeeCity;
	}
	
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	
	public String getEmployeePhone() {
		return employeePhone;
	}
	
	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}
	
	public String getEmployeeManager() {
		return employeeManager;
	}
	
	public void setEmployeeManager(String employeeManager) {
		this.employeeManager = employeeManager;
	}
}
