package com.jgroup.creditos.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jgroup.creditos.model.Contrato;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ContratoServiceAsync {
	
	
	public void buscarContrato(String nroDocumento, AsyncCallback<List<Contrato>> async);
	
	public void getContrato(Long contratoId, AsyncCallback<Contrato> sync);
	
	public void modificarCredito(Long contratoId, Date fechaEmision, Date fechaLiquidacion, String nroPrestamo, AsyncCallback<Void> call);
	
	public void modificarPlanPagosCredito(Long planPagosId, Date fechaPago, String nroRecibo, AsyncCallback<Void> call);
	
	
}
