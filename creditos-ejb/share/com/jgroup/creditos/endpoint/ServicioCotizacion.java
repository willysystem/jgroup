package com.jgroup.creditos.endpoint;

import java.util.List;

import com.jgroup.creditos.exceptions.JGroupException;
import com.jgroup.creditos.model.Banco;
import com.jgroup.creditos.model.Cotizacion;
import com.jgroup.creditos.model.PlanPagosCotizacion;

/**
 * @generated
 */
public interface ServicioCotizacion {

	/**
	 * @generated
	 */
	java.util.List<Cotizacion> buscarCotizacion(String nroDocumento) throws Exception;

	/**
	 * @generated
	 */
	com.jgroup.creditos.model.Cotizacion getCotizacion(String nroCotizacion) throws Exception;

	/**
	 * @generated
	 */
	com.jgroup.creditos.model.Cotizacion tarifarCotizacion(com.jgroup.creditos.model.Cotizacion cotizacion) throws Exception;

	/**
	 * @generated
	 */
	public void emitirCredito(Long idCotizacion) throws Exception;

	/**
	 * @generated
	 */
	public java.util.List<Banco> getBancos() throws Exception;
	
	
	public List<PlanPagosCotizacion> getPlanPagosCotizacion(Long cotizacionId) throws Exception;
	
}