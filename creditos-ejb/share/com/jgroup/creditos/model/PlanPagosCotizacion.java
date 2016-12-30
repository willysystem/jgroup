package com.jgroup.creditos.model;

import javax.persistence.CascadeType;
import javax.persistence.Table;


/**
 * @generated
 */
@Table(name = "J_PlanPagosCotizacion")
@javax.persistence.Entity
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
	@javax.persistence.ManyToOne(cascade = {CascadeType.ALL})
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