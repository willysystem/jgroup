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
	public PlanPagos planPagos;

	/**
	 * @generated
	 */
	public PlanPagos planPagos1;

	/**
	 * @generated
	 */
	public PlanPagos planPagos2;

	/**
	 * @generated
	 */
	public Cotizacion cotizacion1;

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
		planPagos = new com.jgroup.creditos.model.PlanPagos();
		planPagos1 = new com.jgroup.creditos.model.PlanPagos();
		planPagos2 = new com.jgroup.creditos.model.PlanPagos();
		cotizacion1 = new com.jgroup.creditos.model.Cotizacion();
		banco.setNombre("BNB");
		banco.setTasaInteres(0.20f);
		banco.setPrimaDesgravamen(1.7f);
		banco1.setNombre("Mercantil Santa Cruz");
		banco1.setTasaInteres(0.19f);
		banco1.setPrimaDesgravamen(1.8f);
		banco2.setNombre("Bisa");
		banco2.setTasaInteres(0.18f);
		banco2.setPrimaDesgravamen(1.9f);
		planPagos.setNroCuota(1);
		planPagos.setFechaVencimiento(new java.util.Date("00:00:00 1/2/2017"));
		planPagos.setMontoCapital(283.33f);
		planPagos.setInteres(166.67f);
		planPagos.setPrimaDesgravamen(1.7f);
		planPagos.setTotalCuota(451.7f);
		planPagos.setSaldoCapital(9716.67f);
		planPagos1.setNroCuota(2);
		planPagos1.setFechaVencimiento(new java.util.Date("00:00:00 1/3/2017"));
		planPagos1.setMontoCapital(288.06f);
		planPagos1.setInteres(161.94f);
		planPagos1.setPrimaDesgravamen(1.7f);
		planPagos1.setTotalCuota(451.54f);
		planPagos1.setSaldoCapital(9428.61f);
		planPagos2.setNroCuota(3);
		planPagos2.setFechaVencimiento(new java.util.Date("00:00:00 1/4/2017"));
		planPagos2.setMontoCapital(292.86f);
		planPagos2.setInteres(157.14f);
		planPagos2.setPrimaDesgravamen(1.7f);
		planPagos2.setTotalCuota(451.7f);
		planPagos2.setSaldoCapital(9135.75f);
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
		entityManager.persist(banco);
		entityManager.persist(banco1);
		entityManager.persist(banco2);
		entityManager.persist(planPagos);
		entityManager.persist(planPagos1);
		entityManager.persist(planPagos2);
		entityManager.persist(cotizacion1);
		cotizacion1.setBanco(banco);
		banco.getCotizacion().add(cotizacion1);
		cotizacion1.getPlanesPagos().add(planPagos);
		planPagos.setPrestamo(cotizacion1);
		cotizacion1.getPlanesPagos().add(planPagos1);
		planPagos1.setPrestamo(cotizacion1);
		cotizacion1.getPlanesPagos().add(planPagos2);
		planPagos2.setPrestamo(cotizacion1);
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