package com.jgroup.creditos.model;


/**
 * @generated
 */
@javax.persistence.Entity
public class Contrato extends Prestamo implements java.io.Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = -1108940986L;
	/**
	 * @generated
	 */
	private Cotizacion cotizacion;

	/**
	 * @generated
	 */
	private String nroPrestamo;

	/**
	 * @generated
	 */
	private java.util.Date fechaLiquidacion;

	/**
	 * @generated
	 */
	private java.util.Date fechaEmision;

	/**
	 * @generated
	 */
	@javax.persistence.OneToOne
	public Cotizacion getCotizacion() {
		return this.cotizacion;
	}

	/**
	 * @generated
	 */
	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "Contrato" + " nroPrestamo=" + nroPrestamo + " fechaEmision="
				+ fechaEmision + " fechaLiquidacion=" + fechaLiquidacion;
	}

	/**
	 * @generated
	 */
	public String getNroPrestamo() {
		return this.nroPrestamo;
	}

	/**
	 * @generated
	 */
	public void setNroPrestamo(String nroPrestamo) {
		this.nroPrestamo = nroPrestamo;
	}

	/**
	 * @generated
	 */
	public java.util.Date getFechaLiquidacion() {
		return this.fechaLiquidacion;
	}

	/**
	 * @generated
	 */
	public void setFechaLiquidacion(java.util.Date fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	/**
	 * @generated
	 */
	public java.util.Date getFechaEmision() {
		return this.fechaEmision;
	}

	/**
	 * @generated
	 */
	public void setFechaEmision(java.util.Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @generated
	 */
	public Contrato() {
	}
}