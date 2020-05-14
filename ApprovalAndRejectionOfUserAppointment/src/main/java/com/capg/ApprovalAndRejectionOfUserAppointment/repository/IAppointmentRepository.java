package com.capg.ApprovalAndRejectionOfUserAppointment.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capg.ApprovalAndRejectionOfUserAppointment.entity.Appointment;

@Repository("appointmentRepository")
public interface IAppointmentRepository extends JpaRepository<Appointment,BigInteger>{
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery =true,value="update APPOINTMENT a set a.status='rejected' "
			+ "where a.date_Time<current_timestamp and a.status='pending'")
	public void automaticallyReject();
	
	
	@SuppressWarnings("rawtypes")
	@Query("SELECT a.appointmentId,a.dateTime,a.status, t.testId,t.testName,"
			+ "u.userName,u.userId,u.emailId,u.contactNo,u.userRole,c.centerId,"
			+ "c.centerName,c.address,c.contactNo FROM Appointment a, Test t,User u "
			+ ",DiagnosticCenter c WHERE a.testId = t.testId and a.userId=u.userId"
			+ " and a.centerId=c.centerId")
	public List joinTable();
	
	@Query("select a from Appointment a where a.appointmentId=?1")
	public List<Appointment> getAppointmentById(BigInteger id);
	
	
	@SuppressWarnings("rawtypes")
	@Query("SELECT a.appointmentId,a.dateTime,a.status, t.testId,t.testName,"
			+ "u.userName,u.userId,u.emailId,u.contactNo,u.userRole,c.centerId,"
			+ "c.centerName,c.address,c.contactNo FROM Appointment a, Test t,User u "
			+ ",DiagnosticCenter c WHERE a.testId = t.testId and a.userId=u.userId"
			+ " and a.centerId=c.centerId and u.userId =:uId")
	public List getAppointmentDetailsByUserId(@Param("uId") String id);
	
	@SuppressWarnings("rawtypes")
	@Query("SELECT a.appointmentId,a.dateTime,a.status, t.testId,t.testName,"
			+ "u.userName,u.userId,u.emailId,u.contactNo,u.userRole,c.centerId,"
			+ "c.centerName,c.address,c.contactNo FROM Appointment a, Test t,User u "
			+ ",DiagnosticCenter c WHERE a.testId = t.testId and a.userId=u.userId"
			+ " and a.centerId=c.centerId and a.appointmentId =:appId")
	public List getAppointmentDetails(@Param("appId") BigInteger id);
	
	@SuppressWarnings("rawtypes")
	@Query("SELECT a.appointmentId,a.dateTime,a.status, t.testId,t.testName,"
			+ "u.userName,u.userId,u.emailId,u.contactNo,u.userRole,c.centerId,"
			+ "c.centerName,c.address,c.contactNo FROM Appointment a, Test t,User u "
			+ ",DiagnosticCenter c WHERE a.testId = t.testId and a.userId=u.userId"
			+ " and a.centerId=c.centerId and a.status =:status")
	List findAppointmentByStatus(@Param("status") String status);
	
	@Transactional
	@Modifying
    @Query(nativeQuery =true, value="Update Appointment a SET a.status='approved' WHERE "
    		+ "a.status='pending' and a.appointment_Id =:appId")
	public void approveAppointment(@Param("appId") String appId);
	
	@Transactional
	@Modifying
    @Query(nativeQuery =true, value="Update Appointment a SET a.status='rejected' WHERE"
    		+ " a.appointment_Id =:appId")
	public void disapproveAppointment(@Param("appId") String appId);
	
	
	@Query("SELECT a.appointmentId,a.dateTime,a.status, t.testId,t.testName,"
			+ "u.userName,u.userId,u.emailId,u.contactNo,u.userRole FROM Appointment "
			+ "a,Test t,User u WHERE  a.testId = t.testId and"
			+ " a.userId=u.userId and a.status='pending' and a.centerId =:id")
	List getCenterById(@Param("id") String id);
}