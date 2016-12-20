package com.jgroup.creditos.servicios;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.jgroup.creditos.model.Cotizacion;

/**
 * @generated
 */
@Path("/cotizacion")
public interface ServicioCotizacion {

	/**
	 * @generated
	 */
	@GET
	@Path("/buscar/{nroDocumento}")
	@Produces({ "application/json" })
	java.util.List<Cotizacion> buscarCotizacion(@PathParam("nroDocumento") String nroDocumento);

	/**
	 * @generated
	 */
	Cotizacion getCotizacion(String nroCotizacion);

	/**
	 * @generated
	 */
	Cotizacion nuevaCotizacion(Cotizacion cotizacion);

	/**
	 * @generated
	 */
	void emitirCredito(Long idCotizacion);
}