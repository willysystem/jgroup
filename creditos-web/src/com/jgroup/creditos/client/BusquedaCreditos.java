package com.jgroup.creditos.client;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jgroup.creditos.model.Contrato;

public class BusquedaCreditos extends DialogBox {
	
	private CreditosVista creditosVista;
	
	public BusquedaCreditos(CreditosVista creditosVista,  List<Contrato> Contratoes) {
		super();
		this.creditosVista = creditosVista;
		
		setGlassEnabled(true);
		setAnimationEnabled(false);
		setText("Elija una Crédito");
		
		
		DataGrid<Contrato> dataGrid = new DataGrid<Contrato>();
		dataGrid.setEmptyTableWidget(new Label("Sin Datos"));
		
		TextColumn<Contrato> nroPrestamo = new TextColumn<Contrato>() {
		   @Override
		   public String getValue(Contrato object) {
			    return ""+object.getNroPrestamo();
		   }};
		dataGrid.addColumn(nroPrestamo, "Nro Prestamo");
		
		TextColumn<Contrato> nombre = new TextColumn<Contrato>() {
		   @Override
		   public String getValue(Contrato object) {
			    return ""+object.getNombreCompleto();
		   }};
		dataGrid.addColumn(nombre, "Nombre");
		
		TextColumn<Contrato> documentoIdentidad = new TextColumn<Contrato>() {
		   @Override
		   public String getValue(Contrato object) {
			    return ""+object.getDocumentoIdentidad();
		   }};
		dataGrid.addColumn(documentoIdentidad, "Documento Identidad");
		dataGrid.setRowCount(0, true);
		dataGrid.setRowData(Contratoes);
		
		dataGrid.addDomHandler(new DoubleClickHandler() {
		    @SuppressWarnings("unchecked")
		    @Override
		    public void onDoubleClick(DoubleClickEvent event) {
		        DataGrid<Contrato> grid = (DataGrid<Contrato>) event.getSource();
		        int row = grid.getKeyboardSelectedRow();
		        final Contrato item = grid.getVisibleItem(row);
		        GWT.log("Contrato: " + item);
		        ContratoService.Util.getInstance().getContrato(item.getId(), new AsyncCallback<Contrato>() {
					@Override
					public void onFailure(Throwable caught) {
						
					}
					@Override
					public void onSuccess(Contrato result) { 
						BusquedaCreditos.this.creditosVista.setContrato(result);
						BusquedaCreditos.this.hide();
					}
				});
		    }    
		}, DoubleClickEvent.getType());
		
		VerticalPanel verticalPanel2 = new VerticalPanel();
		verticalPanel2.setSize("100%", "100%");
		verticalPanel2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel2.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		SimpleLayoutPanel slp = new SimpleLayoutPanel();
		slp.setSize("500px", "400px");
		slp.add(dataGrid);
		verticalPanel2.add(slp);
		
		HorizontalPanel horizontalPanel2 = new HorizontalPanel();
		horizontalPanel2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		horizontalPanel2.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		Button cancelarButton = new Button("Cancelar");
		cancelarButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				BusquedaCreditos.this.hide();
			}
		});
		verticalPanel2.add(cancelarButton);
		setWidget(verticalPanel2);
		
		center();
		
	}
}
