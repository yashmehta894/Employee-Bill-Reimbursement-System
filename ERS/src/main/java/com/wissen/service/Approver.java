package com.wissen.service;

import java.util.List;

import com.wissen.model.Bill;
import com.wissen.model.BillStatus;
import com.wissen.model.Report;

public interface Approver {

	List<Report> getReports(int id, BillStatus billStatus);

	List<Report> getReports(int level, int centerId);

	Report update(Report report);

	List<Bill> getBills(int reportId);

}