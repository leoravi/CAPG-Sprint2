package com.capg.ApprovalAndRejectionOfUserAppointment.controller;

import java.math.BigInteger;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ApprovalAndRejectionOfUserAppointment.entity.Appointment;
import com.capg.ApprovalAndRejectionOfUserAppointment.entity.DiagnosticCenter;
import com.capg.ApprovalAndRejectionOfUserAppointment.entity.Test;
import com.capg.ApprovalAndRejectionOfUserAppointment.entity.User;
import com.capg.ApprovalAndRejectionOfUserAppointment.service.EmailService;
import com.capg.ApprovalAndRejectionOfUserAppointment.service.IApproveAppointmentService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping({"/api"})

@RestController
public class ApproveAppointmentController {

	@Autowired
	IApproveAppointmentService approveAppointmentService;
	
	@GetMapping("/user")
    public List<User> getUser() {
        return approveAppointmentService.getUser();
    }
	@GetMapping("/user/{id}")
    public List<User> getUserById(@PathVariable String id) {
        return approveAppointmentService.getUserById(id);
    }
	@GetMapping("/test")
    public List<Test> getTest() {
        return approveAppointmentService.getTest();
    }
	@GetMapping("/test/{id}")
    public List<Test> getTestById(@PathVariable String id) {
        return approveAppointmentService.getTestById(id);
    }
	@RequestMapping("/appointment")
    public List<Appointment> getAppointment() {
        return approveAppointmentService.getAppointment();
    }
	@RequestMapping("/appointment/{id}")
    public List<Appointment> getAppointmentById(@PathVariable BigInteger id) {
        return approveAppointmentService.getAppointmentById(id);
    }
	
	//gets centers
	@RequestMapping("/center")
    public List<DiagnosticCenter> getDiagnosticCenter() {
        return approveAppointmentService.getDiagnosticCenter();
    }
	//get appointments of specific center
	@RequestMapping("/center/{id}")
    public List getDiagnosticCenterById(@PathVariable String id) {
        return approveAppointmentService.getDiagnosticCenterById(id);
    }
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/list")
    public List<Appointment> getAppointmentDetails() throws ResourceNotFoundException{
		List<Appointment> appointmentList= approveAppointmentService.join();
		if(appointmentList.size() == 0)
		{
			throw new ResourceNotFoundException("No Records Available ");
		}
		return appointmentList;
	}
	
	@RequestMapping("/userWithEmail")
    public List<User> getQuery() {
        return approveAppointmentService.findUsersWithGmailAddress();
    }
	
	@RequestMapping("/approveAppointment/{appId}")
	public String approveAppointment(@PathVariable String appId){
		approveAppointmentService.approveAppointment(appId);
		return "Appointment approved";
	}
	@RequestMapping("/rejectAppointment/{appId}")
	public String rejectAppointment(@PathVariable String appId){
		approveAppointmentService.disApproveAppointment(appId);
		return "Appointment rejected";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/allAppointment")
	public List getAllAppointment()throws ResourceNotFoundException{
		List allAppointmentList= approveAppointmentService.join();	
		if(allAppointmentList.size() == 0)
		{
			throw new ResourceNotFoundException("No Records Available ");
		}
		return allAppointmentList;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/allAppointment/{id}")
	public List<Appointment> getAppointmentDetailsById(@PathVariable BigInteger id)
			throws ResourceNotFoundException{
		List<Appointment> appointmentList=approveAppointmentService.getAppointmentDetails(id);		
		if(appointmentList.size() == 0)
		{
			throw new ResourceNotFoundException("No Records Available ");
		}
		return appointmentList;
	}	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/allAppointment/user/{id}")
	public List<Appointment> getAppointmentDetailsByUserId(@PathVariable String id)
			throws ResourceNotFoundException{
		List<Appointment> appointmentList= approveAppointmentService.getAppointmentDetailsByUserId(id);		
		if(appointmentList.size() == 0)
		{
			throw new ResourceNotFoundException("No Records Available ");
		}
		return appointmentList;
	}	
	
	@RequestMapping("/approved")
	public List<Appointment> getApproved()throws ResourceNotFoundException{
		List<Appointment> approvedList=approveAppointmentService.getApprovedAppointmentList();
		if(approvedList.size() == 0)
		{
			throw new ResourceNotFoundException("No Records Available ");
		}
		return approvedList;
	}
	
	@RequestMapping("/rejected")
	public List<Appointment> getRejected()throws ResourceNotFoundException{
		List<Appointment> rejectedList=approveAppointmentService.getRejectedAppointmentList();
		if(rejectedList.size() == 0)
		{
			throw new ResourceNotFoundException("No Records Available ");
		}
		return rejectedList;
	}
	
	@RequestMapping("/pending")
	public List<Appointment> getPending()throws ResourceNotFoundException{
		List<Appointment> pendingList=approveAppointmentService.getPendingAppointmentList();
		if(pendingList.size() == 0)
		{
			throw new ResourceNotFoundException("No Records Available ");
		}
		return pendingList;
	}
	
	@Autowired
    private EmailService emailService;

    @RequestMapping(value = "/sendEmail/{toMail}" , method = RequestMethod.GET)
    public void sendEmail(@PathVariable String toMail){
    	//User uObj=new User();
    	//String toMail=uObj.getEmailId();
    	String subject="Appointment Confirmation";
    	String message="Hey, your appointment has been approved. Please login to"
        		+ " your account and download receipt. Please reach the test center on time."
        		+ "\nWe hope you like our services.\nWe wish you good health.\n Thank you."
        		+ "\n\n\n\n\n\n\nRegards,\nLeon HealthCare Center";
        try {
            emailService.sendMail(toMail, subject, message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}


