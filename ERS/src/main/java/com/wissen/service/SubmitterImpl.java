package com.wissen.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.UtilityFuctions;
import com.wissen.model.Bill;
import com.wissen.model.BillStatus;
import com.wissen.model.Costcenter;
import com.wissen.model.Employee;
import com.wissen.model.InputReportRequestBody;
import com.wissen.model.Report;
import com.wissen.model.ResubmitReportRequesBody;
import com.wissen.repository.BillRepository;
import com.wissen.repository.CostCenterRepository;
import com.wissen.repository.EmployeeRepository;
import com.wissen.repository.ReportRepository;

@Controller
@CrossOrigin(origins = "*")
public class SubmitterImpl implements Submitter {

	@Autowired
	ReportRepository reportRepository;

	@Autowired
	BillRepository billRepository;

	@Autowired
	CostCenterRepository costcenterRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wissen.brs.service.Submitter#addReport(com.wissen.brs.service.
	 * InputReportRequestBody)
	 */
	// @Override
	// public String addReport(@RequestBody InputReportRequestBody request) throws
	// Exception {
	// Report report = new Report();
	//
	// Date date = request.getDate();
	//
	// report.setEmployee(employeeRepository.findOne(request.getEmployeeId()));
	// report.setBills(request.getBills());
	// report.setStatus(BillStatus.SUBMITTED);
	// report.setApprovedBy(request.getApprovedBy());
	// report.setComment(request.getComment());
	// report.setDate(date);
	// report.setQuarter(UtilityFuctions.getQuarter(date.getMonth()));
	// report.setTotalAmount(UtilityFuctions.calculateTotalAmt(request.getBills()));
	// reportRepository.save(report);
	// System.out.println("saved");
	// return "SUCCESS";
	// }

	@Override
	public String addReport(@RequestBody InputReportRequestBody request) throws Exception {

		System.out.println(request);

		Report report = new Report();

		Date date = request.getDate();
		System.out.println("approved by" + request.getApprovedBy());
		System.out.println(employeeRepository.findOne(request.getApprovedBy()).toString());
		report.setEmployee(employeeRepository.findOne(request.getEmployeeId()));

		report.setBills(request.getBills());
		List<Bill> bills = report.getBills();

		report.setBills(bills);
		report.setStatus(BillStatus.SUBMITTED);
		System.out.println(employeeRepository.findOne(request.getApprovedBy()).getName());
		report.setApprovedBy(employeeRepository.findOne(request.getApprovedBy()).getName());
		// report.setApprovedBy(request.getApprovedBy());
		report.setComment(request.getComment());
		report.setDate(date);
		Costcenter costcenter = costcenterRepository.findOne(request.getCostcenter());
		report.setCostCenter(costcenter.getCenterName());
		report.setQuarter(UtilityFuctions.getQuarter(date.getMonth()));
		report.setTotalAmount(UtilityFuctions.calculateTotalAmt(request.getBills()));
		report.setApprovedById(request.getApprovedBy());
		reportRepository.save(report);
		System.out.println("saved");
		return "SUCCESS";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wissen.brs.service.Submitter#getBills(int)
	 */
	@Override
	public List<Bill> getBills(@RequestParam("reportId") int reportId) {
		System.out.println(reportId);
		Report report = reportRepository.findByReportId(reportId);
		System.out.println(report);
		System.out.println(report.getBills().get(0).getBillDate());
		return report.getBills();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wissen.brs.service.Submitter#getCostCenters()
	 */
	@Override
	public List<Costcenter> getCostCenters() {
		// System.out.println(cost);
		return costcenterRepository.getCostCenters();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wissen.brs.service.Submitter#getApprovers(int, int)
	 */
	@Override
	public List<Employee> getApprovers(@RequestParam("costcenterId") int costcenterId,
			@RequestParam("level") int level) {
		return employeeRepository.findApproverByCenter(costcenterId, level);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wissen.brs.service.Submitter#resubmit(com.wissen.brs.service.
	 * ResubmitReportRequesBody)
	 */
	// /*@Override
	// public void resubmit(@RequestBody ResubmitReportRequesBody reportInput) {
	//
	// Report report = reportRepository.findOne(reportInput.getReportId());
	// // report.setReportId(reportInput.getReportId());
	// // System.out.println(reportInput.getBills());
	//
	// // System.out.println(reportInput.getBills());
	// report.setBills(reportInput.getBills());
	// report.setTotalAmount(UtilityFuctions.calculateTotalAmt(reportInput.getBills()));
	// report.setComment(reportInput.getComment());
	//
	// if (reportInput.getStatus() == BillStatus.APPROVER_REJECTED) {
	// report.setStatus(BillStatus.SUBMITTED);
	// } else {
	// report.setStatus(BillStatus.APPROVED);
	// }
	//
	// reportRepository.save(report);
	//
	// }*/

	@Override
	public void resubmit(@RequestBody ResubmitReportRequesBody reportInput) {

		Report report = reportRepository.findOne(reportInput.getReportId());
		System.out.println("Im in resubmit");
		System.out.println(reportInput);
		// report.setReportId(reportInput.getReportId());
		// System.out.println(reportInput.getBills());

		// System.out.println(reportInput.getBills());
		report.setBills(reportInput.getBills());
		report.setTotalAmount(UtilityFuctions.calculateTotalAmt(reportInput.getBills()));
		// report.setComment(reportInput.getComment());

		if (reportInput.getStatus() == BillStatus.APPROVER_REJECTED) {
			report.setStatus(BillStatus.SUBMITTED);
		} else {
			report.setStatus(BillStatus.APPROVED);
		}

		reportRepository.save(report);
		// billRepository.save(reportInput.getBills());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wissen.brs.service.Submitter#getReports(int)
	 */

	@Override
	public List<Report> getReports(@RequestParam("empId") int empId) {

		return reportRepository.findByemployee(employeeRepository.findOne(empId));
	}

	/*
	 * Return the remaining amount in that quarter Takes input the employeeId finds
	 * the sum of all reports of that employee in that quarter subtract from total
	 * limit and return
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wissen.brs.service.Submitter#getRemainingAmount(int,
	 * java.lang.String)
	 */
	@Override
	public double getRemainingAmount(@RequestParam("empId") int empId, @RequestParam("date") String date)
			throws ParseException {

		Employee employee = employeeRepository.findOne(empId);
		Date dateInFormat = new SimpleDateFormat("yyyy-MM-dd").parse(date);// date-yyyy-MM-dd
		//Date dateInFormat = date;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date datenew = formatter.parse(date);
		System.out.println(formatter.format(datenew));
		
		System.out.println(dateInFormat);
		System.out.println(dateInFormat.getMonth());
		System.out.println(UtilityFuctions.getQuarter(datenew.getMonth()));

		List<Report> reports = reportRepository.sumOfReports(empId,
				UtilityFuctions.getQuarter(datenew.getMonth()));
		double totalAmountReport = 0.0;
		for (Report report : reports) {
			totalAmountReport += report.getTotalAmount();
		}
		System.out.println(employee.getBillLimit() - totalAmountReport);

		return employee.getBillLimit() - totalAmountReport;
	}

	/*
	 * 
	 * 
	 * for each type of bills{
	 * 
	 * 
	 * get the total of all the bills in that quarter from bill repository amount =
	 * subtract from limit add in the map bill type and amount
	 * 
	 * 
	 * } return map
	 * 
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wissen.brs.service.Submitter#getBillTypeLimit(int, java.util.Date)
	 */
	@Override
	public Map<String, Double> getBillTypeLimit(int empId, String date) {

		return null;

	}

	@Override
	public double getTotalReimbursedAmount(@RequestParam("empId") int empId, @RequestParam("date") String date)
			throws ParseException {
		Employee employee = employeeRepository.findOne(empId);
		Date dateInFormat = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date datenew = formatter.parse(date);
		System.out.println(formatter.format(datenew));
		List<Report> reports = reportRepository.sumOfReports(empId,
				UtilityFuctions.getQuarter(datenew.getMonth()));
		double totalAmountReport = 0.0;
		for (Report report : reports) {
			totalAmountReport += report.getTotalAmount();
		}
		return totalAmountReport;

	}

	@Override
	public Employee getMgrName(@RequestParam("mgrId") int mgrId) throws ParseException {
		Employee employee = employeeRepository.findOne(mgrId);
		return employee;

	}

}
