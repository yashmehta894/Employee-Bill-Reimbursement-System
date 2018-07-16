package com.wissen.controller;

import com.wissen.ErrorHandler;
import com.wissen.model.*;
import com.wissen.service.Approver;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/approver")
@CrossOrigin(origins = "*")
public class ApproverController {

	@Autowired
	Approver approverService;

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

	@RequestMapping(value = "/report/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public List<Report> getReports(@PathVariable("id") int id) {
		System.out.println("In get reports");
		BillStatus billStatus = BillStatus.SUBMITTED;
		return approverService.getReports(id, billStatus);
	}

	@RequestMapping(value = "/report")
	@CrossOrigin(origins = "*")
	public List<Report> getReports(@RequestParam("level") int level, @RequestParam("centerId") int centerId) {
		// System.out.println(level + " " + centerId + " " + billStatus);
		return approverService.getReports(level, centerId);
	}

	@RequestMapping(value = "/report", method = RequestMethod.PUT, consumes = "application/json")
	@CrossOrigin(origins = "*")
	public Report update(@RequestBody Report report) {
		System.out.println("In update report");
		return approverService.update(report);
	}

	@RequestMapping(value = "/bills", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public List<Bill> getBills(@RequestParam("reportId") int reportId) {
		return approverService.getBills(reportId);
	}
}
