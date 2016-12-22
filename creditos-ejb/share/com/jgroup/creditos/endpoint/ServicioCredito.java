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
	com.jgroup.creditos.model.Contrato getContrato(String nroContrato);
}