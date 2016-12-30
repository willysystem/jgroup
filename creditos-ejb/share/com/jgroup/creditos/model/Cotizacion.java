package com.jgroup.creditos.model;

import javax.persistence.CascadeType;
import javax.persistence.Table;


/**
 * @generated
 */
@Table(name = "J_Cotizacion")
@javax.persistence.Entity
public class Cotizacion extends Prestamo implements java.io.Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 382447927L;
	/**
	 * @generated
	 */
	private Character estado;

	/**
	 * @generated
	 */
	private java.util.List<PlanPagosCotizacion> planPagosCotizacion = new java.util.ArrayList<PlanPagosCotizacion>();

	/**
	 * @generated
	 */
	public Cotizacion() {
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

	/**
	 * @generated
	 */
	public String toString() {
		return "Cotizacion" + " estado=" + estado;
	}

	/**
	 * @generated
	 */
	@javax.persistence.OneToMany(mappedBy = "cotizacion", cascade = {CascadeType.ALL})
	public java.util.List<PlanPagosCotizacion> getPlanPagosCotizacion() {
		return this.planPagosCotizacion;
	}

	/**
	 * @generated
	 */
	public void setPlanPagosCotizacion(
			java.util.List<PlanPagosCotizacion> planPagosCotizacion) {
				this.planPagosCotizacion = planPagosCotizacion;
			}

	/**
	 * @generated
	 */
	public void addPlanPagosCotizacion(PlanPagosCotizacion planPagosCotizacion) {
		getPlanPagosCotizacion().add(planPagosCotizacion);
		planPagosCotizacion.setCotizacion(this);
	}

	/**
	 * @generated
	 */
	public void removePlanPagosCotizacion(
			PlanPagosCotizacion planPagosCotizacion) {
				getPlanPagosCotizacion().remove(planPagosCotizacion);
				planPagosCotizacion.setCotizacion(null);
			}
}