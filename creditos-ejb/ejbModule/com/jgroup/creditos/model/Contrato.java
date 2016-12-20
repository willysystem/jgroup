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
	public Contrato() {
	}

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
		return "Contrato" + " nroPrestamo=" + nroPrestamo;
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
}