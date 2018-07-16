package com.wissen.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the billtypelimit database table.
 * 
 */
@Embeddable
public class BilltypelimitPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	private BillType billType;

	@Column(insertable=false, updatable=false)
	private int centerId;

	public BilltypelimitPK() {
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

	public int getCenterId() {
		return this.centerId;
	}
	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BilltypelimitPK)) {
			return false;
		}
		BilltypelimitPK castOther = (BilltypelimitPK)other;
		return 
			this.billType.equals(castOther.billType)
			&& (this.centerId == castOther.centerId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.billType.hashCode();
		hash = hash * prime + this.centerId;
		
		return hash;
	}
}