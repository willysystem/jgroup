package com.jgroup.creditos.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jgroup.creditos.model.Banco;
import com.jgroup.creditos.model.Cotizacion;
import com.jgroup.creditos.model.PlanPagosCotizacion;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface CotizacionServiceAsync {
	
	public void getCotizacion(Cotizacion cotizacion, AsyncCallback<Cotizacion> async);
	
	public void buscarCotizacion(String nroDocumento, AsyncCallback<List<Cotizacion>> async);
	
	public void getPlanPagosCotizacion(Long cotizacionId, AsyncCallback<List<PlanPagosCotizacion>> async);
	
	public void getBancos(AsyncCallback<List<Banco>> async);
	
	public void emitirCredito(Cotizacion cotizacion, AsyncCallback<Void> async);
	
	public void nuevoBanco(Banco banco, AsyncCallback<Void> call);
	
	public void actualizarBanco(Banco banco, AsyncCallback<Banco> call) ;
	
	public void eliminarBanco(Long bancoId, AsyncCallback<Void> call);
	
	public void eliminarCotizacion(Long cotizacionId, AsyncCallback<Void> call);
	
}
