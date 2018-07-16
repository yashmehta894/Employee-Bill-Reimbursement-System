package com.wissen.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * The persistent class for the costcenter database table.
 * 
 */
@Entity
@NamedQuery(name = "Costcenter.findAll", query = "SELECT c FROM Costcenter c")
public class Costcenter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int centerId;

	private String centerName;

	// bi-directional many-to-one association to Billtypelimit
	@JsonIgnore
	@JsonIgnoreProperties("costcenter")
	@OneToMany(mappedBy = "costcenter")
	private List<Billtypelimit> billtypelimits;

	// bi-directional many-to-one association to Employee
	@JsonIgnoreProperties({ "costcenter", "employees", "employee" })
	@OneToMany(mappedBy = "costcenter", fetch = FetchType.LAZY)
	private List<Employee> employees;

	public Costcenter() {
	}

	public int getCenterId() {
		return this.centerId;
	}

	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}

	public String getCenterName() {
		return this.centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public List<Billtypelimit> getBilltypelimits() {
		return this.billtypelimits;
	}

	public void setBilltypelimits(List<Billtypelimit> billtypelimits) {
		this.billtypelimits = billtypelimits;
	}

	public Billtypelimit addBilltypelimit(Billtypelimit billtypelimit) {
		getBilltypelimits().add(billtypelimit);
		billtypelimit.setCostcenter(this);

		return billtypelimit;
	}

	public Billtypelimit removeBilltypelimit(Billtypelimit billtypelimit) {
		getBilltypelimits().remove(billtypelimit);
		billtypelimit.setCostcenter(null);

		return billtypelimit;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setCostcenter(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setCostcenter(null);

		return employee;
	}

}