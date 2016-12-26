package com.jgroup.creditos.model;


/**
 * @generated
 */
public class CotizacionPersistentTest extends junit.framework.TestCase {
	/**
	 * @generated
	 */
	private com.jgroup.creditos.util.HibernateHelper persistenceHelper;

	/**
	 * @generated
	 */
	public Banco banco;

	/**
	 * @generated
	 */
	public Banco banco1;

	/**
	 * @generated
	 */
	public Banco banco2;

	/**
	 * @generated
	 */
	public Cotizacion cotizacion1;

	/**
	 * @generated
	 */
	public PlanPagosCotizacion planPagosCotizacion;

	/**
	 * @generated
	 */
	public PlanPagosCotizacion planPagosCotizacion1;

	/**
	 * @generated
	 */
	public PlanPagosCotizacion planPagosCotizacion2;

	/**
	 * @generated
	 */
	public CotizacionPersistentTest() {
		persistenceHelper = com.jgroup.creditos.util.HibernateHelper
				.getInstance();
	}

	/**
	 * @generated
	 */
	private void initObjects() {
		javax.persistence.EntityManager entityManager = persistenceHelper
				.getEntityManager();
		javax.persistence.EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		banco = new com.jgroup.creditos.model.Banco();
		banco1 = new com.jgroup.creditos.model.Banco();
		banco2 = new com.jgroup.creditos.model.Banco();
		cotizacion1 = new com.jgroup.creditos.model.Cotizacion();
		planPagosCotizacion = new com.jgroup.creditos.model.PlanPagosCotizacion();
		planPagosCotizacion1 = new com.jgroup.creditos.model.PlanPagosCotizacion();
		planPagosCotizacion2 = new com.jgroup.creditos.model.PlanPagosCotizacion();
		banco.setNombre("BNB");
		banco.setTasaInteres(0.20f);
		banco.setPrimaDesgravamen(1.7f);
		banco1.setNombre("Mercantil Santa Cruz");
		banco1.setTasaInteres(0.19f);
		banco1.setPrimaDesgravamen(1.8f);
		banco2.setNombre("Bisa");
		banco2.setTasaInteres(0.18f);
		banco2.setPrimaDesgravamen(1.9f);
		cotizacion1.setEstado('P');
		cotizacion1.setNombreCompleto("Juan Perez");
		cotizacion1.setEdadActual(50);
		cotizacion1.setFechaNacimiento(new java.util.Date("00:00:00 1/2/1966"));
		cotizacion1.setIngresoBase(1800.0f);
		cotizacion1.setNroCuotas(24);
		cotizacion1.setCapacidadPago(0.25f);
		cotizacion1.setNroCotizacion("C001");
		cotizacion1.setFechaCotizacion(new java.util.Date());
		cotizacion1.setMontoPrestamo(10000.0f);
		cotizacion1.setMontoBaseCouta(450.0f);
		cotizacion1.setDocumentoIdentidad("512454578 PL");
		planPagosCotizacion.setNroCuota(1);
		planPagosCotizacion.setFechaVencimiento(new java.util.Date(
				"00:00:00 1/2/2017"));
		planPagosCotizacion.setMontoCapital(283.33f);
		planPagosCotizacion.setInteres(166.67f);
		planPagosCotizacion.setPrimaDesgravamen(1.7f);
		planPagosCotizacion.setTotalCuota(451.7f);
		planPagosCotizacion.setSaldoCapital(9716.67f);
		planPagosCotizacion1.setNroCuota(2);
		planPagosCotizacion1.setFechaVencimiento(new java.util.Date(
				"00:00:00 1/3/2017"));
		planPagosCotizacion1.setMontoCapital(288.06f);
		planPagosCotizacion1.setInteres(161.94f);
		planPagosCotizacion1.setPrimaDesgravamen(1.7f);
		planPagosCotizacion1.setTotalCuota(451.54f);
		planPagosCotizacion1.setSaldoCapital(9428.61f);
		planPagosCotizacion2.setNroCuota(3);
		planPagosCotizacion2.setFechaVencimiento(new java.util.Date(
				"00:00:00 1/4/2017"));
		planPagosCotizacion2.setMontoCapital(292.86f);
		planPagosCotizacion2.setInteres(157.14f);
		planPagosCotizacion2.setPrimaDesgravamen(1.7f);
		planPagosCotizacion2.setTotalCuota(451.7f);
		planPagosCotizacion2.setSaldoCapital(9135.75f);
		entityManager.persist(banco);
		entityManager.persist(banco1);
		entityManager.persist(banco2);
		entityManager.persist(cotizacion1);
		entityManager.persist(planPagosCotizacion);
		entityManager.persist(planPagosCotizacion1);
		entityManager.persist(planPagosCotizacion2);
		cotizacion1.setBanco(banco);
		banco.getCotizacion().add(cotizacion1);
		planPagosCotizacion.setCotizacion(cotizacion1);
		cotizacion1.getPlanPagosCotizacion().add(planPagosCotizacion);
		planPagosCotizacion1.setCotizacion(cotizacion1);
		cotizacion1.getPlanPagosCotizacion().add(planPagosCotizacion1);
		planPagosCotizacion2.setCotizacion(cotizacion1);
		cotizacion1.getPlanPagosCotizacion().add(planPagosCotizacion2);
		tx.commit();
		entityManager.close();
	}

	/**
	 * @generated
	 */
	protected void tearDown() throws Exception {
		if (persistenceHelper != null) {
			persistenceHelper.close();
		}
		super.tearDown();
	}

	/**
	 * @generated
	 */
	protected void setUp() throws Exception {
		super.setUp();
		initObjects();
	}

	/**
	 * @generated
	 */
	public void test1() throws Exception {
	}

	/**
	 * @generated
	 */
	public String toString() {
		return "CotizacionPersistentTest";
	}
}