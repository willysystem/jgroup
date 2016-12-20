package com.jgroup.creditos.model;


/**
 * @generated
 */
public interface ServicioCotizacion {

	/**
	 * @generated
	 */
	java.util.List buscarCotizacion(String nroDocumento);

	/**
	 * @generated
	 */
	Cotizacion getCotizacion(String nroCotizacion);

	/**
	 * @generated
	 */
	Cotizacion nuevaCotizacion(Cotizacion cotizacion);

	/**
	 * @generated
	 */
	void emitirCredito(Long idCotizacion);

	/**
	 * @generated
	 */
	java.util.List getBancos();
}