package com.capg.ApprovalAndRejectionOfUserAppointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capg.ApprovalAndRejectionOfUserAppointment.entity.DiagnosticCenter;


@Repository("diagnosticCenterRepository")
public interface IDiagnosticCenterRepository extends JpaRepository<DiagnosticCenter,String>{

	/*@Query("select c from DiagnosticCenter c where c.centerId=?1")
	public List<DiagnosticCenter> getCenterById(String id);*/
}
