package com.jgroup.creditos.endpoint;


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
	com.jgroup.creditos.model.Cotizacion getCotizacion(String nroCotizacion);

	/**
	 * @generated
	 */
	com.jgroup.creditos.model.Cotizacion nuevaCotizacion(com.jgroup.creditos.model.Cotizacion cotizacion);

	/**
	 * @generated
	 */
	void emitirCredito(Long idCotizacion);

	/**
	 * @generated
	 */
	java.util.List getBancos();
}