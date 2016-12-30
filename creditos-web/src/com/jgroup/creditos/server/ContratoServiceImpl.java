package com.jgroup.creditos.server;

import com.jgroup.creditos.client.ContratoService;
import com.jgroup.creditos.endpoint.ServicioCredito;
import com.jgroup.creditos.model.Contrato;
import com.jgroup.creditos.util.ServiceLocator;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ContratoServiceImpl extends RemoteServiceServlet implements ContratoService {
	 
	private ServicioCredito servicioCredito = ServiceLocator.lookupService(ServicioCredito.class); 

	@Override
	public List<Contrato> buscarContrato(String nroDocumento) throws Exception {
		return servicioCredito.buscarCredito(nroDocumento);
	}

	@Override
	public Contrato getContrato(Long contratoId) {
		return servicioCredito.getContrato(contratoId);
	}

}
