package com.wissen.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the report database table.
 * 
 */
@Entity
@NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r")
public class Report implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int reportId;

	private String approvedBy;

	private int approvedById;

	private String comment;

	private double totalAmount;

	private String quarter;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Enumerated(EnumType.STRING)
	private BillStatus status;

	String costCenter;

	// bi-directional many-to-one association to Bill

	@JsonIgnoreProperties("report")
	// @JsonIgnore
	@OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
	private List<Bill> bills;

	// bi-directional many-to-one association to Employee
	@JsonIgnoreProperties({ "reports", "employee", "employees" })
	@ManyToOne
	@JoinColumn(name = "EmpId")
	private Employee employee;

	public Report() {
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public int getReportId() {
		return this.reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BillStatus getStatus() {
		return status;
	}

	public void setStatus(BillStatus status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Bill addBill(Bill bill) {
		getBills().add(bill);
		bill.setReport(this);
		return bill;
	}

	public Bill removeBill(Bill bill) {
		getBills().remove(bill);
		bill.setReport(null);

		return bill;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
		for (Bill bill : bills) {
			System.out.println(this.toString() + " " + bill);
			bill.setReport(this);
			System.out.println("report in bill " + bill.getReport());
		}
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getApprovedById() {
		return approvedById;
	}

	public void setApprovedById(int approvedById) {
		this.approvedById = approvedById;
	}

}