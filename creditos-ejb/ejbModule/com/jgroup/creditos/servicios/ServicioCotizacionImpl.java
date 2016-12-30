package com.jgroup.creditos.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.jgroup.creditos.endpoint.ServicioCotizacion;
import com.jgroup.creditos.finance.FinanceCalculator;
import com.jgroup.creditos.model.Banco;
import com.jgroup.creditos.model.Contrato;
import com.jgroup.creditos.model.Cotizacion;
import com.jgroup.creditos.model.PlanPagosContrato;
import com.jgroup.creditos.model.PlanPagosCotizacion;
import com.jgroup.creditos.utils.Tools;

@Stateless
public class ServicioCotizacionImpl implements ServicioCotizacion {

	@PersistenceContext(unitName = "PUnitCreditos")
	private EntityManager em;

	@Override
	public List<Cotizacion> buscarCotizacion(String nroDocumento) {
		Query query = null;
		if (nroDocumento.trim().trim().equals("")) {
			query = em.createQuery("SELECT c FROM Cotizacion c ");
		} else {
			query = em.createQuery("SELECT c FROM Cotizacion c WHERE c.documentoIdentidad LIKE :documentoIdentidad");
			query.setParameter("documentoIdentidad", "%" + nroDocumento + "%");
		}

		@SuppressWarnings("unchecked")
		List<Cotizacion> cotizacionesP = (List<Cotizacion>) query.getResultList();
		List<Cotizacion> cotizacionesDTO = new ArrayList<Cotizacion>();
		Cotizacion cotizacionDTO = null;
		for (Cotizacion cotizacionP : cotizacionesP) {
			cotizacionDTO = new Cotizacion();
			cotizacionDTO.setId(cotizacionP.getId());
			cotizacionDTO.setNombreCompleto(cotizacionP.getNombreCompleto());
			cotizacionDTO.setCapacidadPago(cotizacionP.getCapacidadPago());
			cotizacionDTO.setEdadActual(cotizacionP.getEdadActual());
			cotizacionDTO.setNroCotizacion(cotizacionP.getNroCotizacion());
			cotizacionDTO.setFechaNacimiento(cotizacionP.getFechaNacimiento());
			cotizacionDTO.setFechaCotizacion(cotizacionP.getFechaCotizacion());
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
	public Cotizacion getCotizacion(String nroCotizacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cotizacion tarifarCotizacion(Cotizacion cotizacion) throws Exception {
		// Previamente Borrar
		if (cotizacion.getId() != null) {
			for (PlanPagosCotizacion plan : cotizacion.getPlanPagosCotizacion()) {
				plan.setCotizacion(null);
				plan = em.merge(plan);
				em.remove(plan);
			}
		}
		Cotizacion cotizacionP = calcularPrestamo(cotizacion);

		cotizacionP = em.merge(cotizacionP);

		// Cotizacion
		Cotizacion cotizacionDTO = new Cotizacion();
		cotizacionDTO.setId(cotizacionP.getId());
		cotizacionDTO.setNombreCompleto(cotizacionP.getNombreCompleto());
		cotizacionDTO.setCapacidadPago(cotizacionP.getCapacidadPago());
		cotizacionDTO.setEdadActual(cotizacionP.getEdadActual());
		cotizacionDTO.setNroCotizacion(cotizacionP.getNroCotizacion());
		cotizacionDTO.setFechaNacimiento(cotizacionP.getFechaNacimiento());
		cotizacionDTO.setFechaCotizacion(cotizacionP.getFechaCotizacion());
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
		// Plan de pagos
		List<PlanPagosCotizacion> planes = cotizacionP.getPlanPagosCotizacion();
		List<PlanPagosCotizacion> planesDTO = new ArrayList<PlanPagosCotizacion>();
		for (PlanPagosCotizacion pP : planes) {
			PlanPagosCotizacion pDTO = new PlanPagosCotizacion();
			pDTO.setId(pP.getId());
			pDTO.setNroCuota(pP.getNroCuota());
			pDTO.setMontoCapital(pP.getMontoCapital());
			pDTO.setInteres(pP.getInteres());
			pDTO.setPrimaDesgravamen(pP.getPrimaDesgravamen());
			pDTO.setTotalCuota(pP.getTotalCuota());
			pDTO.setFechaVencimiento(pP.getFechaVencimiento());
			pDTO.setSaldoCapital(pP.getSaldoCapital());
			pDTO.setFechaPago(pP.getFechaPago());
			planesDTO.add(pDTO);
		}
		cotizacionDTO.setPlanPagosCotizacion(planesDTO);

		return cotizacionDTO;
	}

	@Override
	public void emitirCredito(Cotizacion cotizacionI) throws Exception {
		
		Long idCotizacion = cotizacionI.getId();
		Cotizacion cotiP = em.find(Cotizacion.class, idCotizacion);
		if(cotiP.getEstado()!=null && cotiP.getEstado() != 'O'){
			throw new Exception("No ha sido validado la cotización");
		}
		String nroPrestamo = "PRE" + Tools.getInstance().date2String(new Date());
		Contrato contrato = new Contrato();
		contrato.setNroPrestamo(nroPrestamo);
		contrato.setFechaEmision(new Date());
        
		Cotizacion cotizacion = calcularPrestamo(cotiP);
		contrato.setNombreCompleto(cotizacion.getNombreCompleto());
		contrato.setCapacidadPago(cotizacion.getCapacidadPago());
		contrato.setEdadActual(cotizacion.getEdadActual());
		contrato.setNroCotizacion(cotizacion.getNroCotizacion());
		contrato.setFechaNacimiento(cotizacion.getFechaNacimiento());
		contrato.setFechaCotizacion(cotizacion.getFechaCotizacion());
		contrato.setIngresoBase(cotizacion.getIngresoBase());
		contrato.setMontoBaseCouta(cotizacion.getMontoBaseCouta());
		contrato.setNroCuotas(cotizacion.getNroCuotas());
		contrato.setMontoPrestamo(cotizacion.getMontoPrestamo());
		Banco banco =  new Banco();
		banco.setId(cotizacion.getBanco().getId());
		contrato.setBanco(banco);
		contrato.setDocumentoIdentidad(cotizacion.getDocumentoIdentidad());
		List<PlanPagosContrato> planesContrato = new ArrayList<PlanPagosContrato>(); 
		for (PlanPagosCotizacion planCoti : cotizacion.getPlanPagosCotizacion()) {
			PlanPagosContrato plan = new PlanPagosContrato();
			plan.setId(planCoti.getId());
			plan.setNroCuota(planCoti.getNroCuota());
			plan.setMontoCapital(planCoti.getMontoCapital());
			plan.setInteres(planCoti.getInteres());
			plan.setPrimaDesgravamen(planCoti.getPrimaDesgravamen());
			plan.setTotalCuota(planCoti.getTotalCuota());
			plan.setFechaVencimiento(planCoti.getFechaVencimiento());
			plan.setSaldoCapital(planCoti.getSaldoCapital());
			plan.setFechaPago(planCoti.getFechaPago());
			plan.setContrato(contrato);
			planesContrato.add(plan);
		}
		contrato.setPlanPagosCotnrato(planesContrato);
		em.persist(contrato);
		
		// Borrar cotizacion
		for (PlanPagosCotizacion plan : cotizacionI.getPlanPagosCotizacion()) {
			plan.setCotizacion(null);
			plan = em.merge(plan);
			em.remove(plan);
		}
		cotiP.setPlanPagosCotizacion(new ArrayList<PlanPagosCotizacion>());
		em.remove(cotiP);
		
	}

	@Override
	public List<Banco> getBancos() {
		Query query = em.createQuery("SELECT b FROM Banco b", Banco.class);
		@SuppressWarnings("unchecked")
		List<Banco> bancosP = query.getResultList();
		List<Banco> bancosDTO = new ArrayList<Banco>();
		for (Banco bancoP : bancosP) {
			Banco bancoDTO = new Banco();
			bancoDTO.setId(bancoP.getId());
			bancoDTO.setNombre(bancoP.getNombre());
			bancoDTO.setPrimaDesgravamen(bancoP.getPrimaDesgravamen());
			bancoDTO.setTasaInteres(bancoP.getTasaInteres());
			bancosDTO.add(bancoDTO);
		}
		return bancosDTO;
	}

	private Cotizacion calcularPrestamo(Cotizacion cotizacion) throws Exception {
		if (cotizacion.getNroCuotas() == 1) {
			throw new Exception("Número de cuotas no puede ser 1");
		}

		if (cotizacion.getNroCotizacion() == null) {
			cotizacion.setNroCotizacion("COT" + Tools.getInstance().date2String(new Date()));
		}
		int cuotas = cotizacion.getNroCuotas();
		double ingresoBase = cotizacion.getIngresoBase();
		double capacidadPago = cotizacion.getCapacidadPago();
		Double montoBaseCuota = ingresoBase * capacidadPago;
		cotizacion.setMontoBaseCouta(montoBaseCuota.floatValue());
		cotizacion.setFechaCotizacion(new Date());
		double tasaInteres = 0.18d;
		// DEfinir el monto de prestamo por VA = 0
		// Double montoPrestamo = montoBaseCuota * cuotas;
		Double montoPrestamo = FinanceCalculator.getInstance().calculateVA(ingresoBase, capacidadPago, tasaInteres, cuotas, 0);
		Double saldoCapital = montoPrestamo;
		List<PlanPagosCotizacion> planPagos = new ArrayList<PlanPagosCotizacion>();
		for (int quota = 1; quota <= cuotas; quota++) {
			Double montoInteres = saldoCapital * (tasaInteres / 12);
			Double montoCapital = montoBaseCuota - montoInteres;
			Double primaDesgravamen = 1.21D;
			Double montoCuota = montoCapital + montoInteres + primaDesgravamen;
			saldoCapital = saldoCapital - montoCapital;
			PlanPagosCotizacion pago = new PlanPagosCotizacion();
			pago.setFechaVencimiento(Tools.addToDate(cotizacion.getFechaCotizacion(), 0, quota, 0));
			pago.setInteres(montoInteres.floatValue());
			pago.setPrimaDesgravamen(primaDesgravamen.floatValue());
			pago.setNroCuota(quota);
			pago.setSaldoCapital(saldoCapital.floatValue());
			pago.setTotalCuota(montoCuota.floatValue());
			pago.setMontoCapital(montoCapital.floatValue());
			pago.setCotizacion(cotizacion);
			planPagos.add(pago);
		}
		cotizacion.setPlanPagosCotizacion(planPagos);
		cotizacion.setMontoPrestamo(new Float(montoPrestamo));
		return cotizacion;
	}

	@Override
	public List<PlanPagosCotizacion> getPlanPagosCotizacion(Long cotizacionId) {
		Cotizacion cotizacion = em.find(Cotizacion.class, cotizacionId);
		List<PlanPagosCotizacion> planes = cotizacion.getPlanPagosCotizacion();
		List<PlanPagosCotizacion> planesDTO = new ArrayList<PlanPagosCotizacion>();
		for (PlanPagosCotizacion pP : planes) {
			PlanPagosCotizacion pDTO = new PlanPagosCotizacion();
			pDTO.setId(pP.getId());
			pDTO.setNroCuota(pP.getNroCuota());
			pDTO.setMontoCapital(pP.getMontoCapital());
			pDTO.setInteres(pP.getInteres());
			pDTO.setPrimaDesgravamen(pP.getPrimaDesgravamen());
			pDTO.setTotalCuota(pP.getTotalCuota());
			pDTO.setFechaVencimiento(pP.getFechaVencimiento());
			pDTO.setSaldoCapital(pP.getSaldoCapital());
			pDTO.setFechaPago(pP.getFechaPago());
			planesDTO.add(pDTO);
		}

		return planesDTO;
	}
}
