package com.jgroup.creditos.endpoint;

import com.jgroup.creditos.model.Cotizacion;

/**
 * @generated
 */
public interface ServicioCotizacion {

	/**
	 * @generated
	 */
	java.util.List<Cotizacion> buscarCotizacion(String nroDocumento);

	/**
	 * @generated
	 */
	com.jgroup.creditos.model.Cotizacion getCotizacion(String nroCotizacion);

	/**
	 * @generated
	 */
	com.jgroup.creditos.model.Cotizacion tarifarCotizacion(com.jgroup.creditos.model.Cotizacion cotizacion);

	/**
	 * @generated
	 */
	void emitirCredito(Long idCotizacion);

	/**
	 * @generated
	 */
	java.util.List getBancos();
}