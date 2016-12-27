package com.jgroup.creditos.pdf;

import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;

public class PlanPagosPDF {

	public PlanPagosPDF() {
		// jspdf
		ScriptInjector.fromUrl("library/jspdf.min.js").setCallback(new Callback<Void, Exception>() {
			public void onFailure(Exception reason) {
				GWT.log("jsPDF fallo al cargarse");
				
			}
			public void onSuccess(Void result) {
				GWT.log("jsPDF cargado");
			}
		}).inject();
		// harness 
		ScriptInjector.fromUrl("library/test_harness.js").setCallback(new Callback<Void, Exception>() {
			public void onFailure(Exception reason) {
				GWT.log("test_harness fallo al cargarse");
				
			}
			public void onSuccess(Void result) {
				GWT.log("test_harness cargado");
			}
		}).inject();
		// autotable 
		ScriptInjector.fromUrl("library/jspdf.plugin.autotable.src.js").setCallback(new Callback<Void, Exception>() {
			public void onFailure(Exception reason) {
				GWT.log("autotable fallo al cargarse");			
			}
			public void onSuccess(Void result) {
				GWT.log("autotable cargado");
			}
		}).inject();
		
		
	}    

	public native void generarPDF(String[] titulos, String[][] data) /*-{
		console.log("aqui 0");
		var titulos1 = ["ID", "Country", "Rank", "Capital"];
//        var data = [
//            [1, "Denmark", 7.526, "Copenhagen"],
//            [2, "Switzerland", 	7.509, "Bern"],
//            [3, "Iceland", 7.501, "Reykjavík"],
//            [4, "Norway", 7.498, "Oslo"],
//            [5, "Finland", 7.413, "Helsinki"]
//        ];
		console.log("aqui 1");
        var doc = new jsPDF('p', 'pt');
        console.log("aqui 2");
        doc.autoTable(titulos, data)
        console.log("aqui 3");
		doc.save("table.pdf");
		console.log("aqui 4");
		//pdf_test_harness_init(doc);
		
	}-*/;
}
