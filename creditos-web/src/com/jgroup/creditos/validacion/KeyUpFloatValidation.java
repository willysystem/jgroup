package com.jgroup.creditos.validacion;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.TextBox;

public class KeyUpFloatValidation implements KeyUpHandler {

	private TextBox textBox;
	
	public KeyUpFloatValidation(TextBox textBox) {
		this.textBox = textBox;
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		String input = textBox.getText();
        if (!input.matches("[0-9]*\\.?[0-9]*")) {
        	String value = textBox.getValue();
        	value = value.substring(0, value.length()-1);
        	textBox.setValue(value);
        	textBox.cancelKey();
            return;
        }
	}
}
