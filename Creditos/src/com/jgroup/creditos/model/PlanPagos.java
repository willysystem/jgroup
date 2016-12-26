package com.jgroup.creditos.model;


/**
 * @generated
 */
@javax.persistence.Entity
public class PlanPagos implements java.io.Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = -1828800409L;
	/**
	 * @generated
	 */
	private Long id;
	/**
	 * @generated
	 */
	private Integer nroCuota;
	/**
	 * @generated
	 */
	private java.util.Date fechaVencimiento;
	/**
	 * @generated
	 */
	private Float montoCapital;
	/**
	 * @generated
	 */
	private Float interes;
	/**
	 * @generated
	 */
	private Float primaDesgravamen;
	/**
	 * @generated
	 */
	private Float totalCuota;
	/**
	 * @generated
	 */
	private Float saldoCapital;
	/**
	 * @generated
	 */
	private String nroRecibo;
	/**
	 * @generated
	 */
	private java.util.Date fechaPago;

	/**
	 * @generated
	 */
	public PlanPagos() {
	}

	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.GeneratedValue
	public Long getId() {
		return this.id;
	}

	/**
	 * @generated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @generated
	 */
	public Integer getNroCuota() {
		return this.nroCuota;
	}

	/**
	 * @generated
	 */
	public void setNroCuota(Integer nroCuota) {
		this.nroCuota = nroCuota;
	}

	/**
	 * @generated
	 */
	public java.util.Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	/**
	 * @generated
	 */
	public void setFechaVencimiento(java.util.Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @generated
	 */
	public Float getMontoCapital() {
		return this.montoCapital;
	}

	/**
	 * @generated
	 */
	public void setMontoCapital(Float montoCapital) {
		this.montoCapital = montoCapital;
	}

	/**
	 * @generated
	 */
	public Float getInteres() {
		return this.interes;
	}

	/**
	 * @generated
	 */
	public void setInteres(Float interes) {
		this.interes = interes;
	}

	/**
	 * @generated
	 */
	public Float getPrimaDesgravamen() {
		return this.primaDesgravamen;
	}

	/**
	 * @generated
	 */
	public void setPrimaDesgravamen(Float primaDesgravamen) {
		this.primaDesgravamen = primaDesgravamen;
	}

	/**
	 * @generated
	 */
	public Float getTotalCuota() {
		return this.totalCuota;
	}

	/**
	 * @generated
	 */
	public void setTotalCuota(Float totalCuota) {
		this.totalCuota = totalCuota;
	}

	/**
	 * @generated
	 */
	public Float getSaldoCapital() {
		return this.saldoCapital;
	}

	/**
	 * @generated
	 */
	public void setSaldoCapital(Float saldoCapital) {
		this.saldoCapital = saldoCapital;
	}

	/**
	 * @generated
	 */
	public String getNroRecibo() {
		return this.nroRecibo;
	}

	/**
	 * @generated
	 */
	public void setNroRecibo(String nroRecibo) {
		this.nroRecibo = nroRecibo;
	}

	/**
	 * @generated
	 */
	public java.util.Date getFechaPago() {
		return this.fechaPago;
	}

	/**
	 * @generated
	 */
	public void setFechaPago(java.util.Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "PlanPagos" + " id=" + id + " nroCuota=" + nroCuota
				+ " fechaVencimiento=" + fechaVencimiento + " montoCapital="
				+ montoCapital + " interes=" + interes + " primaDesgravamen="
				+ primaDesgravamen + " totalCuota=" + totalCuota
				+ " saldoCapital=" + saldoCapital + " nroRecibo=" + nroRecibo
				+ " fechaPago=" + fechaPago;
	}
}