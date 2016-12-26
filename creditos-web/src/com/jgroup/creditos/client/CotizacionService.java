package com.jgroup.creditos.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.jgroup.creditos.model.Cotizacion;


/**
 * GWT RPC Service
 * @author willy
 */
public interface CotizacionService extends RemoteService {

	public static class Util {
		private static CotizacionServiceAsync instance;

		public static CotizacionServiceAsync getInstance() {
			if (instance == null) {
				instance = (CotizacionServiceAsync) GWT.create(CotizacionService.class);
				ServiceDefTarget endpoint = (ServiceDefTarget) instance;
				endpoint.setServiceEntryPoint(GWT.getHostPageBaseURL() + "cotizacion");
			}
			return instance;
		}
	}

	Cotizacion getCotizacion(Cotizacion cotizacion) throws IllegalArgumentException;

}