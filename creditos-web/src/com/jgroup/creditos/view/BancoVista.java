package com.jgroup.creditos.view;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.jgroup.creditos.client.CotizacionService;
import com.jgroup.creditos.mensajes.MensageError;
import com.jgroup.creditos.mensajes.MensageExito;
import com.jgroup.creditos.model.Banco;

public class BancoVista extends DialogBox {

	private CotizacionVista cotizacionVista;
	private DataGrid<Banco> dataGrid = null;
	private Banco bancoSeleccionado = null;
	
	private ListDataProvider<Banco> dataProvider = new ListDataProvider<Banco>();
	
	public BancoVista(CotizacionVista cotizacionVista) {
		
		super();
		this.cotizacionVista = cotizacionVista;
		setGlassEnabled(true);
		setAnimationEnabled(false);
		setText("Bancos");
		
		final SingleSelectionModel<Banco> selectionModel = new SingleSelectionModel<Banco>();
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				bancoSeleccionado = selectionModel.getSelectedObject();
			}
		});
		dataGrid = new DataGrid<Banco>();
		dataGrid.setWidth("500px");
		dataGrid.setHeight("300px");
		dataGrid.setEmptyTableWidget(new Label("Sin Datos"));
		dataGrid.setSelectionModel(selectionModel);
		
		ListHandler<Banco> sortHandler = new ListHandler<Banco>(dataProvider.getList());
		dataGrid.addColumnSortHandler(sortHandler);
		
		TextColumn<Banco> nombre = new TextColumn<Banco>() {
			@Override
			public String getValue(Banco object) {
				return "" + object.getNombre();
			}
		};
		dataGrid.setColumnWidth(0, "150px");
		dataGrid.addColumn(nombre, "Nombre");
		
		TextColumn<Banco> tasaInteres = new TextColumn<Banco>() {
			@Override
			public String getValue(Banco object) {
				String value = "";
				if (object.getTasaInteres() != null) {
					value = NumberFormat.getFormat("#.00").format(object.getTasaInteres());
				}
				return value;
			}
		};
		tasaInteres.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		dataGrid.setColumnWidth(1, "100px");
		dataGrid.addColumn(tasaInteres, "Interes");
		
		TextColumn<Banco> primaDesgravamen = new TextColumn<Banco>() {
			@Override
			public String getValue(Banco object) {
				String value = "";
				if (object.getTasaInteres() != null) {
					value = NumberFormat.getFormat("#.00").format(object.getPrimaDesgravamen());
				}
				return value;
			}
		};
		primaDesgravamen.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		dataGrid.setColumnWidth(2, "100px");
		dataGrid.addColumn(primaDesgravamen, "Prima Desgravamen");
		dataGrid.setRowCount(0, true);
		
		
		Button salirButton = new Button("Salir");
		salirButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				BancoVista.this.hide();	
			}
		});
		
		Button nuevoButton = new Button("Nuevo");
		nuevoButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				new BancoNuevoActualizarVista(BancoVista.this, TypeCRUD.NUEVO);	
			}
		});
		
		Button modificarButton = new Button("Modificar");
		modificarButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(bancoSeleccionado == null){
					new MensageError("Tiene que elegir un registro");
				} else {
					new BancoNuevoActualizarVista(BancoVista.this, TypeCRUD.MODIFICAR, bancoSeleccionado);
				}
					
			}
		});
		Button eliminarButton = new Button("Eliminar");
		eliminarButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				CotizacionService.Util.getInstance().eliminarBanco( bancoSeleccionado.getId(),new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						new MensageExito("Se elimino corrtamente");
						cargarDatos();
					}
					@Override
					public void onFailure(Throwable caught) {
						new MensageError(caught.getMessage()).show();
					}
				});	
			}
		});
		
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.add(dataGrid);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setWidth("100%");
		horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		FlexTable layout = new FlexTable();
		layout.setCellSpacing(5);
		layout.setWidget(0,0,nuevoButton);
		layout.setWidget(0,1,modificarButton);
		layout.setWidget(0,2,eliminarButton);
		layout.setWidget(0,3,salirButton);
		horizontalPanel.add(layout);
		
		verticalPanel.add(horizontalPanel);
		
		cargarDatos();
		
		setWidget(verticalPanel);
		center();
		
	}
	
	public void cargarDatos(){		
		CotizacionService.Util.getInstance().getBancos(new AsyncCallback<List<Banco>>() {
			@Override
			public void onSuccess(List<Banco> result) {
				dataGrid.setRowData(result);
			}
			@Override
			public void onFailure(Throwable caught) {
				new MensageError(caught.getMessage()).show();
			}
		});
	} 
	
	
}
