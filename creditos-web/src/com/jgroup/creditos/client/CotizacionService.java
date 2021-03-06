package com.jgroup.creditos.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.jgroup.creditos.model.Banco;
import com.jgroup.creditos.model.Cotizacion;
import com.jgroup.creditos.model.PlanPagosCotizacion;


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

	public Cotizacion getCotizacion(Cotizacion cotizacion) throws Exception;
	
	public List<Cotizacion> buscarCotizacion(String nroDocumento) throws Exception; 

	public List<PlanPagosCotizacion> getPlanPagosCotizacion(Long cotizacionId) throws Exception;
	
	public List<Banco> getBancos() throws Exception;
	
	public void emitirCredito(Cotizacion cotizacionI) throws Exception;
	
	public void nuevoBanco(Banco banco) throws Exception;
	
	public Banco actualizarBanco(Banco banco) throws Exception;
	
	public void eliminarBanco(Long bancoId) throws Exception;
	
	public void eliminarCotizacion(Long cotizacionId) throws Exception;
}
