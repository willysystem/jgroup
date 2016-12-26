package com.jgroup.creditos.pdf;

import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;

public class PlanPagosPDF {

	public PlanPagosPDF() {
		ScriptInjector.fromUrl("library/jspdf.min.js").setCallback(new Callback<Void, Exception>() {
			public void onFailure(Exception reason) {
				GWT.log("jsPDF fallo al cargarse");
				
			}
			public void onSuccess(Void result) {
				GWT.log("jsPDF cargado");
			}
		}).inject();
	}    

	public native void generarPDF() /*-{
		var doc = new jsPDF();
		alert("" + doc);
		doc.text(20, 20, 'Hello world.');
		
		doc.save('Test.pdf');
		
	}-*/;
}
