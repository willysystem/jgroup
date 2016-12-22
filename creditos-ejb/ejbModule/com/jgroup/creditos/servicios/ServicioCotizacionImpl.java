package com.jgroup.creditos.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.jgroup.creditos.endpoint.ServicioCotizacion;
import com.jgroup.creditos.model.Banco;
import com.jgroup.creditos.model.Cotizacion;


@Stateless
public class ServicioCotizacionImpl implements ServicioCotizacion {

	@PersistenceContext(unitName="PUnitCreditos")
	private EntityManager em;

	@Override
	public List<Cotizacion> buscarCotizacion(String nroDocumento) {
		Query query = em.createQuery("SELECT c FROM Cotizacion c WHERE c.documentoIdentidad LIKE :documentoIdentidad");
		query.setParameter("documentoIdentidad", "%"+nroDocumento+"%");
		
		@SuppressWarnings("unchecked")
		List<Cotizacion> cotizacionesP = (List<Cotizacion>)query.getResultList();
		List<Cotizacion> cotizacionesDTO = new ArrayList<Cotizacion>();
		Cotizacion cotizacionDTO = null;
		for (Cotizacion cotizacionP : cotizacionesP) {
			cotizacionDTO = new Cotizacion();
			cotizacionDTO.setNombreCompleto(cotizacionP.getNombreCompleto());
			cotizacionDTO.setEdadActual(cotizacionP.getEdadActual());
			cotizacionDTO.setFechaNacimiento(cotizacionP.getFechaNacimiento());
			cotizacionesDTO.add(cotizacionDTO);
		}		
		
		// Serializacion
		return cotizacionesDTO;
	}

	@Override
	public Cotizacion getCotizacion(String nroCotizacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cotizacion nuevaCotizacion(Cotizacion cotizacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void emitirCredito(Long idCotizacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Banco> getBancos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
