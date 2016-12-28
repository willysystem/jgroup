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
import com.jgroup.creditos.model.Cotizacion;
import com.jgroup.creditos.model.PlanPagosCotizacion;
import com.jgroup.creditos.utils.Tools;

@Stateless
public class ServicioCotizacionImpl implements ServicioCotizacion {

	@PersistenceContext(unitName = "PUnitCreditos")
	private EntityManager em;

	@Override
	public List<Cotizacion> buscarCotizacion(String nroDocumento) {
		Query query = null; 
		if(nroDocumento.trim().trim().equals("")){
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
			// TODO falta Banco
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
	public Cotizacion tarifarCotizacion(Cotizacion cotizacion) {

		return calcularPrestamo(cotizacion);
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

	private Cotizacion calcularPrestamo(Cotizacion cotizacion) {
		if (cotizacion.getNroCotizacion() != null) {
			cotizacion.setNroCotizacion("COT" + Tools.getInstance().date2String(cotizacion.getFechaCotizacion()));
		}
		int cuotas = cotizacion.getNroCuotas();
		double ingresoBase = cotizacion.getIngresoBase();
		double capacidadPago = cotizacion.getCapacidadPago();
		Double montoBaseCuota = ingresoBase * capacidadPago;
		cotizacion.setMontoBaseCouta(montoBaseCuota.floatValue());
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
			pago.setFechaPago(new Date());
			pago.setFechaVencimiento(new Date());
			pago.setInteres(montoInteres.floatValue());
			pago.setPrimaDesgravamen(primaDesgravamen.floatValue());
			pago.setNroCuota(quota);
			pago.setSaldoCapital(saldoCapital.floatValue());
			pago.setTotalCuota(montoCuota.floatValue());
			pago.setMontoCapital(montoCapital.floatValue());
			planPagos.add(pago);
		}
		cotizacion.setPlanPagosCotizacion(planPagos);
		return cotizacion;
	}

	@Override
	public List<PlanPagosCotizacion> getPlanPagosCotizacion(Long cotizacionId) {
		Cotizacion cotizacion = em.find(Cotizacion.class, cotizacionId);
		List<PlanPagosCotizacion> planes = cotizacion.getPlanPagosCotizacion();
		List<PlanPagosCotizacion>  planesDTO = new ArrayList<PlanPagosCotizacion>();
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
