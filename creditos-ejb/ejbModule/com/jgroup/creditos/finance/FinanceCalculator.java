package com.jgroup.creditos.finance;

public class FinanceCalculator {

	private static FinanceCalculator instance = null;

	public static FinanceCalculator getInstance() {
		if (instance == null) {
			instance = new FinanceCalculator();
		}
		return instance;
	}

	/**
	 * 
	 * @param montoBase
	 * @param porcentajeBase
	 * @param tasa
	 * @param numeroPeriodos
	 * @param tipo
	 * @return
	 */
	public Double calculateVA(double montoBase, double porcentajeBase, double tasa, int numeroPeriodos, int tipo) {
		Double valorPresente = 0.00D;
		Integer nPeriodos = numeroPeriodos - 1;
		Double montoCuota = -montoBase * porcentajeBase;
		Double tasaMensual = tasa / 12;
		Double valorFuturo = 0.00d;
		// Valor Presente
		Double result = (Double) Math.pow((1 + tasaMensual), nPeriodos);
		if (tasaMensual == 0) {
			valorPresente = valorFuturo - (montoCuota * nPeriodos);
		} else {
			valorPresente = (valorFuturo - montoCuota * (1 + tasaMensual * tipo) * ((result - 1) / tasaMensual)) / result;
		}

		return valorPresente;
	}

	public static void main(String[] args) {
		System.out.println("Monto de Prestamo: " + FinanceCalculator.getInstance().calculateVA(4000.00d, 0.50d, 0.18, 12, 0));
	}

}
