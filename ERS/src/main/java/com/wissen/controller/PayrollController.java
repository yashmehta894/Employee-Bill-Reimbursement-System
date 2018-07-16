package com.wissen.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.ErrorHandler;
import com.wissen.model.BillStatus;
import com.wissen.model.Report;
import com.wissen.service.Payroll;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/payroll")
public class PayrollController {

	@Autowired
	Payroll payrollService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wissen.controller.Payroll#getAllReports(com.wissen.model.BillStatus)
	 */

	@ExceptionHandler(Exception.class)
	public void handleException(HttpServletResponse response) throws Exception {

		ErrorHandler errorHandler = new ErrorHandler();
		errorHandler.setMessage("Not Found");
		errorHandler.setStatusCode("404");
		response.setStatus(404);
		response.setHeader("Message", "Requested Resoiurce not found");
		response.sendError(404, "Requested resource not found");
		// return errorHandler;
	}

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public List<Report> getAllReports(@RequestParam("status") BillStatus status) {

		// System.out.println("In payroll report method");
		List<Report> reports = payrollService.getAllReports(status);
		return reports;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wissen.controller.Payroll#update(com.wissen.model.Report)
	 */
	@RequestMapping(value = "/report", method = RequestMethod.PUT, consumes = "application/json")
	public Report update(@RequestBody Report report) {
		return payrollService.update(report);

	}

}
