package com.jgroup.creditos.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jgroup.creditos.model.Cotizacion;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface CotizacionServiceAsync {
	
	public void getCotizacion(Cotizacion cotizacion, AsyncCallback<Cotizacion> async) throws IllegalArgumentException;
	
}
