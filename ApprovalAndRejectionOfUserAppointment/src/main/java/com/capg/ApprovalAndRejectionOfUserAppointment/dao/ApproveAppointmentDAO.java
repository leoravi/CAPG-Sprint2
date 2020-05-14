package com.capg.ApprovalAndRejectionOfUserAppointment.dao;

import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.capg.ApprovalAndRejectionOfUserAppointment.entity.Appointment;
import com.capg.ApprovalAndRejectionOfUserAppointment.entity.DiagnosticCenter;
import com.capg.ApprovalAndRejectionOfUserAppointment.entity.Test;
import com.capg.ApprovalAndRejectionOfUserAppointment.entity.User;
import com.capg.ApprovalAndRejectionOfUserAppointment.repository.IAppointmentRepository;
import com.capg.ApprovalAndRejectionOfUserAppointment.repository.IDiagnosticCenterRepository;
import com.capg.ApprovalAndRejectionOfUserAppointment.repository.ITestRepository;
import com.capg.ApprovalAndRejectionOfUserAppointment.repository.IUserRepository;

@Configuration
public class ApproveAppointmentDAO implements IApproveAppointmentDAO{

	@Autowired
	IAppointmentRepository appointmentRepository;
	@Autowired
	ITestRepository testRepository;
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	IDiagnosticCenterRepository diagnosticCenterRepository;
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getApprovedAppointmentList(String status) {
		appointmentRepository.automaticallyReject();
		return appointmentRepository.findAppointmentByStatus(status);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getRejectedAppointmentList(String status) {
		appointmentRepository.automaticallyReject();
		return appointmentRepository.findAppointmentByStatus(status);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getPendingAppointmentList(String status) {
		appointmentRepository.automaticallyReject();
		return appointmentRepository.findAppointmentByStatus(status);
	}

	

	@Override
	public void approveAppointment(String appId) {
		appointmentRepository.approveAppointment(appId);
		
	}
	@Override
	public void disApproveAppointment(String appId) {
		appointmentRepository.disapproveAppointment(appId);
	}

	

	@SuppressWarnings("rawtypes")
	@Override
	public List join(){
		appointmentRepository.automaticallyReject();
		return appointmentRepository.joinTable();

	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List getAppointmentDetails(BigInteger id){
		appointmentRepository.automaticallyReject();
		return appointmentRepository.getAppointmentDetails(id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getAppointmentDetailsByUserId(String id){
		appointmentRepository.automaticallyReject();
		return appointmentRepository.getAppointmentDetailsByUserId(id);
	}
	
	
	@Override
	public List<Appointment> getAppointment() {
		appointmentRepository.automaticallyReject();
		return appointmentRepository.findAll();	
	}
	@Override
	public List<Appointment> getAppointmentById(BigInteger id) {
		appointmentRepository.automaticallyReject();
		return appointmentRepository.getAppointmentById(id);
	}


	@Override
	public List<User> findUsersWithGmailAddress(){
		return userRepository.findUsersWithGmailAddress();
	}
	@Override
    public List<User> getUser() {
        return userRepository.findAll();
    }
	@Override
	public List<User> getUserById(String id) {
		return userRepository.getUserById(id);
	}


	@Override
	public List<Test> getTestById(String id) {
		return testRepository.getCenterById(id);
	}
	@Override
	public List<Test> getTest() {
		return testRepository.findAll();
	}


	@Override
	public List<DiagnosticCenter> getDiagnosticCenter() {
		return diagnosticCenterRepository.findAll();
	}
	@Override
	public List<Appointment> getDiagnosticCenterById(String id) {
		return appointmentRepository.getCenterById(id);
	}

}
