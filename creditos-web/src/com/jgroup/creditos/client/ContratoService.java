package com.jgroup.creditos.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.jgroup.creditos.model.Contrato;


/**
 * GWT RPC Service
 * @author willy
 */
public interface ContratoService extends RemoteService {

	public static class Util {
		private static ContratoServiceAsync instance;

		public static ContratoServiceAsync getInstance() {
			if (instance == null) {
				instance = (ContratoServiceAsync) GWT.create(ContratoService.class);
				ServiceDefTarget endpoint = (ServiceDefTarget) instance;
				endpoint.setServiceEntryPoint(GWT.getHostPageBaseURL() + "contrato");
			}
			return instance;
		}
	}
	
	public List<Contrato> buscarContrato(String nroDocumento) throws Exception; 

	public Contrato getContrato(Long contratoId);
	
}
