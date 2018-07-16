package com.wissen.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the bill database table.
 * 
 */
@Entity
@NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b")
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int billId;

	private double amount;

	private int billNo;

	@Enumerated(EnumType.STRING)
	private BillType billType;

	private String path;

	// bi-directional many-to-one association to Report
	@JsonIgnoreProperties("bills")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ReportId")
	@JsonIgnore
	private Report report;

	@Temporal(TemporalType.DATE)
	private Date billDate;

	public Bill() {
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public int getBillId() {
		return this.billId;
	}

	
	public void setBillId(int billId) {
		this.billId = billId;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getBillNo() {
		return this.billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	public BillType getBillType() {
		return billType;
	}

	public void setBillType(BillType billType) {
		this.billType = billType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

}