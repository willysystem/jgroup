package com.jgroup.creditos.server;

import com.jgroup.creditos.client.CotizacionService;
import com.jgroup.creditos.endpoint.ServicioCotizacion;
import com.jgroup.creditos.model.Banco;
import com.jgroup.creditos.model.Cotizacion;
import com.jgroup.creditos.model.PlanPagosCotizacion;
import com.jgroup.creditos.util.ServiceLocator;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CotizacionServiceImpl extends RemoteServiceServlet implements CotizacionService {
	 
	private ServicioCotizacion servicioCotizacion = ServiceLocator.lookupService(ServicioCotizacion.class); 

	@Override
	public Cotizacion getCotizacion(Cotizacion cotizacion) throws Exception {
		cotizacion = servicioCotizacion.tarifarCotizacion(cotizacion);
		return cotizacion;
	}

	@Override
	public List<Cotizacion> buscarCotizacion(String nroDocumento) throws Exception {
		return servicioCotizacion.buscarCotizacion(nroDocumento);
	}

	@Override
	public List<PlanPagosCotizacion> getPlanPagosCotizacion(Long cotizacionId) throws Exception {
		return servicioCotizacion.getPlanPagosCotizacion(cotizacionId);
	}

	@Override
	public List<Banco> getBancos() throws Exception {
		return servicioCotizacion.getBancos();
	}

	@Override
	public void emitirCredito(Cotizacion cotizacionI) throws Exception {
		servicioCotizacion.emitirCredito(cotizacionI);
	}

	@Override
	public void nuevoBanco(Banco banco) throws Exception {
		servicioCotizacion.nuevoBanco(banco);
	}

	@Override
	public Banco actualizarBanco(Banco banco) throws Exception {
		return servicioCotizacion.actualizarBanco(banco);
	}

	@Override
	public void eliminarBanco(Long bancoId) throws Exception {
		servicioCotizacion.eliminarBanco(bancoId);
	}

	@Override
	public void eliminarCotizacion(Long cotizacionId) throws Exception {
		servicioCotizacion.eliminarCotizacion(cotizacionId);
	}

	
}
