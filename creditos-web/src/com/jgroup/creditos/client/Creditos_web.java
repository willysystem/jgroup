package com.jgroup.creditos.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.jgroup.creditos.view.CotizacionVista;
import com.jgroup.creditos.view.CreditosVista;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Creditos_web implements EntryPoint {
	
	public void onModuleLoad() {
		
		
		
		DecoratedTabPanel tabPanel = new DecoratedTabPanel(/*2.5, Unit.EM*/);
	    
	    tabPanel.setPixelSize(Window.getClientWidth()-30, Window.getClientHeight()+100);
	    //tabPanel.getElement().getStyle().setMarginBottom(10.0, Unit.PX);

	    String[] tabTitles = {"Cotización", "Crédito"};
	    tabPanel.add(new CotizacionVista(), tabTitles[0]);
	    tabPanel.add(new CreditosVista(), tabTitles[1]);

	    // Return the content
	    tabPanel.selectTab(0);
	    tabPanel.ensureDebugId("cwTabPanel");
	    
	    RootPanel.get().add(tabPanel);

	}
}
