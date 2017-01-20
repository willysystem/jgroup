package com.jgroup.creditos.client;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
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
import com.google.gwt.view.client.HasRows;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.jgroup.creditos.mensajes.MensageConfirmacion;
import com.jgroup.creditos.mensajes.MensageError;
import com.jgroup.creditos.mensajes.MensageExito;
import com.jgroup.creditos.model.Cotizacion;
import com.jgroup.creditos.model.PlanPagosCotizacion;
import com.jgroup.creditos.view.CotizacionVista;

public class BusquedaCotizacion extends DialogBox {
	
	private CotizacionVista cotizacionVista;
	
	private Cotizacion cotizacionSeleccinada;
	
	private DataGrid<Cotizacion> dataGrid;
	
	private String buscarTexto;
	
	private MensageConfirmacion mensageConfirmacion;
	
	public BusquedaCotizacion(CotizacionVista cotizacionVista, String buscarTexto) {
		super();
		this.cotizacionVista = cotizacionVista;
		this.buscarTexto = buscarTexto;
		
		setGlassEnabled(true);
		setAnimationEnabled(false);
		setText("Elija una Cotización");
		
		final SingleSelectionModel<Cotizacion> selectionModel = new SingleSelectionModel<Cotizacion>();
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				cotizacionSeleccinada = selectionModel.getSelectedObject();
			}
		});
		
		dataGrid = new DataGrid<Cotizacion>();
		dataGrid.setEmptyTableWidget(new Label("Sin Datos"));
		dataGrid.setSelectionModel(selectionModel);
		
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		SimplePager pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		pager.setPageSize(10);
		pager.setDisplay(dataGrid);
		
				
		TextColumn<Cotizacion> nroCotizacion = new TextColumn<Cotizacion>() {
		   @Override
		   public String getValue(Cotizacion object) {
			    return ""+object.getNroCotizacion();
		   }};
		dataGrid.addColumn(nroCotizacion, "Nro Cotización");
		dataGrid.setRowCount(10);
		
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
						BusquedaCotizacion.this.cotizacionVista.setCotizacion(item);
						BusquedaCotizacion.this.hide();
					}
					@Override
					public void onFailure(Throwable caught) {
						
					}
				});
		    }    
		}, DoubleClickEvent.getType());
		pager.setVisible(true);
		
		VerticalPanel verticalPanel2 = new VerticalPanel();
		verticalPanel2.setSize("100%", "100%");
		verticalPanel2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel2.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		SimpleLayoutPanel slp = new SimpleLayoutPanel();
		slp.setSize("500px", "400px");
		slp.add(dataGrid);
		verticalPanel2.add(slp);
		verticalPanel2.add(pager);
		
		HorizontalPanel horizontalPanel2 = new HorizontalPanel();
		horizontalPanel2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		horizontalPanel2.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		Button eliminarButton = new Button("Eliminar");
		eliminarButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(cotizacionSeleccinada == null){
					new MensageError("Requiere elegir una cotización");
					return;
				}
				mensageConfirmacion = new MensageConfirmacion("¿Esta seguro de borrar esta cotización: " + cotizacionSeleccinada.getNroCotizacion() + " ?", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						CotizacionService.Util.getInstance().eliminarCotizacion(cotizacionSeleccinada.getId(), new AsyncCallback<Void>() {
							@Override
							public void onFailure(Throwable caught) {
								new MensageError(caught.getMessage()).show();;
							}
							@Override
							public void onSuccess(Void result) {
								cargarDatos();
								new MensageExito("Eliminado Exitosamente").show();
								mensageConfirmacion.hide();
							}
						});
					}
				});
			}
		});
		
		Button cancelarButton = new Button("Cancelar");
		cancelarButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				BusquedaCotizacion.this.hide();
			}
		});
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.add(eliminarButton);
		horizontalPanel.add(cancelarButton);
		
		verticalPanel2.add(horizontalPanel);
	
		setWidget(verticalPanel2);
		
		cargarDatos();
		
		center();
		
	}
	
	private void cargarDatos(){
		CotizacionService.Util.getInstance().buscarCotizacion(buscarTexto, new AsyncCallback<List<Cotizacion>>() {
			@Override
			public void onFailure(Throwable caught) {
				new MensageError(caught.getMessage()).show();
			}
			@Override
			public void onSuccess(List<Cotizacion> result) {
				dataGrid.setRowData(result);
			}
		});
	}
}
