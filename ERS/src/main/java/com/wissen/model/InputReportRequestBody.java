package com.wissen.model;

import java.util.Date;
import java.util.List;

public class InputReportRequestBody {

	@Override
	public String toString() {
		return "InputReportRequestBody [employeeId=" + employeeId + ", bills=" + bills + ", date=" + date
				+ ", approvedBy=" + approvedBy + ", comment=" + comment + "]";
	}

	int employeeId;
	List<Bill> bills;
	Date date;
	int approvedBy;
	String comment;
	//List<Byte[]> images;
	int costcenter;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(int approvedBy) {
		this.approvedBy = approvedBy;
	}

	public int getCostcenter() {
		return costcenter;
	}

	public void setCostcenter(int costcenter) {
		this.costcenter = costcenter;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
