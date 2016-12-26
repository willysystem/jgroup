package com.jgroup.creditos.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Creditos_web implements EntryPoint {
	
	
	
	public void onModuleLoad() {
		
		// Create a tab panel
	    TabLayoutPanel tabPanel = new TabLayoutPanel(2.5, Unit.EM);
	    tabPanel.setSize("1000px", "1000px");
	    tabPanel.getElement().getStyle().setMarginBottom(10.0, Unit.PX);

	    // Add a home tab
	    String[] tabTitles = {"Cotización", "Crédito"};
	    tabPanel.add(new CotizacionVista(), tabTitles[0]);

	    // Add a tab with an image
	    SimplePanel imageContainer = new SimplePanel();
	    imageContainer.setWidget(new Button("gsdfgsdfg"));
	    tabPanel.add(imageContainer, tabTitles[1]);

	    // Return the content
	    tabPanel.selectTab(0);
	    tabPanel.ensureDebugId("cwTabPanel");
	    
	    RootPanel.get().add(tabPanel);

	}
}
