package com.wissen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.wissen.model.BillStatus;
import com.wissen.model.Employee;
import com.wissen.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

	List<Report> findByApprovedByIdAndStatus(int id, BillStatus billStatus);

	@Query(value = "select * from report where approved_by_id in (select emp_id from employee where level = ?1 and center_id = ?2) and status = 'SUBMITTED' ", nativeQuery = true)
	public List<Report> getReports(int level, int centerId);

	Report findByReportId(int reportId);

	List<Report> findByStatus(BillStatus billStatus);

	@Query(value = "update report set status = ?1 where report_id = ?2", nativeQuery = true)
	public Report updateReport(BillStatus status, String reportId);

	////////////////////

	List<Report> findByemployee(Employee employee);

	@Query(value = "select * from Report where emp_id=?1 and quarter=?2 and( status='PAID' or status='APPROVED')", nativeQuery = true)
	List<Report> sumOfReports(int empId, String quarter);
}
