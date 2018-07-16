package com.wissen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wissen.model.Bill;
import com.wissen.model.BillStatus;
import com.wissen.model.Report;
import com.wissen.repository.ReportRepository;

@Service
public class ApproverService implements Approver {

	@Autowired
	ReportRepository reportRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wissen.service.Approver#getReports(int)
	 */
	@Override
	public List<Report> getReports(int id, BillStatus billStatus) {
		return reportRepo.findByApprovedByIdAndStatus(id, billStatus);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wissen.service.Approver#getReports(int, int)
	 */
	@Override
	public List<Report> getReports(int level, int centerId) {
		return reportRepo.getReports(level, centerId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wissen.service.Approver#update(com.wissen.model.Report)
	 */
	@Override
	public Report update(Report report) {
		return reportRepo.save(report);// reportRepo.updateReport(status, reportId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wissen.service.Approver#getBills(int)
	 */
	@Override
	public List<Bill> getBills(int reportId) {
		return reportRepo.findByReportId(reportId).getBills();
	}

}
