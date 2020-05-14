package com.capg.ApprovalAndRejectionOfUserAppointment.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_app")
public class User {

	@Id
	@Column(name="user_Id")
	private String userId;
	//private List<DiagnosticCenter> centerList;
	@Column(name="user_Password")
	private String userPassword;
	@Column(name="user_Name")
	private String userName;
	@Column(name="contact_No")
	private BigInteger contactNo;
	@Column(name="user_Role")
	private String userRole;
	@Column(name="email_Id")
	private String emailId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public BigInteger getContactNo() {
		return contactNo;
	}
	public void setContactNo(BigInteger contactNo) {
		this.contactNo = contactNo;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
}
