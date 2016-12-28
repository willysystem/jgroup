package com.jgroup.creditos.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jgroup.creditos.model.Cotizacion;
import com.jgroup.creditos.model.PlanPagosCotizacion;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface CotizacionServiceAsync {
	
	public void getCotizacion(Cotizacion cotizacion, AsyncCallback<Cotizacion> async) throws IllegalArgumentException;
	
	public void buscarCotizacion(String nroDocumento, AsyncCallback<List<Cotizacion>> async) throws IllegalArgumentException;
	
	public void getPlanPagosCotizacion(Long cotizacionId, AsyncCallback<List<PlanPagosCotizacion>> async) throws IllegalArgumentException;
	
}
