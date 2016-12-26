package com.jgroup.creditos.endpoint;

import com.jgroup.creditos.model.Cotizacion;


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