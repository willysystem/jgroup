package com.jgroup.creditos.endpoint;

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
	
}