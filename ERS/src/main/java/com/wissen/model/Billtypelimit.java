package com.wissen.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the billtypelimit database table.
 * 
 */
@Entity
@NamedQuery(name = "Billtypelimit.findAll", query = "SELECT b FROM Billtypelimit b")
public class Billtypelimit implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BilltypelimitPK id;

	private double billLimit;

	private int level;

	// bi-directional many-to-one association to Costcenter
	@JsonIgnoreProperties("centerId")
	@ManyToOne
	@JoinColumn(name = "CenterId", insertable = false, updatable = false)
	private Costcenter costcenter;

	public Billtypelimit() {
	}

	public BilltypelimitPK getId() {
		return this.id;
	}

	public void setId(BilltypelimitPK id) {
		this.id = id;
	}

	public double getBillLimit() {
		return this.billLimit;
	}

	public void setBillLimit(double billLimit) {
		this.billLimit = billLimit;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Costcenter getCostcenter() {
		return this.costcenter;
	}

	public void setCostcenter(Costcenter costcenter) {
		this.costcenter = costcenter;
	}

}