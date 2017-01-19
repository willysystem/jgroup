package com.jgroup.creditos.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.jgroup.creditos.dao.ContratoDAO;
import com.jgroup.creditos.endpoint.ServicioCredito;
import com.jgroup.creditos.model.Banco;
import com.jgroup.creditos.model.Contrato;
import com.jgroup.creditos.model.PlanPagosContrato;

@Stateless
public class ServicioCreditoImpl implements ServicioCredito {

	@PersistenceContext(unitName = "PUnitCreditos")
	private EntityManager em;
	
	@EJB
	private ContratoDAO contratoDAO;

	@Override
	public List<Contrato> buscarCredito(String nroDocumento) {
		Query query = null;
		if (nroDocumento.trim().trim().equals("")) {
			query = em.createQuery("SELECT c FROM Contrato c ");
		} else {
			query = em.createQuery("SELECT c FROM Contrato c WHERE c.documentoIdentidad LIKE :documentoIdentidad");
			query.setParameter("documentoIdentidad", "%" + nroDocumento + "%");
		}

		@SuppressWarnings("unchecked")
		List<Contrato> cotizacionesP = (List<Contrato>) query.getResultList();
		List<Contrato> cotizacionesDTO = new ArrayList<Contrato>();
		Contrato cotizacionDTO = null;
		for (Contrato cotizacionP : cotizacionesP) {
			cotizacionDTO = new Contrato();
			cotizacionDTO.setId(cotizacionP.getId());
			cotizacionDTO.setNombreCompleto(cotizacionP.getNombreCompleto());
			cotizacionDTO.setCapacidadPago(cotizacionP.getCapacidadPago());
			cotizacionDTO.setEdadActual(cotizacionP.getEdadActual());
			cotizacionDTO.setNroPrestamo(cotizacionP.getNroPrestamo());
			cotizacionDTO.setFechaNacimiento(cotizacionP.getFechaNacimiento());
			cotizacionDTO.setFechaEmision(cotizacionP.getFechaEmision());
			cotizacionDTO.setIngresoBase(cotizacionP.getIngresoBase());
			cotizacionDTO.setMontoBaseCouta(cotizacionP.getMontoBaseCouta());
			cotizacionDTO.setNroCuotas(cotizacionP.getNroCuotas());
			cotizacionDTO.setMontoPrestamo(cotizacionP.getMontoPrestamo());
			if (cotizacionP.getBanco() != null) {
				Banco bancoDTO = new Banco();
				bancoDTO.setId(cotizacionP.getBanco().getId());
				bancoDTO.setNombre(cotizacionP.getBanco().getNombre());
				cotizacionDTO.setBanco(bancoDTO);
			}
			cotizacionDTO.setDocumentoIdentidad(cotizacionP.getDocumentoIdentidad());
			cotizacionesDTO.add(cotizacionDTO);
		}

		// Serializacion
		return cotizacionesDTO;
	}
	
	@Override
	public Contrato getContrato(Long contratoId) {
		
		Contrato contratoP = em.find(Contrato.class, contratoId);
		
		Contrato contrato = new Contrato();
		contrato.setId(contratoP.getId());
		contrato.setNombreCompleto(contratoP.getNombreCompleto());
		contrato.setCapacidadPago(contratoP.getCapacidadPago());
		contrato.setEdadActual(contratoP.getEdadActual());
		contrato.setNroCotizacion(contratoP.getNroCotizacion());
		contrato.setFechaNacimiento(contratoP.getFechaNacimiento());
		contrato.setFechaCotizacion(contratoP.getFechaCotizacion());
		contrato.setIngresoBase(contratoP.getIngresoBase());
		contrato.setMontoBaseCouta(contratoP.getMontoBaseCouta());
		contrato.setNroCuotas(contratoP.getNroCuotas());
		contrato.setMontoPrestamo(contratoP.getMontoPrestamo());
		contrato.setDocumentoIdentidad(contratoP.getDocumentoIdentidad());
		contrato.setFechaEmision(contratoP.getFechaEmision());
		Banco banco =  new Banco();
		banco.setId(contratoP.getBanco().getId());
		banco.setNombre(contratoP.getBanco().getNombre());
		contrato.setBanco(banco);
		contrato.setFechaLiquidacion(contratoP.getFechaLiquidacion());
		contrato.setNroPrestamo(contratoP.getNroPrestamo());
		
		List<PlanPagosContrato> planes = contratoP.getPlanPagosCotnrato();
		List<PlanPagosContrato> planesDTO = new ArrayList<PlanPagosContrato>();
		for (PlanPagosContrato pP : planes) {
			PlanPagosContrato pDTO = new PlanPagosContrato();
			pDTO.setId(pP.getId());
			pDTO.setNroCuota(pP.getNroCuota());
			pDTO.setMontoCapital(pP.getMontoCapital());
			pDTO.setInteres(pP.getInteres());
			pDTO.setPrimaDesgravamen(pP.getPrimaDesgravamen());
			pDTO.setTotalCuota(pP.getTotalCuota());
			pDTO.setFechaVencimiento(pP.getFechaVencimiento());
			pDTO.setSaldoCapital(pP.getSaldoCapital());
			pDTO.setFechaPago(pP.getFechaPago());
			pDTO.setNroRecibo(pP.getNroRecibo());
			planesDTO.add(pDTO);
		}
		contrato.setPlanPagosCotnrato(planesDTO);
		
		return contrato;
	}

	@Override
	public void modificarCredito(Long contratoId, Date fechaEmision, Date fechaLiquidacion, String nroPrestamo) throws Exception {
		Contrato contrato = em.find(Contrato.class, contratoId);
		contrato.setFechaEmision(fechaEmision);
		contrato.setFechaLiquidacion(fechaLiquidacion);
		contrato.setNroPrestamo(nroPrestamo);
		contratoDAO.merge(contrato);
	}

	@Override
	public void modificarPlanPagosCredito(Long planPagosId, Date fechaPago, String nroRecibo) throws Exception {
		PlanPagosContrato planPagosContrato = em.find(PlanPagosContrato.class, planPagosId);
		planPagosContrato.setFechaPago(fechaPago);
		planPagosContrato.setNroRecibo(nroRecibo);
	}
}
