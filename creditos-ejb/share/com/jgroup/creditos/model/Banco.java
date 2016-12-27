package com.jgroup.creditos.model;

import javax.persistence.Table;


/**
 * @generated
 */
@javax.persistence.Entity
@Table(name = "J_Banco")
public class Banco implements java.io.Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = -1491374895L;
	/**
	 * @generated
	 */
	private Long id;
	/**
	 * @generated
	 */
	private String nombre;
	/**
	 * @generated
	 */
	private Float tasaInteres;
	/**
	 * @generated
	 */
	private Float primaDesgravamen;
	/**
	 * @generated
	 */
	private java.util.List<Prestamo> cotizacion = new java.util.ArrayList<Prestamo>();

	/**
	 * @generated
	 */
	public Banco() {
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
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * @generated
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @generated
	 */
	public Float getTasaInteres() {
		return this.tasaInteres;
	}

	/**
	 * @generated
	 */
	public void setTasaInteres(Float tasaInteres) {
		this.tasaInteres = tasaInteres;
	}

	/**
	 * @generated
	 */
	public Float getPrimaDesgravamen() {
		return this.primaDesgravamen;
	}

	/**
	 * @generated
	 */
	public void setPrimaDesgravamen(Float primaDesgravamen) {
		this.primaDesgravamen = primaDesgravamen;
	}

	/**
	 * @generated
	 */
	@javax.persistence.OneToMany(mappedBy = "banco")
	public java.util.List<Prestamo> getCotizacion() {
		return this.cotizacion;
	}

	/**
	 * @generated
	 */
	public void setCotizacion(java.util.List<Prestamo> cotizacion) {
		this.cotizacion = cotizacion;
	}

	/**
	 * @generated
	 */
	public void addCotizacion(Prestamo cotizacion) {
		getCotizacion().add(cotizacion);
		cotizacion.setBanco(this);
	}

	/**
	 * @generated
	 */
	public void removeCotizacion(Prestamo cotizacion) {
		getCotizacion().remove(cotizacion);
		cotizacion.setBanco(null);
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "Banco" + " id=" + id + " nombre=" + nombre + " tasaInteres="
				+ tasaInteres + " primaDesgravamen=" + primaDesgravamen;
	}
}