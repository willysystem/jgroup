package com.jgroup.creditos.mensajes;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MensageError extends DialogBox {
	
	public MensageError(String mensaje){
		super();
		setGlassEnabled(true);
		setAnimationEnabled(false);
		setText("Mensaje de Error");
		
		//
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		TextArea label = new TextArea();
		label.setValue(mensaje);
		label.setPixelSize(250, 50);
		verticalPanel.add(label);
		
		Button aceptarButton = new Button("Aceptar");
		aceptarButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MensageError.this.hide();
			}
		});
		verticalPanel.add(aceptarButton);
		
		setWidget(verticalPanel);
		center();
	} 
	
}
