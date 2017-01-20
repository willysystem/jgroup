package com.jgroup.creditos.pdf;

public class PlanPagosPDF {

	public PlanPagosPDF() {
		// jspdf
//		ScriptInjector.fromUrl("library/jspdf.min.js").setCallback(new Callback<Void, Exception>() {
//			public void onFailure(Exception reason) {
//				GWT.log("jsPDF fallo al cargarse");
//				
//			}
//			public void onSuccess(Void result) {
//				GWT.log("jsPDF cargado");
//			}
//		}).inject();
		// harness 
//		ScriptInjector.fromUrl("library/test_harness.js").setCallback(new Callback<Void, Exception>() {
//			public void onFailure(Exception reason) {
//				GWT.log("test_harness fallo al cargarse");
//				
//			}
//			public void onSuccess(Void result) {
//				GWT.log("test_harness cargado");
//			}
//		}).inject();
		// autotable 
//		ScriptInjector.fromUrl("library/jspdf.plugin.autotable.js").setCallback(new Callback<Void, Exception>() {
//			public void onFailure(Exception reason) {
//				GWT.log("autotable fallo al cargarse");			
//			}
//			public void onSuccess(Void result) {
//				GWT.log("autotable cargado");
//			}
//		}).inject();
		
	}    

	public native void generarPDF(String[] titulos, String[][] data, String nombreCompleto, String capacidadPago,
			                                                         String edadActual, String nroCotizacion,
			                                                         String fechaNacimiento, String fechaCotizacion,
			                                                         String ingresoBase, String montoBaseCuota,
			                                                         String nroCuotas, String montoPrestamo, 
			                                                         String banco, String documentoIdentidad) /*-{
		
		console.log("aqui 1");
        var doc = new $wnd.jsPDF('p', 'pt');
        doc.text(235, 45, "PLAN DE PAGOS");
        console.log("aqui 2");
        doc.text('aaaaaaaa', 14);
        var titulos1 = ["", "", "", ""];
        var data1 = [
            ["Nombre Completo: " , nombreCompleto,  "Capacidad Pago: ",      capacidadPago],
            ["Edad Actual:"      , edadActual,      "Nro Cotización: ",      nroCotizacion],
            ["Fecha Nacimiento: ", fechaNacimiento, "Fecha de Cotización: ", fechaCotizacion],
            ["Ingreso Base: "    , ingresoBase,     "Monto Base: ",          montoBaseCuota],
            ["Nro Cuotas: "      , nroCuotas,       "Monto Prestamo: ",      montoPrestamo],
            ["Banco: "           , banco,           "Documento Identidad: ", documentoIdentidad]
        ];
        doc.autoTable(titulos1, data1, {theme: 'plain'});
        doc.autoTable(titulos, data, {startY: doc.autoTable.previous.finalY + 14, theme: 'grid'})
        console.log("aqui 3");
		doc.save("table.pdf");
		console.log("aqui 4");
		//$wnd.pdf_test_harness_init(doc,"un mensaje");
		
	}-*/;
}
