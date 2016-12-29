package com.jgroup.creditos.client;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jgroup.creditos.model.Cotizacion;
import com.jgroup.creditos.model.PlanPagosCotizacion;

public class Busqueda extends DialogBox {
	
	private CotizacionVista cotizacionVista;
	
	public Busqueda(CotizacionVista cotizacionVista,  List<Cotizacion> cotizaciones) {
		super();
		this.cotizacionVista = cotizacionVista;
		
		setGlassEnabled(true);
		setAnimationEnabled(false);
		setText("Elija una Cotización");
		
		
		DataGrid<Cotizacion> dataGrid = new DataGrid<Cotizacion>();
		dataGrid.setEmptyTableWidget(new Label("Sin Datos"));
		
		TextColumn<Cotizacion> nroCotizacion = new TextColumn<Cotizacion>() {
		   @Override
		   public String getValue(Cotizacion object) {
			    return ""+object.getNroCotizacion();
		   }};
		dataGrid.addColumn(nroCotizacion, "Nro Cotización");
		
		TextColumn<Cotizacion> nombre = new TextColumn<Cotizacion>() {
		   @Override
		   public String getValue(Cotizacion object) {
			    return ""+object.getNombreCompleto();
		   }};
		dataGrid.addColumn(nombre, "Nombre");
		
		TextColumn<Cotizacion> documentoIdentidad = new TextColumn<Cotizacion>() {
		   @Override
		   public String getValue(Cotizacion object) {
			    return ""+object.getDocumentoIdentidad();
		   }};
		dataGrid.addColumn(documentoIdentidad, "Documento Identidad");
		dataGrid.setRowCount(0, true);
		dataGrid.setRowData(cotizaciones);
		
		dataGrid.addDomHandler(new DoubleClickHandler() {
		    @SuppressWarnings("unchecked")
		    @Override
		    public void onDoubleClick(DoubleClickEvent event) {
		        DataGrid<Cotizacion> grid = (DataGrid<Cotizacion>) event.getSource();
		        int row = grid.getKeyboardSelectedRow();
		        final Cotizacion item = grid.getVisibleItem(row);
		        GWT.log("cotizacion: " + item);
		        CotizacionService.Util.getInstance().getPlanPagosCotizacion(item.getId(), new AsyncCallback<List<PlanPagosCotizacion>>() {
					@Override
					public void onSuccess(List<PlanPagosCotizacion> result) {
						item.setPlanPagosCotizacion(result);
						Busqueda.this.cotizacionVista.setCotizacion(item);
						Busqueda.this.hide();
					}
					@Override
					public void onFailure(Throwable caught) {
						
					}
				});
		    }    
		}, DoubleClickEvent.getType());
		
		VerticalPanel verticalPanel2 = new VerticalPanel();
		verticalPanel2.setSize("100%", "100%");
		verticalPanel2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel2.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		SimpleLayoutPanel slp = new SimpleLayoutPanel();
		//slp.setWidth(width);
		slp.setSize("500px", "400px");
		//slp.setPixelSize(1000, 500);
		slp.add(dataGrid);
		verticalPanel2.add(slp);
		//verticalPanel2.add(dataGrid);
		
		HorizontalPanel horizontalPanel2 = new HorizontalPanel();
		horizontalPanel2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		horizontalPanel2.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		Button cancelarButton = new Button("Cancelar");
		cancelarButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Busqueda.this.hide();
			}
		});
		verticalPanel2.add(cancelarButton);
		//verticalPanel2.setSize("600px", "600px");
		setWidget(verticalPanel2);
		
		center();
		
	}
}
