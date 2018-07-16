package com.wissen.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wissen.model.Bill;
import com.wissen.model.Costcenter;
import com.wissen.model.Employee;
import com.wissen.model.InputReportRequestBody;
import com.wissen.model.Report;
import com.wissen.model.ResubmitReportRequesBody;

public interface Submitter {

	String addReport(InputReportRequestBody request) throws Exception;

	List<Bill> getBills(int reportId);

	List<Costcenter> getCostCenters();

	List<Employee> getApprovers(int costcenterId, int level);

	void resubmit(ResubmitReportRequesBody reportInput);

	List<Report> getReports(int empId);

	double getRemainingAmount(int empId, String date) throws ParseException;

	Map<String, Double> getBillTypeLimit(int empId, String date);

	double getTotalReimbursedAmount(int empId, String date) throws ParseException;

	Employee getMgrName(int mgrId) throws ParseException;

}