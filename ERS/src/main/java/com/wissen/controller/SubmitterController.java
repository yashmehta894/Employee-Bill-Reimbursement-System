package com.wissen.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.ErrorHandler;
import com.wissen.model.Bill;
import com.wissen.model.Costcenter;
import com.wissen.model.Employee;
import com.wissen.model.InputReportRequestBody;
import com.wissen.model.Report;
import com.wissen.model.ResubmitReportRequesBody;
import com.wissen.service.SubmitterImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/submitter")
public class SubmitterController {

	@Autowired
	public SubmitterImpl submitterImpl;

	// @ExceptionHandler(Exception.class)
	public void handleException(HttpServletResponse response) throws Exception {

		ErrorHandler errorHandler = new ErrorHandler();
		errorHandler.setMessage("Not Found");
		errorHandler.setStatusCode("404");
		response.setStatus(404);
		response.setHeader("Message", "Requested Resoiurce not found");
		response.sendError(404, "Requested resource not found");
		// return errorHandler;
	}

	@CrossOrigin(origins = "*")

	@ResponseBody
	@RequestMapping(value = "/report", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String addReport(@RequestBody InputReportRequestBody request) throws Exception {
		// System.out.println(request.toString());
		return submitterImpl.addReport(request);
	}

	@RequestMapping(value = "/bills", method = RequestMethod.GET)
	@ResponseBody
	public List<Bill> getBills(@RequestParam("reportId") int reportId) {
		return submitterImpl.getBills(reportId);
	}

	@ResponseBody
	@RequestMapping(value = "/costCenter", method = RequestMethod.GET)
	public List<Costcenter> getCostCenters() {
		// System.out.println(cost);
		return submitterImpl.getCostCenters();
	}

	@ResponseBody
	@RequestMapping(value = "/approver", method = RequestMethod.GET)
	public List<Employee> getApprovers(@RequestParam("costcenterId") int costcenterId,
			@RequestParam("level") int level) {
		return submitterImpl.getApprovers(costcenterId, level);
	}

	@RequestMapping(value = "/report", method = RequestMethod.PUT, consumes = "application/json")
	public void resubmit(@RequestBody ResubmitReportRequesBody reportInput) {
		submitterImpl.resubmit(reportInput);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wissen.brs.service.Submitter#getReports(int)
	 */

	@ResponseBody
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public List<Report> getReports(@RequestParam("empId") int empId) {

		return submitterImpl.getReports(empId);
	}

	/*
	 * Return the remaining amount in that quarter Takes input the employeeId finds
	 * the sum of all reports of that employee in that quarter subtract from total
	 * limit and return
	 */

	/* Have to check this query */
	@ResponseBody
	@RequestMapping(value = "/getAmt", method = RequestMethod.GET)
	public double getRemainingAmount(@RequestParam("empId") int empId, @RequestParam("date") String date)
			throws ParseException {

		return submitterImpl.getRemainingAmount(empId, date);
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

	public Map<String, Double> getBillTypeLimit(int empId, String date) {

		return null;

	}

	/* Check this function */
	@ResponseBody
	@RequestMapping(value = "/getTotalAmt", method = RequestMethod.GET)
	public double getTotalReimbursedAmount(@RequestParam("empId") int empId, @RequestParam("date") String date)
			throws ParseException {
		return submitterImpl.getTotalReimbursedAmount(empId, date);

	}

	@ResponseBody
	@RequestMapping(value = "/getMgrName", method = RequestMethod.GET)
	public Employee getMgrName(@RequestParam("mgrId") int mgrId) throws ParseException {
		return submitterImpl.getMgrName(mgrId);

	}

}
