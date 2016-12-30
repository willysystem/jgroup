package com.jgroup.creditos.model;

import javax.persistence.CascadeType;
import javax.persistence.Table;


/**
 * @generated
 */
@Table(name = "J_Contrato")
@javax.persistence.Entity
public class Contrato extends Prestamo implements java.io.Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = -1108940986L;
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
	private java.util.List<PlanPagosContrato> planPagosCotnrato = new java.util.ArrayList<PlanPagosContrato>();

	/**
	 * @generated
	 */
	private java.util.Date fechaEmision;

	/**
	 * @generated
	 */
	public Contrato() {
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
	public String toString() {
		return "Contrato" + " nroPrestamo=" + nroPrestamo
				+ " fechaLiquidacion=" + fechaLiquidacion + " fechaEmision="
				+ fechaEmision;
	}

	/**
	 * @generated
	 */
	@javax.persistence.OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval=true)
	public java.util.List<PlanPagosContrato> getPlanPagosCotnrato() {
		return this.planPagosCotnrato;
	}

	/**
	 * @generated
	 */
	public void setPlanPagosCotnrato(
			java.util.List<PlanPagosContrato> planPagosCotnrato) {
				this.planPagosCotnrato = planPagosCotnrato;
			}

	/**
	 * @generated
	 */
	public void addPlanPagosCotnrato(PlanPagosContrato planPagosCotnrato) {
		getPlanPagosCotnrato().add(planPagosCotnrato);
		planPagosCotnrato.setContrato(this);
	}

	/**
	 * @generated
	 */
	public void removePlanPagosCotnrato(PlanPagosContrato planPagosCotnrato) {
		getPlanPagosCotnrato().remove(planPagosCotnrato);
		planPagosCotnrato.setContrato(null);
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
}