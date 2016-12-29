package com.jgroup.creditos.mensajes;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MensageConfirmacion extends DialogBox {
	
	public MensageConfirmacion(String mensaje){
		super();
		setGlassEnabled(true);
		setAnimationEnabled(false);
		setText("Mensaje");
		
		//
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		Label label = new Label(mensaje);
		label.setPixelSize(200, 100);
		verticalPanel.add(label);
		
		
		HorizontalPanel horizontalPanel2 = new HorizontalPanel();
		horizontalPanel2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		horizontalPanel2.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		Button aceptarButton = new Button("Aceptar");
		aceptarButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MensageConfirmacion.this.hide();
			}
		});
		horizontalPanel2.add(aceptarButton);
		
		Button aceptarCancelar = new Button("Cancelar");
		aceptarCancelar.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MensageConfirmacion.this.hide();
			}
		});
		horizontalPanel2.add(aceptarCancelar);
		
		verticalPanel.add(horizontalPanel2);
		
		setWidget(verticalPanel);
		center();
	} 
	
}
