package com.jgroup.creditos.finance;

public class FinanceCalculator {
	
	private static FinanceCalculator instance = null;
	
	public static FinanceCalculator getInstance() {
		if(instance == null) {
			instance = new FinanceCalculator();
		}
		return instance;
	}
	
	public Double calculateVA(double tasa, int numeroPeriodos) {
		double va = 0.00D;
		return va;
	}

	public static void main(String[] args) {

	}

}
