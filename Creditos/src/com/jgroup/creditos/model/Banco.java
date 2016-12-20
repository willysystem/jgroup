package com.jgroup.creditos.model;


/**
 * @generated
 */
@javax.persistence.Entity
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
	private java.util.Set<Prestamo> cotizacion = new java.util.HashSet<Prestamo>();

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
	public String toString() {
		return "Banco" + " id=" + id + " nombre=" + nombre + " tasaInteres="
				+ tasaInteres + " primaDesgravamen=" + primaDesgravamen;
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
	public java.util.Set<Prestamo> getCotizacion() {
		return this.cotizacion;
	}

	/**
	 * @generated
	 */
	public void setCotizacion(java.util.Set<Prestamo> cotizacion) {
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
}