package com.jgroup.creditos.model;

import javax.persistence.Table;

/**
 * @generated
 */
@javax.persistence.Entity
@Table(name = "J_PlanPagosContrato")
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
	public PlanPagosContrato() {
	}

	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne
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
		return "PlanPagosContrato";
	}
}