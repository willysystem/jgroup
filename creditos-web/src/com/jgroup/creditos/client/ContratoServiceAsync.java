package com.jgroup.creditos.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jgroup.creditos.model.Contrato;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ContratoServiceAsync {
	
	
	public void buscarContrato(String nroDocumento, AsyncCallback<List<Contrato>> async);
	
	public void getContrato(Long contratoId, AsyncCallback<Contrato> sync);
	
}
