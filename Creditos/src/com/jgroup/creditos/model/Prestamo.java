package com.jgroup.creditos.model;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


/**
 * @generated
 */
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@javax.persistence.Entity
public abstract class Prestamo implements java.io.Serializable {
	/**
	 * @generated
	 */
	private static final long serialVersionUID = -1818225879L;
	/**
	 * @generated
	 */
	private Long id;

	/**
	 * @generated
	 */
	private String nombreCompleto;

	/**
	 * @generated
	 */
	private Integer edadActual;
	/**
	 * @generated
	 */
	private java.util.Date fechaNacimiento;
	/**
	 * @generated
	 */
	private Float ingresoBase;
	/**
	 * @generated
	 */
	private Integer nroCuotas;

	/**
	 * @generated
	 */
	private Float capacidadPago;
	/**
	 * @generated
	 */
	private Banco banco;

	/**
	 * @generated
	 */
	private String nroCotizacion;

	/**
	 * @generated
	 */
	private java.util.Date fechaCotizacion;

	/**
	 * @generated
	 */
	private Float montoPrestamo;

	/**
	 * @generated
	 */
	private Float montoBaseCouta;

	/**
	 * @generated
	 */
	private String documentoIdentidad;

	/**
	 * @generated
	 */
	private java.util.List<PlanPagos> planesPagos = new java.util.ArrayList<PlanPagos>();

	/**
	 * @generated
	 */
	public String toString() {
		return "Prestamo" + " id=" + id + " nombreCompleto=" + nombreCompleto
				+ " edadActual=" + edadActual + " fechaNacimiento="
				+ fechaNacimiento + " ingresoBase=" + ingresoBase
				+ " nroCuotas=" + nroCuotas + " capacidadPago=" + capacidadPago
				+ " nroCotizacion=" + nroCotizacion + " fechaCotizacion="
				+ fechaCotizacion + " montoPrestamo=" + montoPrestamo
				+ " montoBaseCouta=" + montoBaseCouta + " documentoIdentidad="
				+ documentoIdentidad;
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
	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	/**
	 * @generated
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * @generated
	 */
	public Integer getEdadActual() {
		return this.edadActual;
	}

	/**
	 * @generated
	 */
	public void setEdadActual(Integer edadActual) {
		this.edadActual = edadActual;
	}

	/**
	 * @generated
	 */
	public java.util.Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	/**
	 * @generated
	 */
	public void setFechaNacimiento(java.util.Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @generated
	 */
	public Float getIngresoBase() {
		return this.ingresoBase;
	}

	/**
	 * @generated
	 */
	public void setIngresoBase(Float ingresoBase) {
		this.ingresoBase = ingresoBase;
	}

	/**
	 * @generated
	 */
	public Integer getNroCuotas() {
		return this.nroCuotas;
	}

	/**
	 * @generated
	 */
	public void setNroCuotas(Integer nroCuotas) {
		this.nroCuotas = nroCuotas;
	}

	/**
	 * @generated
	 */
	public Float getCapacidadPago() {
		return this.capacidadPago;
	}

	/**
	 * @generated
	 */
	public void setCapacidadPago(Float capacidadPago) {
		this.capacidadPago = capacidadPago;
	}

	/**
	 * @generated
	 */
	@javax.persistence.ManyToOne
	public Banco getBanco() {
		return this.banco;
	}

	/**
	 * @generated
	 */
	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	/**
	 * @generated
	 */
	public String getNroCotizacion() {
		return this.nroCotizacion;
	}

	/**
	 * @generated
	 */
	public void setNroCotizacion(String nroCotizacion) {
		this.nroCotizacion = nroCotizacion;
	}

	/**
	 * @generated
	 */
	public java.util.Date getFechaCotizacion() {
		return this.fechaCotizacion;
	}

	/**
	 * @generated
	 */
	public void setFechaCotizacion(java.util.Date fechaCotizacion) {
		this.fechaCotizacion = fechaCotizacion;
	}

	/**
	 * @generated
	 */
	public Float getMontoPrestamo() {
		return this.montoPrestamo;
	}

	/**
	 * @generated
	 */
	public void setMontoPrestamo(Float montoPrestamo) {
		this.montoPrestamo = montoPrestamo;
	}

	/**
	 * @generated
	 */
	public Float getMontoBaseCouta() {
		return this.montoBaseCouta;
	}

	/**
	 * @generated
	 */
	public void setMontoBaseCouta(Float montoBaseCouta) {
		this.montoBaseCouta = montoBaseCouta;
	}

	/**
	 * @generated
	 */
	public String getDocumentoIdentidad() {
		return this.documentoIdentidad;
	}

	/**
	 * @generated
	 */
	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}

	/**
	 * @generated
	 */
	public Prestamo() {
	}

	/**
	 * @generated
	 */
	@javax.persistence.OneToMany(mappedBy = "prestamo")
	public java.util.List<PlanPagos> getPlanesPagos() {
		return this.planesPagos;
	}

	/**
	 * @generated
	 */
	public void setPlanesPagos(java.util.List<PlanPagos> planesPagos) {
		this.planesPagos = planesPagos;
	}

	/**
	 * @generated
	 */
	public void addPlanesPagos(PlanPagos planesPagos) {
		getPlanesPagos().add(planesPagos);
		planesPagos.setPrestamo(this);
	}

	/**
	 * @generated
	 */
	public void removePlanesPagos(PlanPagos planesPagos) {
		getPlanesPagos().remove(planesPagos);
		planesPagos.setPrestamo(null);
	}
}