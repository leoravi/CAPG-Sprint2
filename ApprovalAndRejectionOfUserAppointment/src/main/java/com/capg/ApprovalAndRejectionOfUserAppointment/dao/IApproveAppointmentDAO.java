package com.capg.ApprovalAndRejectionOfUserAppointment.dao;

import java.math.BigInteger;
import java.util.List;

import com.capg.ApprovalAndRejectionOfUserAppointment.entity.Appointment;
import com.capg.ApprovalAndRejectionOfUserAppointment.entity.DiagnosticCenter;
import com.capg.ApprovalAndRejectionOfUserAppointment.entity.Test;
import com.capg.ApprovalAndRejectionOfUserAppointment.entity.User;

public interface IApproveAppointmentDAO {

	public List<Appointment> getRejectedAppointmentList(String status);
	public List<Appointment> getPendingAppointmentList(String status);
	public List<Appointment> getApprovedAppointmentList(String status);
	
	@SuppressWarnings("rawtypes")
	public List getAppointmentDetails(BigInteger id);
	
	public List<Appointment> getAppointment();
	public List<Appointment> getAppointmentById(BigInteger id);
	
	public void approveAppointment(String appId);
	public void disApproveAppointment(String appId);
	
	public List<User> getUser();
	public List<User> getUserById(String id);

	public List<User> findUsersWithGmailAddress();
	
	@SuppressWarnings("rawtypes")
	public List join();//allAppointmentdetails
	
	public List<Test> getTestById(String id);
	public List<Test> getTest();
	
	public List<DiagnosticCenter> getDiagnosticCenter();
	public List getDiagnosticCenterById(String id);
	@SuppressWarnings("rawtypes")
	List getAppointmentDetailsByUserId(String id);
	
}