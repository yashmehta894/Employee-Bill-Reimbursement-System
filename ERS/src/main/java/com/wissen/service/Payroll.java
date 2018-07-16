package com.wissen.service;

import java.util.List;

import com.wissen.model.BillStatus;
import com.wissen.model.Report;

public interface Payroll {

	List<Report> getAllReports(BillStatus status);

	Report update(Report report);

}