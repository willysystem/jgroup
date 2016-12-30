package com.jgroup.creditos.model;

import javax.persistence.CascadeType;
import javax.persistence.Table;

/**
 * @generated
 */
@Table(name = "J_PlanPagosContrato")
@javax.persistence.Entity
public class PlanPagosContrato extends PlanPagos implements
		java.io.Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = 1851709603L;
	/**
	 * @generated
	 */
	private Contrato contrato;

	/**
	 * @generated
	 */
	private Float interesMora;

	/**
	 * @generated
	 */
	public PlanPagosContrato() {
	}

	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne(cascade = CascadeType.ALL)
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
		return "PlanPagosContrato" + " interesMora=" + interesMora;
	}

	/**
	 * @generated
	 */
	public Float getInteresMora() {
		return this.interesMora;
	}

	/**
	 * @generated
	 */
	public void setInteresMora(Float interesMora) {
		this.interesMora = interesMora;
	}
}