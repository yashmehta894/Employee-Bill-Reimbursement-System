package com.wissen;


import java.util.Calendar;
import java.util.List;

import com.wissen.model.Bill;


public class UtilityFuctions {

	public static String getQuarter(int month) {

		
		return (month >= Calendar.JANUARY && month <= Calendar.MARCH) ? "Q1"
				: (month >= Calendar.APRIL && month <= Calendar.JUNE) ? "Q2"
						: (month >= Calendar.JULY && month <= Calendar.SEPTEMBER) ? "Q3" : "Q4";
	}
	
	
	public static double calculateTotalAmt(List<Bill> bills) {
		
		double total=0;
		for (Bill bill : bills) {
			total+=bill.getAmount();
			
		}
		return total;
		
		
		
		
	}

}
