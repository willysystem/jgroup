package com.jgroup.creditos.endpoint;

import java.util.Date;

import com.jgroup.creditos.model.Contrato;
/**
 * @generated
 */
public interface ServicioCredito {
	/**
	 * @generated
	 */
	java.util.List<Contrato> buscarCredito(String nroDocumento);

	/**
	 * @generated
	 */
	public Contrato getContrato(Long contratoId);
	
	public void modificarCredito(Long contratoId, Date fechaEmision, Date fechaLiquidacion, String nroPrestamo) throws Exception;
	
	public void modificarPlanPagosCredito(Long planPagosId, Date fechaPago, String nroRecibo) throws Exception;
	
}