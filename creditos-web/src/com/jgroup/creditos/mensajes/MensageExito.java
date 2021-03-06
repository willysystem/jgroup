package com.jgroup.creditos.mensajes;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MensageExito extends DialogBox {
	
	public MensageExito(String mensaje){
		super();
		setGlassEnabled(true);
		setAnimationEnabled(false);
		setText("Mensaje");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		Label label = new Label();
		label.setText(mensaje);
		label.setPixelSize(250, 50);
		verticalPanel.add(label);
		
		Button aceptarButton = new Button("Aceptar");
		aceptarButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MensageExito.this.hide();
			}
		});
		verticalPanel.add(aceptarButton);
		
		setWidget(verticalPanel);
		center();
	} 
	
}
