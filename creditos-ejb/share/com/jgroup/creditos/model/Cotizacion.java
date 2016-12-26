package com.jgroup.creditos.model;


/**
 * @generated
 */
@javax.persistence.Entity
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

	@Override
	public String toString() {
		return "Cotizacion [contrato=" + contrato + ", estado=" + estado
				+ ", getContrato()=" + getContrato() + ", getEstado()="
				+ getEstado() + ", toString()=" + super.toString()
				+ ", getId()=" + getId() + ", getNombreCompleto()="
				+ getNombreCompleto() + ", getEdadActual()=" + getEdadActual()
				+ ", getFechaNacimiento()=" + getFechaNacimiento()
				+ ", getIngresoBase()=" + getIngresoBase()
				+ ", getNroCuotas()=" + getNroCuotas()
				+ ", getCapacidadPago()=" + getCapacidadPago()
				+ ", getBanco()=" + getBanco() + ", getNroCotizacion()="
				+ getNroCotizacion() + ", getFechaCotizacion()="
				+ getFechaCotizacion() + ", getMontoPrestamo()="
				+ getMontoPrestamo() + ", getMontoBaseCouta()="
				+ getMontoBaseCouta() + ", getDocumentoIdentidad()="
				+ getDocumentoIdentidad() + ", getPlanesPagos()="
				+ getPlanesPagos() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
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
	
	
	
	
}