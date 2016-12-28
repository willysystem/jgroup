package com.jgroup.creditos.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.jgroup.creditos.model.Cotizacion;
import com.jgroup.creditos.model.PlanPagosCotizacion;
import com.jgroup.creditos.pdf.PlanPagosPDF;
import com.jgroup.creditos.validacion.KeyUpFloatValidation;
import com.jgroup.creditos.validacion.KeyUpIntegerValidation;

/**
 * @author willy
 */
public class CotizacionVista extends VerticalPanel {
	
	private DataGrid<PlanPagosCotizacion> dataGrid = null;
	
	private TextBox nombreTextBox          = new TextBox();
	private TextBox capacidadPagoTextBox   = new TextBox();
	private TextBox edadActualTextBox      = new TextBox();
	private Label nroCotizacion            = new Label();
	private DateBox fechaNacimientoDateBox = new DateBox();
	private Label fechaCotizacionLabel     = new Label();
	private TextBox ingresoBaseTextBox     = new TextBox();
	private TextBox montoBaseCuotaTextBox  = new TextBox();
	private TextBox nroCuotasTextBox       = new TextBox();
	private Label montoPrestamoLabel       = new Label();
	private ListBox bancoListBox           = new ListBox();
	
    private Cotizacion cotizacion;
    private PlanPagosPDF planPagosPDF;
    
    private ListDataProvider<Cotizacion> dataProvider = new ListDataProvider<Cotizacion>();
    
    private TextBox buscarTextBox;
    
    public void init(){
    	    	
    	// Estilos
    	setSize("100%", "100%");
    	
    	// Adicion de escuchadores para validacion
    	capacidadPagoTextBox.addKeyUpHandler(new KeyUpFloatValidation(capacidadPagoTextBox));
    	edadActualTextBox.addKeyUpHandler(new KeyUpIntegerValidation(edadActualTextBox));
    	ingresoBaseTextBox.addKeyUpHandler(new KeyUpFloatValidation(ingresoBaseTextBox));
    	montoBaseCuotaTextBox.addKeyUpHandler(new KeyUpFloatValidation(montoBaseCuotaTextBox));
    	nroCuotasTextBox.addKeyUpHandler(new KeyUpIntegerValidation(nroCuotasTextBox));
    	
    	// Valores iniciales
    	bancoListBox.addItem("Seleccione un Banco ...");
	    bancoListBox.addItem("UNION");
	    bancoListBox.addItem("BCP");
	    
	    // Cargar script
	    planPagosPDF = new PlanPagosPDF();
	    
	    // Valores de prueba
	    nombreTextBox.setValue("Willy Hurtado");
	    capacidadPagoTextBox.setValue("0.75");
	    edadActualTextBox.setValue("28");
	    fechaNacimientoDateBox.setValue(new Date());
	    ingresoBaseTextBox.setValue("5000");
	    montoBaseCuotaTextBox.setValue("2000");
	    nroCuotasTextBox.setValue("6");
	    
    }
    
	public CotizacionVista() {
		
		init();
		
		// 1. Busqueda
		CaptionPanel captionPanel = new CaptionPanel();
		captionPanel.setCaptionHTML("<b>Buscar</b>");
		
		buscarTextBox = new TextBox();
		Button buscarButton = new Button("Buscar");
		Button buscarNuevo = new Button("Nuevo");
		buscarButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				CotizacionService.Util.getInstance().buscarCotizacion(buscarTextBox.getValue() , new AsyncCallback<List<Cotizacion>>() {
					@Override
					public void onFailure(Throwable caught) {
						
					}

					@Override
					public void onSuccess(List<Cotizacion> result) {
						new Busqueda(CotizacionVista.this, result);
					}
				});
			}
		});
		
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.add(new Label("Documento Identidad: "));
		horizontalPanel.add(buscarTextBox);
		horizontalPanel.add(buscarButton);
		horizontalPanel.add(buscarNuevo);
		captionPanel.add(horizontalPanel);
		
		this.add(captionPanel);
		
		// 2. Cotización
		captionPanel = new CaptionPanel();
		captionPanel.setCaptionHTML("<b>Cotización</b>");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		FlexTable layout = new FlexTable();
	    //FlexCellFormatter formatter = layout.getFlexCellFormatter();
	    layout.setCellSpacing(5);
	    
	    layout.setHTML(0, 0, "Nombre Completo:");
	    layout.setWidget(0, 1, nombreTextBox);
	    
	    layout.setHTML(0, 2, "Capacidad de Pago:");
	    layout.setWidget(0, 3, capacidadPagoTextBox);
	    
	    layout.setHTML(1, 0, "Edad Actual:");
	    layout.setWidget(1, 1, edadActualTextBox);
	    
	    layout.setHTML(1, 2, "Nro Cotización:");
	    layout.setWidget(1, 3, nroCotizacion);
	    
	    layout.setHTML(2, 0, "Fecha Nacimiento:");
	    layout.setWidget(2, 1, fechaNacimientoDateBox);
	    
	    layout.setHTML(2, 2, "Fecha Cotización:");
	    layout.setWidget(2, 3, fechaCotizacionLabel);
	    
	    layout.setHTML(3, 0, "Ingreso Base:");
	    layout.setWidget(3, 1, ingresoBaseTextBox);
	    
	    layout.setHTML(3, 2, "Monto Base Couta:");
	    layout.setWidget(3, 3, montoBaseCuotaTextBox);
	    
	    layout.setHTML(4, 0, "Numero Cuotas:");
	    layout.setWidget(4, 1, nroCuotasTextBox);
	    
	    layout.setHTML(4, 2, "Monto Prestamo:");
	    layout.setWidget(4, 3, montoPrestamoLabel);
	    
	    layout.setHTML(5, 0, "Banco:");
	    layout.setWidget(5, 1, bancoListBox);
	    verticalPanel.add(layout);
	    
	    Button cotizarButton = new Button("Cotizar");
	    cotizarButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cotizacion = new Cotizacion();
				cotizacion.setNombreCompleto(nombreTextBox.getValue());
				String capacidadPago = capacidadPagoTextBox.getValue();
				cotizacion.setCapacidadPago(Float.parseFloat(capacidadPago));
				Integer edadActual = Integer.parseInt(edadActualTextBox.getValue());
				cotizacion.setEdadActual(edadActual);
				Date fechaNacimiento = fechaNacimientoDateBox.getValue();
				cotizacion.setFechaNacimiento(fechaNacimiento);
				Float ingresoBase = Float.parseFloat(ingresoBaseTextBox.getValue());
				cotizacion.setIngresoBase(ingresoBase);
				Float montoBaseCuota = Float.parseFloat(montoBaseCuotaTextBox.getValue());
				cotizacion.setMontoBaseCouta(montoBaseCuota);
				Integer nroCuotas = Integer.parseInt(nroCuotasTextBox.getValue());
				cotizacion.setNroCuotas(nroCuotas);
				CotizacionService.Util.getInstance().getCotizacion(cotizacion, new AsyncCallback<Cotizacion>() {
					@Override
					public void onSuccess(Cotizacion result) {
						List<PlanPagosCotizacion> planes = new ArrayList<>(result.getPlanPagosCotizacion());
						dataGrid.setRowData(planes);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						
					}
				});
			}
		});
	    verticalPanel.add(cotizarButton);
	    
	    captionPanel.add(verticalPanel);
	    
	    this.add(captionPanel);
		
		// 3. Plan de Pagos
	    captionPanel = new CaptionPanel();
		captionPanel.setCaptionHTML("<b>Plan de Pagos</b>");
		
		dataGrid = new DataGrid<PlanPagosCotizacion>();
		dataGrid.setWidth("100%");
		dataGrid.setColumnWidth(0, "100px");
		dataGrid.setEmptyTableWidget(new Label("Sin Datos"));
		
		ListHandler<Cotizacion> sortHandler = new ListHandler<Cotizacion>(dataProvider.getList());
		dataGrid.addColumnSortHandler(sortHandler);
		
		TextColumn<PlanPagosCotizacion> nroCuota = new TextColumn<PlanPagosCotizacion>() {
		   @Override
		   public String getValue(PlanPagosCotizacion object) {
			    return ""+object.getNroCuota();
	    }};
		dataGrid.addColumn(nroCuota, "Nro Cuota");
		
		TextColumn<PlanPagosCotizacion> montoCapital = new TextColumn<PlanPagosCotizacion>() {
		   @Override
		   public String getValue(PlanPagosCotizacion object) {
			   String value = "";
			   if(object.getMontoCapital() != null){
				  value = NumberFormat.getFormat("#.00").format(object.getMontoCapital());   
			   }
			   return value;
		}};
		dataGrid.addColumn(montoCapital, "Monto Capital");
		
		TextColumn<PlanPagosCotizacion> intereses = new TextColumn<PlanPagosCotizacion>() {
			@Override
			public String getValue(PlanPagosCotizacion object) {
				String value = "";
				if(object.getInteres() != null){
				   value = NumberFormat.getFormat("#.00").format(object.getInteres());   
				}
			    return value;
			}};
		dataGrid.addColumn(intereses, "Intereses");
		
		TextColumn<PlanPagosCotizacion> desgravamen = new TextColumn<PlanPagosCotizacion>() {
			@Override
			public String getValue(PlanPagosCotizacion object) {
				String value = "";
				if(object.getPrimaDesgravamen() != null){
				   value = NumberFormat.getFormat("#.00").format(object.getPrimaDesgravamen());   
				}
				return value;
			}};
		dataGrid.addColumn(desgravamen, "Desgravamen");
		
		TextColumn<PlanPagosCotizacion> totalCuota = new TextColumn<PlanPagosCotizacion>() {
			@Override
			public String getValue(PlanPagosCotizacion object) {
				String value = "";
				if(object.getTotalCuota() != null){
				   value = NumberFormat.getFormat("#.00").format(object.getTotalCuota());   
				}
				return value;
			}};
		dataGrid.addColumn(totalCuota, "Total Cuota");
		
		TextColumn<PlanPagosCotizacion> fechaVencimiento = new TextColumn<PlanPagosCotizacion>() {
			@Override
			public String getValue(PlanPagosCotizacion object) {
				String value = "";
				if(object.getFechaVencimiento() != null){
					value = DateTimeFormat.getShortDateFormat().format(object.getFechaVencimiento());
				}
			    return value;
			}};
		dataGrid.addColumn(fechaVencimiento, "Fecha Vencimiento");
		
		TextColumn<PlanPagosCotizacion> saldoCapital = new TextColumn<PlanPagosCotizacion>() {
			@Override
			public String getValue(PlanPagosCotizacion object) {
				String value = "";
				if(object.getSaldoCapital() != null){
				   value = NumberFormat.getFormat("#.00").format(object.getSaldoCapital());   
				}
				return value;
			}};
		dataGrid.addColumn(saldoCapital, "Saldo Capital");
		
		TextColumn<PlanPagosCotizacion> fechaPago = new TextColumn<PlanPagosCotizacion>() {
			@Override
			public String getValue(PlanPagosCotizacion object) {
				String value = "";
				if(object.getFechaPago() != null){
					value = DateTimeFormat.getShortDateFormat().format(object.getFechaPago());
				}
			    return value;
			}};
		dataGrid.addColumn(fechaPago, "Fecha Pago");
		
		// Add a selection model to handle user selection.
  	    final SingleSelectionModel<PlanPagosCotizacion> selectionModel = new SingleSelectionModel<PlanPagosCotizacion>();
  	    dataGrid.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
		    public void onSelectionChange(SelectionChangeEvent event) {
		    	PlanPagosCotizacion selected = selectionModel.getSelectedObject();
			    GWT.log("selected: " + selected);
		    }
		});
		dataGrid.setRowCount(0, true);

		VerticalPanel verticalPanel2 = new VerticalPanel();
		verticalPanel2.setSize("100%", "100%");
		verticalPanel2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel2.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		SimpleLayoutPanel slp = new SimpleLayoutPanel();
		slp.setSize("100%", "400px");
		slp.add(dataGrid);
		verticalPanel2.add(slp);
		
		HorizontalPanel horizontalPanel2 = new HorizontalPanel();
		horizontalPanel2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		horizontalPanel2.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		Button exportPdfButton = new Button("Exportar en PDF");
		exportPdfButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String titulos[] = {"ID", "Country", "Rank", "Capital2222"};
				String data[][] = {{"1", "aa", "bb", "cc"}};
				planPagosPDF.generarPDF(titulos, data);
			}
		});
		horizontalPanel2.add(exportPdfButton);
		
		Button emitirEmitir = new Button("Emitir");
		horizontalPanel2.add(emitirEmitir);
		
		verticalPanel2.add(horizontalPanel2);

		captionPanel.add(verticalPanel2);
		
		this.add(captionPanel);
	}

	public void setCotizacion(Cotizacion cotizacion) {
		if(cotizacion.getNombreCompleto() != null)  nombreTextBox.setValue(cotizacion.getNombreCompleto());
		if(cotizacion.getCapacidadPago() != null)   capacidadPagoTextBox.setValue(cotizacion.getCapacidadPago() + "");
		if(cotizacion.getEdadActual() != null)      edadActualTextBox.setValue(cotizacion.getEdadActual()+"");
		if(cotizacion.getNroCotizacion() != null)   nroCotizacion.setText(cotizacion.getNroCotizacion());
		if(cotizacion.getFechaNacimiento() != null) fechaNacimientoDateBox.setValue(cotizacion.getFechaNacimiento()); 
		if(cotizacion.getFechaCotizacion() != null) fechaCotizacionLabel.setText(cotizacion.getFechaCotizacion().toLocaleString()); 
		if(cotizacion.getIngresoBase() != null)     ingresoBaseTextBox.setValue(cotizacion.getIngresoBase()+"");
		if(cotizacion.getMontoBaseCouta() != null)  montoBaseCuotaTextBox.setValue(cotizacion.getMontoBaseCouta()+"");
		if(cotizacion.getNroCuotas() != null)       nroCuotasTextBox.setValue(cotizacion.getNroCuotas()+"");
		if(cotizacion.getMontoPrestamo() != null)   montoPrestamoLabel.setText(cotizacion.getMontoPrestamo()+"");
		// TODO falta banco
		
		this.cotizacion = cotizacion;
	}
	
}
