package com.capg.ApprovalAndRejectionOfUserAppointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capg.ApprovalAndRejectionOfUserAppointment.entity.Test;

@Repository("testRepository")
public interface ITestRepository extends JpaRepository<Test,String>{

	/*@Query("com.sunsoft.ApprovalAndRejectionOfUserAppointment.entity.AppointTestJoin("
			+ "t.testId, t.testName, t.centerId, t.appointment.appointmentId, t.appointment.dateTime) "
			+ "from Test t, Appointment a where t.appointment = a")*/
	//public List<AppointTestJoin> join();
	@Query("select t from Test t where t.testId=?1")
	public List<Test> getCenterById(String id);
}
