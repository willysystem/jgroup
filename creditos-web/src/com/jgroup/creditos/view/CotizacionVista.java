package com.jgroup.creditos.view;

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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.jgroup.creditos.client.BusquedaCotizacion;
import com.jgroup.creditos.client.CotizacionService;
import com.jgroup.creditos.mensajes.MensageConfirmacion;
import com.jgroup.creditos.mensajes.MensageError;
import com.jgroup.creditos.mensajes.MensageExito;
import com.jgroup.creditos.model.Banco;
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

	private TextBox nombreTextBox = new TextBox();
	private TextBox capacidadPagoTextBox = new TextBox();
	private TextBox edadActualTextBox = new TextBox();
	private Label nroCotizacion = new Label();
	private DateBox fechaNacimientoDateBox = new DateBox();
	private Label fechaCotizacionLabel = new Label();
	private TextBox ingresoBaseTextBox = new TextBox();
	private TextBox montoBaseCuotaTextBox = new TextBox();
	private TextBox nroCuotasTextBox = new TextBox();
	private Label montoPrestamoLabel = new Label();
	private ListBox bancoListBox = new ListBox();
	private TextBox documentoIdentidadTextBox = new TextBox();
	
	private Button emitirButton;

	private Cotizacion cotizacion;
	private PlanPagosPDF planPagosPDF;

	private ListDataProvider<Cotizacion> dataProvider = new ListDataProvider<Cotizacion>();

	private TextBox buscarTextBox;

	private static final String BANCOS_SELECCIONAR_ITEM = "Seleccione un Banco";
	
	private MensageConfirmacion mensageConfirmacion = null;

	public void init() {
		
		// Estilos
		//setSize("100%", "100%");

		// Adicion de escuchadores para validacion
		capacidadPagoTextBox.addKeyUpHandler(new KeyUpFloatValidation(capacidadPagoTextBox));
		edadActualTextBox.addKeyUpHandler(new KeyUpIntegerValidation(edadActualTextBox));
		ingresoBaseTextBox.addKeyUpHandler(new KeyUpFloatValidation(ingresoBaseTextBox));
		montoBaseCuotaTextBox.addKeyUpHandler(new KeyUpFloatValidation(montoBaseCuotaTextBox));
		nroCuotasTextBox.addKeyUpHandler(new KeyUpIntegerValidation(nroCuotasTextBox));

		// Valores iniciales
		bancoListBox.addItem(BANCOS_SELECCIONAR_ITEM, "0");
		CotizacionService.Util.getInstance().getBancos(new AsyncCallback<List<Banco>>() {
			@Override
			public void onFailure(Throwable caught) {
				new MensageError(caught.getMessage()).show();
			}

			@Override
			public void onSuccess(List<Banco> result) {
				for (Banco banco : result) {
					bancoListBox.addItem(banco.getNombre(), banco.getId() + "");
				}
			}
		});

		// Cargar script
		planPagosPDF = new PlanPagosPDF();

		// Valores de prueba
		nombreTextBox.setValue("Willy Hurtado");
		capacidadPagoTextBox.setValue("0.75");
		edadActualTextBox.setValue("28");
		edadActualTextBox.setAlignment(TextAlignment.RIGHT);
		fechaNacimientoDateBox.setValue(new Date());
		fechaNacimientoDateBox.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd/MM/yyyy")));
		ingresoBaseTextBox.setValue("5000");
		montoBaseCuotaTextBox.setValue("2000");
		nroCuotasTextBox.setValue("6");
		documentoIdentidadTextBox.setValue("5134828 POTOSI");
	}

	public CotizacionVista() {

		init();
		this.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);

		// 1. Busqueda
		DisclosurePanel captionPanel = new DisclosurePanel("Buscar");
		captionPanel.setOpen(true);
		captionPanel.setAnimationEnabled(true);
		captionPanel.ensureDebugId("cwDisclosurePanel");
		

		buscarTextBox = new TextBox();
		Button nuevoButton = new Button("Nuevo");
		nuevoButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				setCotizacion(new Cotizacion());
			}
		});
		Button buscarButton = new Button("Buscar");
		buscarButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				new BusquedaCotizacion(CotizacionVista.this, buscarTextBox.getValue());
			}
		});

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.add(new Label("Documento Identidad: "));
		horizontalPanel.add(buscarTextBox);
		horizontalPanel.add(buscarButton);
		horizontalPanel.add(nuevoButton);
		captionPanel.setContent(horizontalPanel);

		this.add(captionPanel);

		// 2. Cotización
		captionPanel = new DisclosurePanel("Cotización");
		captionPanel.setOpen(true);
		captionPanel.setAnimationEnabled(true);

		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		FlexTable layout = new FlexTable();
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

		Button bancoCrudModificar = new Button("+");
		bancoCrudModificar.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				new BancoVista(CotizacionVista.this).show();
			}
		});
		HorizontalPanel horizontalPanel3 = new HorizontalPanel();
		horizontalPanel3.add(bancoListBox);
		horizontalPanel3.add(bancoCrudModificar);
		
		layout.setHTML(5, 0, "Banco:");
		layout.setWidget(5, 1, horizontalPanel3);
		
		layout.setHTML(5, 2, "Documento Identidad:");
		layout.setWidget(5, 3, documentoIdentidadTextBox);
		
		verticalPanel.add(layout);

		Button cotizarButton = new Button("Cotizar");
		cotizarButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (bancoListBox.getSelectedIndex() == 0) {
					new MensageError("Eliga un banco").show();
					return;
				}
				if (cotizacion == null)
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
				int indexSelected = bancoListBox.getSelectedIndex();
				String id = bancoListBox.getValue(indexSelected);
				Long idBanco = Long.parseLong(id);
				Banco bancoDTO = new Banco();
				bancoDTO.setId(idBanco);
				cotizacion.setBanco(bancoDTO);
				cotizacion.setDocumentoIdentidad(documentoIdentidadTextBox.getValue());

				CotizacionService.Util.getInstance().getCotizacion(cotizacion, new AsyncCallback<Cotizacion>() {
					@Override
					public void onSuccess(Cotizacion result) {
						CotizacionVista.this.setCotizacion(result);
						List<PlanPagosCotizacion> planes = new ArrayList<>(result.getPlanPagosCotizacion());
						dataGrid.setRowData(planes);
						emitirButton.setEnabled(true);
					}

					@Override
					public void onFailure(Throwable caught) {
						new MensageError(caught.getMessage()).show();
						emitirButton.setEnabled(false);
					}
				});
			}
		});
		verticalPanel.add(cotizarButton);

		captionPanel.setContent(verticalPanel);

		this.add(captionPanel);

		// 3. Plan de Pagos
		captionPanel = new DisclosurePanel("Plan de Pagos");
		captionPanel.setOpen(true);
		captionPanel.setAnimationEnabled(true);
		
		dataGrid = new DataGrid<PlanPagosCotizacion>();
		dataGrid.getElement().setId("dataGridId");
		dataGrid.setWidth("100%");
		dataGrid.setEmptyTableWidget(new Label("Sin Datos"));

		ListHandler<Cotizacion> sortHandler = new ListHandler<Cotizacion>(dataProvider.getList());
		dataGrid.addColumnSortHandler(sortHandler);

		TextColumn<PlanPagosCotizacion> nroCuota = new TextColumn<PlanPagosCotizacion>() {
			@Override
			public String getValue(PlanPagosCotizacion object) {
				return "" + object.getNroCuota();
			}
		};
		dataGrid.setColumnWidth(0, "80px");
		dataGrid.addColumn(nroCuota, "Nro Cuota");

		TextColumn<PlanPagosCotizacion> montoCapital = new TextColumn<PlanPagosCotizacion>() {
			@Override
			public String getValue(PlanPagosCotizacion object) {
				String value = "";
				if (object.getMontoCapital() != null) {
					value = NumberFormat.getFormat("#.00").format(object.getMontoCapital());
				}
				return value;
			}
		};
		dataGrid.setColumnWidth(1, "100px");
		dataGrid.addColumn(montoCapital, "Monto Capital");

		TextColumn<PlanPagosCotizacion> intereses = new TextColumn<PlanPagosCotizacion>() {
			@Override
			public String getValue(PlanPagosCotizacion object) {
				String value = "";
				if (object.getInteres() != null) {
					value = NumberFormat.getFormat("#.00").format(object.getInteres());
				}
				return value;
			}
		};
		dataGrid.setColumnWidth(2, "100px");
		dataGrid.addColumn(intereses, "Intereses");

		TextColumn<PlanPagosCotizacion> desgravamen = new TextColumn<PlanPagosCotizacion>() {
			@Override
			public String getValue(PlanPagosCotizacion object) {
				String value = "";
				if (object.getPrimaDesgravamen() != null) {
					value = NumberFormat.getFormat("#.00").format(object.getPrimaDesgravamen());
				}
				return value;
			}
		};
		desgravamen.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		dataGrid.setColumnWidth(3, "100px");
		dataGrid.addColumn(desgravamen, "Desgravamen");

		TextColumn<PlanPagosCotizacion> totalCuota = new TextColumn<PlanPagosCotizacion>() {
			@Override
			public String getValue(PlanPagosCotizacion object) {
				String value = "";
				if (object.getTotalCuota() != null) {
					value = NumberFormat.getFormat("#.00").format(object.getTotalCuota());
				}
				return value;
			}
		};
		dataGrid.setColumnWidth(4, "100px");
		dataGrid.addColumn(totalCuota, "Total Cuota");

		TextColumn<PlanPagosCotizacion> fechaVencimiento = new TextColumn<PlanPagosCotizacion>() {
			@SuppressWarnings("deprecation")
			@Override
			public String getValue(PlanPagosCotizacion object) {
				String value = "";
				if (object.getFechaVencimiento() != null) {
					value = DateTimeFormat.getShortDateFormat().format(object.getFechaVencimiento());
				}
				return value;
			}
		};
		dataGrid.setColumnWidth(5, "100px");
		dataGrid.addColumn(fechaVencimiento, "Fecha Vencimiento");

		TextColumn<PlanPagosCotizacion> saldoCapital = new TextColumn<PlanPagosCotizacion>() {
			@Override
			public String getValue(PlanPagosCotizacion object) {
				String value = "";
				if (object.getSaldoCapital() != null) {
					value = NumberFormat.getFormat("#.00").format(object.getSaldoCapital());
				}
				return value;
			}
		};
		saldoCapital.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		dataGrid.setColumnWidth(6, "100px");
		dataGrid.addColumn(saldoCapital, "Saldo Capital");

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

		SimpleLayoutPanel slp = new SimpleLayoutPanel();
		slp.setSize("900px", "400px");
		slp.add(dataGrid);
		verticalPanel2.add(slp);

		HorizontalPanel horizontalPanel2 = new HorizontalPanel();
		horizontalPanel2.setWidth("250px");
		Button exportPdfButton = new Button("Exportar PDF");
		exportPdfButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String titulos[] = { "Nro Cuota", "Monto Capital", "Intereses", "Desgravamen", "Total Cuota", "Fecha Vencimiento", "Saldo Capital"};
				String data[][] = new String[7][dataGrid.getRowCount()];
				int i = 0;
				for (PlanPagosCotizacion item : dataGrid.getVisibleItems()) {
					data[i][0] = item.getNroCuota()+"";
					data[i][1] = NumberFormat.getFormat("#.00").format(item.getMontoCapital());
					data[i][2] = NumberFormat.getFormat("#.00").format(item.getInteres());
					data[i][3] = NumberFormat.getFormat("#.00").format(item.getPrimaDesgravamen());
					data[i][4] = NumberFormat.getFormat("#.00").format(item.getTotalCuota());
					data[i][5] = DateTimeFormat.getFormat("dd/MM/yyyy").format(item.getFechaVencimiento());
					data[i][6] = NumberFormat.getFormat("#.00").format(item.getSaldoCapital());
					i++;
				}
				
				int index = bancoListBox.getSelectedIndex();
				String bancoValue = bancoListBox.getItemText(index);
				String fechaNacimientoValue = DateTimeFormat.getFormat("dd/MM/yyyy").format(fechaNacimientoDateBox.getValue());
				planPagosPDF.generarPDF(titulos, data, nombreTextBox.getValue(),          capacidadPagoTextBox.getValue(), 
						                               edadActualTextBox.getValue(),      nroCotizacion.getText(),
						                               fechaNacimientoValue,              fechaCotizacionLabel.getText(),
						                               ingresoBaseTextBox.getValue(),     montoBaseCuotaTextBox.getValue(),
						                               nroCuotasTextBox.getValue(),       montoPrestamoLabel.getText(),
						                               bancoValue,                        documentoIdentidadTextBox.getValue());
			}
		});
		horizontalPanel2.add(exportPdfButton);
		
		Button exportExcelButton = new Button("Exportar Excel");
		exportExcelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				exportToExcel();
			}
		});
		horizontalPanel2.add(exportExcelButton);
		
		Button imprimirButton = new Button("Imprimir");
		imprimirButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.open("http://www.google.com", "_black", "");
			}
		});
		horizontalPanel2.add(imprimirButton);

		emitirButton = new Button("Emitir");
		emitirButton.setEnabled(false);
		emitirButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mensageConfirmacion = new MensageConfirmacion("¿Decea emitir crédito?", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						CotizacionService.Util.getInstance().emitirCredito(cotizacion, new AsyncCallback<Void>() {
							@Override
							public void onFailure(Throwable caught) {
								new MensageError(caught.getMessage()).show();
							}
							@Override
							public void onSuccess(Void result) {
								new MensageExito("Emisión existosa").show();
								nombreTextBox.setValue("");
								capacidadPagoTextBox.setValue("");
								edadActualTextBox.setValue("");
								nroCotizacion.setText("");
								fechaNacimientoDateBox.setValue(null);
								fechaCotizacionLabel.setText("");
								ingresoBaseTextBox.setText("");
								montoBaseCuotaTextBox.setText("");
								nroCuotasTextBox.setValue("");
								montoPrestamoLabel.setText("");
								bancoListBox.setSelectedIndex(0);
								documentoIdentidadTextBox.setValue("");
								mensageConfirmacion.hide();
								dataGrid.setRowData(new ArrayList<PlanPagosCotizacion>());
								emitirButton.setEnabled(false);
							}
						});
					}
				});
			}
		});
		horizontalPanel2.add(emitirButton);

		HorizontalPanel horizontalPanel4 = new HorizontalPanel();
		horizontalPanel4.setWidth("900px");
		horizontalPanel4.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		horizontalPanel4.add(horizontalPanel2);
		
		verticalPanel2.add(horizontalPanel4);

		captionPanel.setContent(verticalPanel2);

		this.add(captionPanel);
	}

	public void setCotizacion(Cotizacion cotizacion) {
		if (cotizacion.getNombreCompleto() != null)
			nombreTextBox.setValue(cotizacion.getNombreCompleto());
		else
			nombreTextBox.setValue("");
		if (cotizacion.getCapacidadPago() != null)
			capacidadPagoTextBox.setValue(cotizacion.getCapacidadPago() + "");
		else
			capacidadPagoTextBox.setValue("");
		if (cotizacion.getEdadActual() != null)
			edadActualTextBox.setValue(cotizacion.getEdadActual() + "");
		else
			edadActualTextBox.setValue("");
		if (cotizacion.getNroCotizacion() != null)
			nroCotizacion.setText(cotizacion.getNroCotizacion());
		else
			nroCotizacion.setText("");
		if (cotizacion.getFechaNacimiento() != null)
			fechaNacimientoDateBox.setValue(cotizacion.getFechaNacimiento());
		else
			fechaNacimientoDateBox.setValue(null);
		;
		if (cotizacion.getFechaCotizacion() != null)
			fechaCotizacionLabel.setText(DateTimeFormat.getFormat("dd/MM/yyyy").format(cotizacion.getFechaCotizacion()));
		else
			fechaCotizacionLabel.setText("");
		if (cotizacion.getIngresoBase() != null)
			ingresoBaseTextBox.setValue(cotizacion.getIngresoBase() + "");
		else
			ingresoBaseTextBox.setValue("");
		if (cotizacion.getMontoBaseCouta() != null)
			montoBaseCuotaTextBox.setValue(cotizacion.getMontoBaseCouta() + "");
		else
			montoBaseCuotaTextBox.setValue("");
		if (cotizacion.getNroCuotas() != null)
			nroCuotasTextBox.setValue(cotizacion.getNroCuotas() + "");
		else
			nroCuotasTextBox.setValue("");
		if (cotizacion.getMontoPrestamo() != null){
			String monto = NumberFormat.getFormat("#.00").format(cotizacion.getMontoPrestamo());
			montoPrestamoLabel.setText(monto);
		} else
			montoPrestamoLabel.setText("");
		if (cotizacion.getBanco() != null) {
			Banco bancoDTO = cotizacion.getBanco();
			int totalItems = bancoListBox.getItemCount();
			GWT.log("totalItems: " + totalItems);
			GWT.log("bancoDTO.getId():" + bancoDTO.getId());
			for (int i = 0; i < totalItems; i++) {
				String id = bancoListBox.getValue(i);
				GWT.log("id: " + id);
				if (id.equals(bancoDTO.getId() + "")) {
					bancoListBox.setSelectedIndex(i);
					GWT.log("i: " + i);
					break;
				}
			}
		} else {
			bancoListBox.setSelectedIndex(0);
		}
		if(cotizacion.getDocumentoIdentidad() != null)
			documentoIdentidadTextBox.setValue(cotizacion.getDocumentoIdentidad());
		else 
			documentoIdentidadTextBox.setValue("");
		
		dataGrid.setRowData(cotizacion.getPlanPagosCotizacion());
		this.cotizacion = cotizacion;
		emitirButton.setEnabled(true);
	}
	
	private native void exportToExcel() /*-{
		$wnd.tableToExcel('dataGridId', 'COTIZACION')
	}-*/;

}
