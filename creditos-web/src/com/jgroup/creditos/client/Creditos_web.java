package com.jgroup.creditos.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Creditos_web implements EntryPoint {
	
	public void onModuleLoad() {
		
	    TabLayoutPanel tabPanel = new TabLayoutPanel(2.5, Unit.EM);
	    tabPanel.setSize("1000px", "1000px");
	    tabPanel.getElement().getStyle().setMarginBottom(10.0, Unit.PX);

	    String[] tabTitles = {"Cotización", "Crédito"};
	    tabPanel.add(new CotizacionVista(), tabTitles[0]);
	    tabPanel.add(new CreditosVista(), tabTitles[1]);

	    // Return the content
	    tabPanel.selectTab(0);
	    tabPanel.ensureDebugId("cwTabPanel");
	    
	    RootPanel.get().add(tabPanel);

	}
}
