package com.jgroup.creditos.model;

import javax.persistence.Table;


/**
 * @generated
 */
@javax.persistence.Entity
@Table(name = "J_Cotizacion")
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
	private java.util.List<PlanPagosCotizacion> planPagosCotizacion = new java.util.ArrayList<PlanPagosCotizacion>();

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
	@javax.persistence.OneToMany(mappedBy = "cotizacion")
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