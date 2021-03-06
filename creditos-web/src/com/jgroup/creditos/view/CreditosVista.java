package com.jgroup.creditos.view;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.jgroup.creditos.client.BusquedaCreditos;
import com.jgroup.creditos.client.ContratoService;
import com.jgroup.creditos.mensajes.MensageError;
import com.jgroup.creditos.mensajes.MensageExito;
import com.jgroup.creditos.model.Contrato;
import com.jgroup.creditos.model.PlanPagosContrato;
import com.jgroup.creditos.pdf.PlanPagosPDF;


/**
 * @author willy
 */
public class CreditosVista extends VerticalPanel {

	private DataGrid<PlanPagosContrato> dataGrid = null;

	private Label nombreLabel = new Label();
	private Label capacidadPagoLabel = new Label();
	private Label edadActualLabel = new Label();
	private Label nroContratoLabel = new Label();
	private Label fechaNacimientoLabel = new Label();
	private Label fechaContratoLabel = new Label();
	private Label ingresoBaseLabel = new Label();
	private Label montoBaseCuotaLabel = new Label();
	private Label nroCuotasLabel = new Label();
	private Label montoPrestamoLabel = new Label();
	private Label bancoLabel = new Label();
	private Label documentoIdentidadLabel = new Label();
	private DateBox fechaEmisionLabel = new DateBox();
	private TextBox nroPrestamoTextBox = new TextBox();
	private DateBox fechaLiquidacionDateBox = new DateBox();
	
	private PlanPagosPDF planPagosPDF;

	private ListDataProvider<PlanPagosContrato> dataProvider = new ListDataProvider<PlanPagosContrato>();
	
	private TextColumn<PlanPagosContrato> nroCuota;
	
	final ProvidesKey<PlanPagosContrato> KEY_PROVIDER = new ProvidesKey<PlanPagosContrato>() {
        @Override
        public Object getKey(PlanPagosContrato item) {
            return item.getNroCuota();
        }
    };
    

	private TextBox buscarTextBox;
	
	private PlanPagosContrato pagoSeleccionado;

	private Contrato contratoSeleccionado;
	
	public Contrato getContratoSeleccionado() {
		return contratoSeleccionado;
	}

	public void init() {

		fechaEmisionLabel.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd/MM/yyyy")));
		fechaLiquidacionDateBox.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd/MM/yyyy")));
		nroPrestamoTextBox.setWidth("200px");
		
		// Cargar script
		planPagosPDF = new PlanPagosPDF();

	}

	public CreditosVista() {

		init();

		// 1. Busqueda
		CaptionPanel captionPanel = new CaptionPanel();
		captionPanel.setCaptionHTML("<b>Buscar</b>");

		buscarTextBox = new TextBox();
		
		Button buscarButton = new Button("Buscar");
		buscarButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ContratoService.Util.getInstance().buscarContrato(buscarTextBox.getValue(), new AsyncCallback<List<Contrato>>() {
					@Override
					public void onFailure(Throwable caught) {
						new MensageError(caught.getMessage()).show();
					}

					@Override
					public void onSuccess(List<Contrato> result) {
						new BusquedaCreditos(CreditosVista.this, result);
					}
				});
			}
		});

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.add(new Label("Documento Identidad: "));
		horizontalPanel.add(buscarTextBox);
		horizontalPanel.add(buscarButton);
		captionPanel.add(horizontalPanel);

		this.add(captionPanel);

		// 2. Cotización
		captionPanel = new CaptionPanel();
		captionPanel.setCaptionHTML("<b>Cotización</b>");

		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		FlexTable layout = new FlexTable();
		FlexCellFormatter formatter = layout.getFlexCellFormatter();
		formatter.setWidth(0, 1, "200px");
		layout.setCellSpacing(5);

		layout.setHTML(0, 0, "Nombre Completo:");
		layout.setWidget(0, 1, nombreLabel);

		layout.setHTML(0, 2, "Capacidad de Pago:");
		layout.setWidget(0, 3, capacidadPagoLabel);

		layout.setHTML(1, 0, "Edad Actual:");
		layout.setWidget(1, 1, edadActualLabel);

		layout.setHTML(1, 2, "Nro Cotización:");
		layout.setWidget(1, 3, nroContratoLabel);

		layout.setHTML(2, 0, "Fecha Nacimiento:");
		layout.setWidget(2, 1, fechaNacimientoLabel);

		layout.setHTML(2, 2, "Fecha Cotización:");
		layout.setWidget(2, 3, fechaContratoLabel);

		layout.setHTML(3, 0, "Ingreso Base:");
		layout.setWidget(3, 1, ingresoBaseLabel);

		layout.setHTML(3, 2, "Monto Base Couta:");
		layout.setWidget(3, 3, montoBaseCuotaLabel);

		layout.setHTML(4, 0, "Numero Cuotas:");
		layout.setWidget(4, 1, nroCuotasLabel);

		layout.setHTML(4, 2, "Monto Prestamo:");
		layout.setWidget(4, 3, montoPrestamoLabel);

		layout.setHTML(5, 0, "Banco:");
		layout.setWidget(5, 1, bancoLabel);
		
		layout.setHTML(5, 2, "Documento Identidad:");
		layout.setWidget(5, 3, documentoIdentidadLabel);
		
		layout.setHTML(6, 0, "Fecha Emisión:");
		layout.setWidget(6, 1, fechaEmisionLabel);
		
		layout.setHTML(6, 2, "Nro Prestamo:");
		layout.setWidget(6, 3, nroPrestamoTextBox);
		
		layout.setHTML(7, 0, "Fecha Liquidación:");
		layout.setWidget(7, 1, fechaLiquidacionDateBox);
		
		Button buttonGuardar = new Button("Guardar");
		buttonGuardar.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(contratoSeleccionado == null){
					new MensageError("Tiene que elegir una cotización").show();
				}
				ContratoService.Util.getInstance().modificarCredito(contratoSeleccionado.getId(), fechaEmisionLabel.getValue(), fechaLiquidacionDateBox.getValue(), nroPrestamoTextBox.getValue(), new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						new MensageExito("Se guardo exitosamente").show();
					}
					@Override
					public void onFailure(Throwable caught) {
						new MensageError(caught.getMessage()).show();
					}
				});
			}
		});
		
		verticalPanel.add(layout);
		verticalPanel.add(buttonGuardar);

		captionPanel.add(verticalPanel);

		this.add(captionPanel);

		// 3. Plan de Pagos
		captionPanel = new CaptionPanel();
		captionPanel.setCaptionHTML("<b>Plan de Pagos</b>");

		final SingleSelectionModel<PlanPagosContrato> selectionModel = new SingleSelectionModel<PlanPagosContrato>();
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				pagoSeleccionado = selectionModel.getSelectedObject();
			}
		});
		
		dataGrid = new DataGrid<PlanPagosContrato>(KEY_PROVIDER);
		dataGrid.setWidth("100%");
		dataGrid.setColumnWidth(0, "100px");
		dataGrid.setEmptyTableWidget(new Label("Sin Datos"));
		dataGrid.setSelectionModel(selectionModel);

//		ListHandler<String> sortHandler = new ListHandler<String>(list);
//		    sortHandler.setComparator(partnerIdColumn, new Comparator<String>() {
//		        @Override
//		        public int compare(String o1, String o2) {
//		            return o1.compareTo(o2);
//		        }
//		});
//		dataGrid.addColumnSortHandler(sortHandler);
		
		dataProvider.addDataDisplay(dataGrid);
		
		ListHandler<PlanPagosContrato> sortHandler = new ListHandler<PlanPagosContrato>(dataProvider.getList());
		dataGrid.addColumnSortHandler(sortHandler);

		nroCuota = new TextColumn<PlanPagosContrato>() {
			@Override
			public String getValue(PlanPagosContrato object) {
				return "" + object.getNroCuota();
			}
		};
		nroCuota.setSortable(true);
		dataGrid.addColumn(nroCuota, "Nro Cuota");

		sortHandler.setComparator(nroCuota, new Comparator<PlanPagosContrato>() {
	        @Override
	        public int compare(PlanPagosContrato o1, PlanPagosContrato o2) {
	            return o1.getNroCuota().compareTo(o2.getNroCuota());
	        }
	    });
		
		
		TextColumn<PlanPagosContrato> montoCapital = new TextColumn<PlanPagosContrato>() {
			@Override
			public String getValue(PlanPagosContrato object) {
				String value = "";
				if (object.getMontoCapital() != null) {
					value = NumberFormat.getFormat("#.00").format(object.getMontoCapital());
				}
				return value;
			}
		};
		dataGrid.addColumn(montoCapital, "Monto Capital");

		TextColumn<PlanPagosContrato> intereses = new TextColumn<PlanPagosContrato>() {
			@Override
			public String getValue(PlanPagosContrato object) {
				String value = "";
				if (object.getInteres() != null) {
					value = NumberFormat.getFormat("#.00").format(object.getInteres());
				}
				return value;
			}
		};
		dataGrid.addColumn(intereses, "Intereses");

		TextColumn<PlanPagosContrato> desgravamen = new TextColumn<PlanPagosContrato>() {
			@Override
			public String getValue(PlanPagosContrato object) {
				String value = "";
				if (object.getPrimaDesgravamen() != null) {
					value = NumberFormat.getFormat("#.00").format(object.getPrimaDesgravamen());
				}
				return value;
			}
		};
		dataGrid.addColumn(desgravamen, "Desgravamen");

		TextColumn<PlanPagosContrato> totalCuota = new TextColumn<PlanPagosContrato>() {
			@Override
			public String getValue(PlanPagosContrato object) {
				String value = "";
				if (object.getTotalCuota() != null) {
					value = NumberFormat.getFormat("#.00").format(object.getTotalCuota());
				}
				return value;
			}
		};
		dataGrid.addColumn(totalCuota, "Total Cuota");

		TextColumn<PlanPagosContrato> fechaVencimiento = new TextColumn<PlanPagosContrato>() {
			@Override
			public String getValue(PlanPagosContrato object) {
				String value = "";
				if (object.getFechaVencimiento() != null) {
					value = DateTimeFormat.getFormat("dd/MM/yyyy").format(object.getFechaVencimiento());
				}
				return value;
			}
		};
		dataGrid.addColumn(fechaVencimiento, "Fecha Vencimiento");

		TextColumn<PlanPagosContrato> saldoCapital = new TextColumn<PlanPagosContrato>() {
			@Override
			public String getValue(PlanPagosContrato object) {
				String value = "";
				if (object.getSaldoCapital() != null) {
					value = NumberFormat.getFormat("#.00").format(object.getSaldoCapital());
				}
				return value;
			}
		};
		dataGrid.addColumn(saldoCapital, "Saldo Capital");

		TextColumn<PlanPagosContrato> fechaPago = new TextColumn<PlanPagosContrato>() {
			@Override
			public String getValue(PlanPagosContrato object) {
				String value = "";
				if (object.getFechaPago() != null) {
					value = DateTimeFormat.getFormat("dd/MM/yyyy").format(object.getFechaPago());
				}
				return value;
			}
		};
		dataGrid.addColumn(fechaPago, "Fecha Pago");
		
		TextColumn<PlanPagosContrato> nroRecibo = new TextColumn<PlanPagosContrato>() {
			@Override
			public String getValue(PlanPagosContrato object) {
				String value = "";
				if (object.getNroRecibo() != null) {
					value = object.getNroRecibo();
				}
				return value;
			}
		};
		dataGrid.addColumn(nroRecibo, "Nro Recibo");
		dataGrid.setRowCount(0, true);

		VerticalPanel verticalPanel2 = new VerticalPanel();
		//verticalPanel2.setSize("100%", "100%");
		verticalPanel2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel2.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		SimpleLayoutPanel slp = new SimpleLayoutPanel();
		slp.setSize("900px", "400px");
		slp.add(dataGrid);
		verticalPanel2.add(slp);

		HorizontalPanel horizontalPanel2 = new HorizontalPanel();
		horizontalPanel2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		horizontalPanel2.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		Button modificarPagoButton = new Button("Modificar Pago");
		modificarPagoButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(pagoSeleccionado == null){
					new MensageError("Necesita elegir un pago").show();
					return;
				}
				new PagoModificacionVista(CreditosVista.this, pagoSeleccionado).show();
				
			}
		});
		horizontalPanel2.add(modificarPagoButton);
		
		Button exportPdfButton = new Button("Exportar en PDF");
		exportPdfButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String titulos[] = { "ID", "Country", "Rank", "Capital2222" };
				String data[][] = { { "1", "aa", "bb", "cc" } };
				//planPagosPDF.generarPDF(titulos, data, nombreLabel.getText());
			}
		});
		horizontalPanel2.add(exportPdfButton);
		
		Button imprimirButton = new Button("Imprimir");
		imprimirButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.open("http://www.google.com", "_black", "");
			}
		});
		horizontalPanel2.add(imprimirButton);

		verticalPanel2.add(horizontalPanel2);

		captionPanel.add(verticalPanel2);

		this.add(captionPanel);
	}

	public void setContrato(Contrato contrato) {
		this.contratoSeleccionado = contrato;
		if (contrato.getNombreCompleto() != null)
			nombreLabel.setText(contrato.getNombreCompleto());
		else
			nombreLabel.setText("");
		if (contrato.getCapacidadPago() != null)
			capacidadPagoLabel.setText(NumberFormat.getFormat("#.00").format(contrato.getCapacidadPago()));
		else
			capacidadPagoLabel.setText("");
		if (contrato.getEdadActual() != null)
			edadActualLabel.setText(contrato.getEdadActual() + "");
		else
			edadActualLabel.setText("");
		if (contrato.getNroPrestamo() != null)
			nroContratoLabel.setText(contrato.getNroPrestamo());
		else
			nroContratoLabel.setText("");
		if (contrato.getFechaNacimiento() != null)
			fechaNacimientoLabel.setText(DateTimeFormat.getFormat("dd/MM/yyyy").format(contrato.getFechaNacimiento()));
		else
			fechaNacimientoLabel.setText("");
		if (contrato.getFechaEmision() != null)
			fechaContratoLabel.setText(DateTimeFormat.getFormat("dd/MM/yyyy").format(contrato.getFechaEmision()));
		else
			fechaContratoLabel.setText("");
		if (contrato.getIngresoBase() != null)
			ingresoBaseLabel.setText(NumberFormat.getFormat("#.00").format(contrato.getIngresoBase()));
		else
			ingresoBaseLabel.setText("");
		if (contrato.getMontoBaseCouta() != null)
			montoBaseCuotaLabel.setText(NumberFormat.getFormat("#.00").format(contrato.getMontoBaseCouta()));
		else
			montoBaseCuotaLabel.setText("");
		if (contrato.getNroCuotas() != null)
			nroCuotasLabel.setText(contrato.getNroCuotas()+"");
		else
			nroCuotasLabel.setText("");
		if (contrato.getMontoPrestamo() != null)
			montoPrestamoLabel.setText(contrato.getMontoPrestamo() + "");
		else
			montoPrestamoLabel.setText("");
		
		if (contrato.getBanco() != null)
			bancoLabel.setText(contrato.getBanco().getNombre());
		else
			bancoLabel.setText("");
		if(contrato.getDocumentoIdentidad() != null)
			documentoIdentidadLabel.setText(contrato.getDocumentoIdentidad());
		else 
			documentoIdentidadLabel.setText("");
		if(contrato.getFechaEmision() != null)
			fechaEmisionLabel.setValue(contrato.getFechaEmision());
		else 
			fechaEmisionLabel.setValue(null);
		if(contrato.getNroPrestamo() != null)
			nroPrestamoTextBox.setText(contrato.getNroPrestamo());
		else 
			nroPrestamoTextBox.setText("");
		if(contrato.getFechaLiquidacion() != null)
			fechaLiquidacionDateBox.setValue(contrato.getFechaLiquidacion());
		else 
			fechaLiquidacionDateBox.setValue(null);;
		
		//dataGrid.setRowData(contrato.getPlanPagosCotnrato());
		List<PlanPagosContrato> list = dataProvider.getList();
		list.clear();
		
		List<PlanPagosContrato> pagos = contrato.getPlanPagosCotnrato();
		
		Collections.sort(pagos);
		
		for (PlanPagosContrato data : pagos) {
	        list.add(data);
	    }
		
	}

}
