package com.jgroup.creditos.model;


/**
 * @generated
 */
@javax.persistence.Entity
public class Cotizacion extends Prestamo implements java.io.Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 382447927L;
	/**
	 * @generated
	 */
	private Contrato contrato;

	/**
	 * @generated
	 */
	private Character estado;

	/**
	 * @generated
	 */
	public Cotizacion() {
	}

	/**
	 * @generated
	 */
	@javax.persistence.OneToOne(mappedBy = "cotizacion")
	public Contrato getContrato() {
		return this.contrato;
	}

	/**
	 * @generated
	 */
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "Cotizacion" + " estado=" + estado;
	}

	/**
	 * @generated
	 */
	public Character getEstado() {
		return this.estado;
	}

	/**
	 * @generated
	 */
	public void setEstado(Character estado) {
		this.estado = estado;
	}
}