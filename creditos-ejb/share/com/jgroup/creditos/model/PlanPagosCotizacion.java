package com.jgroup.creditos.model;

import javax.persistence.Table;


/**
 * @generated
 */
@javax.persistence.Entity
@Table(name = "J_PlanPagosCotizacion")
public class PlanPagosCotizacion extends PlanPagos implements
		java.io.Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = -1995653292L;
	/**
	 * @generated
	 */
	private Cotizacion cotizacion;

	/**
	 * @generated
	 */
	public PlanPagosCotizacion() {
	}

	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne
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
		return "PlanPagosCotizacion";
	}
}