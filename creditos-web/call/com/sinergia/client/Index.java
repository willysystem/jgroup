package com.sinergia.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.RootPanel;

public class Index implements EntryPoint {

	@Override
	public void onModuleLoad() {
		Frame frame = new Frame();
		frame.setUrl("http://localhost:8090/creditos-web/Creditos_web.html");
		frame.setSize("1000px", "650px");
		RootPanel.get().add(frame);

	}

}
