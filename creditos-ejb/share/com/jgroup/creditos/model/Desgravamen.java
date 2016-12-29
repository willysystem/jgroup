package com.jgroup.creditos.model;

import javax.persistence.Table;

/**
 * @generated
 */
@Table(name = "J_Desgravamen")
@javax.persistence.Entity
public class Desgravamen implements java.io.Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = -1031787043L;
	/**
	 * @generated
	 */
	private Long id;
	/**
	 * @generated
	 */
	private Integer edadMeses;
	/**
	 * @generated
	 */
	private Integer duracionPago;
	/**
	 * @generated
	 */
	private Float factorDesgravamen;

	/**
	 * @generated
	 */
	public Desgravamen() {
	}

	/**
	 * @generated
	 */
	@javax.persistence.Id
	@javax.persistence.GeneratedValue
	public Long getId() {
		return this.id;
	}

	/**
	 * @generated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @generated
	 */
	public Integer getEdadMeses() {
		return this.edadMeses;
	}

	/**
	 * @generated
	 */
	public void setEdadMeses(Integer edadMeses) {
		this.edadMeses = edadMeses;
	}

	/**
	 * @generated
	 */
	public Integer getDuracionPago() {
		return this.duracionPago;
	}

	/**
	 * @generated
	 */
	public void setDuracionPago(Integer duracionPago) {
		this.duracionPago = duracionPago;
	}

	/**
	 * @generated
	 */
	public Float getFactorDesgravamen() {
		return this.factorDesgravamen;
	}

	/**
	 * @generated
	 */
	public void setFactorDesgravamen(Float factorDesgravamen) {
		this.factorDesgravamen = factorDesgravamen;
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "Desgravamen" + " id=" + id + " edadMeses=" + edadMeses
				+ " duracionPago=" + duracionPago + " factorDesgravamen="
				+ factorDesgravamen;
	}
}