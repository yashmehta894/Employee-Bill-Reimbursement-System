package com.wissen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wissen.model.BillStatus;
import com.wissen.model.Report;
import com.wissen.repository.ReportRepository;

@Service
public class PayrollService implements Payroll {

	@Autowired
	ReportRepository reportRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wissen.service.Payroll#getAllReports(com.wissen.model.BillStatus)
	 */
	@Override
	public List<Report> getAllReports(BillStatus status) {
		List<Report> reports = reportRepo.findByStatus(status);
		return reports;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wissen.service.Payroll#update(com.wissen.model.Report)
	 */
	@Override
	public Report update(Report report) {
		return reportRepo.save(report);

	}

}
