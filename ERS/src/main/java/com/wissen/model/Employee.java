package com.wissen.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
@Table(name = "employee")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "empId")
	private int empId;

	private String accNum;

	private String bankName;

	private double billLimit;

	private String ifsc;

	private int level;

	private String name;

	private String role;

	private String password;

	// bi-directional many-to-one association to Costcenter
	//@JsonIgnore
	@JsonIgnoreProperties("employees")
	@ManyToOne
	@JoinColumn(name = "CenterId")
	private Costcenter costcenter;

	@Column(name = "MgrId")
	private int mgrId;

	// bi-directional many-to-one association to Employee
	@JsonIgnoreProperties("employees,employee")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JoinColumn(name = "MgrId")
	private Employee employee;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getMgrId() {
		return mgrId;
	}

	public void setMgrId(int mgrId) {
		this.mgrId = mgrId;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	// bi-directional many-to-one association to Employee
	@JsonIgnoreProperties({ "employees", "costcenter,employee" })
	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Employee> employees;

	// bi-directional many-to-one association to Report
	// @JsonIgnoreProperties("employee")
	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	private List<Report> reports;

	public Employee() {
	}

	public int getEmpId() {
		return this.empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getAccNum() {
		return this.accNum;
	}

	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public double getBillLimit() {
		return this.billLimit;
	}

	public void setBillLimit(double billLimit) {
		this.billLimit = billLimit;
	}

	public String getIfsc() {
		return this.ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// @JsonIgnore
	public Costcenter getCostcenter() {
		return this.costcenter;
	}

	// @JsonIgnore
	public void setCostcenter(Costcenter costcenter) {
		this.costcenter = costcenter;
	}

	// public Employee getEmployee() {
	// return this.employee;
	// }

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	//
	// public void setEmployees(List<Employee> employees) {
	// this.employees = employees;
	// }

	//
	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setEmployee(this);
		//
		return employee;
	}

	//
	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setEmployee(null);
		//
		return employee;
	}

	public List<Report> getReports() {
		return this.reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public Report addReport(Report report) {
		getReports().add(report);
		report.setEmployee(this);

		return report;
	}

	public Report removeReport(Report report) {
		getReports().remove(report);
		report.setEmployee(null);

		return report;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}