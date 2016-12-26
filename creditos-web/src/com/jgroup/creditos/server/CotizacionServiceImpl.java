package com.jgroup.creditos.server;

import com.jgroup.creditos.client.CotizacionService;
import com.jgroup.creditos.endpoint.ServicioCotizacion;
import com.jgroup.creditos.model.Cotizacion;
import com.jgroup.creditos.util.ServiceLocator;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CotizacionServiceImpl extends RemoteServiceServlet implements CotizacionService {
	 
	private ServicioCotizacion servicioCotizacion = ServiceLocator.lookupService(ServicioCotizacion.class); 


	@Override
	public Cotizacion getCotizacion(Cotizacion cotizacion) throws IllegalArgumentException {
		cotizacion = servicioCotizacion.tarifarCotizacion(cotizacion);
		return cotizacion;
	}
}
