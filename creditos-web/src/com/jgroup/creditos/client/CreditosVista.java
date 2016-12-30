package com.jgroup.creditos.client;

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
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.jgroup.creditos.mensajes.MensageError;
import com.jgroup.creditos.model.Contrato;
import com.jgroup.creditos.model.PlanPagosContrato;
import com.jgroup.creditos.pdf.PlanPagosPDF;
import com.jgroup.creditos.validacion.KeyUpFloatValidation;
import com.jgroup.creditos.validacion.KeyUpIntegerValidation;

/**
 * @author willy
 */
public class CreditosVista extends VerticalPanel {

	private DataGrid<PlanPagosContrato> dataGrid = null;

	private TextBox nombreTextBox = new TextBox();
	private TextBox capacidadPagoTextBox = new TextBox();
	private TextBox edadActualTextBox = new TextBox();
	private Label nroContrato = new Label();
	private DateBox fechaNacimientoDateBox = new DateBox();
	private Label fechaContratoLabel = new Label();
	private TextBox ingresoBaseTextBox = new TextBox();
	private TextBox montoBaseCuotaTextBox = new TextBox();
	private TextBox nroCuotasTextBox = new TextBox();
	private Label montoPrestamoLabel = new Label();
	private Label bancoLabel = new Label();
	private TextBox documentoIdentidadTextBox = new TextBox();
	
	private PlanPagosPDF planPagosPDF;

	private ListDataProvider<Contrato> dataProvider = new ListDataProvider<Contrato>();

	private TextBox buscarTextBox;

	public void init() {

		// Estilos
		setSize("100%", "100%");

		// Adicion de escuchadores para validacion
		capacidadPagoTextBox.addKeyUpHandler(new KeyUpFloatValidation(capacidadPagoTextBox));
		edadActualTextBox.addKeyUpHandler(new KeyUpIntegerValidation(edadActualTextBox));
		ingresoBaseTextBox.addKeyUpHandler(new KeyUpFloatValidation(ingresoBaseTextBox));
		montoBaseCuotaTextBox.addKeyUpHandler(new KeyUpFloatValidation(montoBaseCuotaTextBox));
		nroCuotasTextBox.addKeyUpHandler(new KeyUpIntegerValidation(nroCuotasTextBox));
		
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
		// FlexCellFormatter formatter = layout.getFlexCellFormatter();
		layout.setCellSpacing(5);

		layout.setHTML(0, 0, "Nombre Completo:");
		layout.setWidget(0, 1, nombreTextBox);

		layout.setHTML(0, 2, "Capacidad de Pago:");
		layout.setWidget(0, 3, capacidadPagoTextBox);

		layout.setHTML(1, 0, "Edad Actual:");
		layout.setWidget(1, 1, edadActualTextBox);

		layout.setHTML(1, 2, "Nro Cotización:");
		layout.setWidget(1, 3, nroContrato);

		layout.setHTML(2, 0, "Fecha Nacimiento:");
		layout.setWidget(2, 1, fechaNacimientoDateBox);

		layout.setHTML(2, 2, "Fecha Cotización:");
		layout.setWidget(2, 3, fechaContratoLabel);

		layout.setHTML(3, 0, "Ingreso Base:");
		layout.setWidget(3, 1, ingresoBaseTextBox);

		layout.setHTML(3, 2, "Monto Base Couta:");
		layout.setWidget(3, 3, montoBaseCuotaTextBox);

		layout.setHTML(4, 0, "Numero Cuotas:");
		layout.setWidget(4, 1, nroCuotasTextBox);

		layout.setHTML(4, 2, "Monto Prestamo:");
		layout.setWidget(4, 3, montoPrestamoLabel);

		layout.setHTML(5, 0, "Banco:");
		layout.setWidget(5, 1, bancoLabel);
		
		layout.setHTML(5, 2, "Documento Identidad:");
		layout.setWidget(5, 3, documentoIdentidadTextBox);
		
		verticalPanel.add(layout);

		captionPanel.add(verticalPanel);

		this.add(captionPanel);

		// 3. Plan de Pagos
		captionPanel = new CaptionPanel();
		captionPanel.setCaptionHTML("<b>Plan de Pagos</b>");

		dataGrid = new DataGrid<PlanPagosContrato>();
		dataGrid.setWidth("100%");
		dataGrid.setColumnWidth(0, "100px");
		dataGrid.setEmptyTableWidget(new Label("Sin Datos"));

		ListHandler<Contrato> sortHandler = new ListHandler<Contrato>(dataProvider.getList());
		dataGrid.addColumnSortHandler(sortHandler);

		TextColumn<PlanPagosContrato> nroCuota = new TextColumn<PlanPagosContrato>() {
			@Override
			public String getValue(PlanPagosContrato object) {
				return "" + object.getNroCuota();
			}
		};
		dataGrid.addColumn(nroCuota, "Nro Cuota");

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
			@SuppressWarnings("deprecation")
			@Override
			public String getValue(PlanPagosContrato object) {
				String value = "";
				if (object.getFechaVencimiento() != null) {
					value = DateTimeFormat.getShortDateFormat().format(object.getFechaVencimiento());
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
			@SuppressWarnings("deprecation")
			@Override
			public String getValue(PlanPagosContrato object) {
				String value = "";
				if (object.getFechaPago() != null) {
					value = DateTimeFormat.getShortDateFormat().format(object.getFechaPago());
				}
				return value;
			}
		};
		dataGrid.addColumn(fechaPago, "Fecha Pago");

		// Add a selection model to handle user selection.
		final SingleSelectionModel<PlanPagosContrato> selectionModel = new SingleSelectionModel<PlanPagosContrato>();
		dataGrid.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				PlanPagosContrato selected = selectionModel.getSelectedObject();
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
				String titulos[] = { "ID", "Country", "Rank", "Capital2222" };
				String data[][] = { { "1", "aa", "bb", "cc" } };
				planPagosPDF.generarPDF(titulos, data);
			}
		});
		horizontalPanel2.add(exportPdfButton);

		verticalPanel2.add(horizontalPanel2);

		captionPanel.add(verticalPanel2);

		this.add(captionPanel);
	}

	public void setContrato(Contrato contrato) {
		if (contrato.getNombreCompleto() != null)
			nombreTextBox.setValue(contrato.getNombreCompleto());
		else
			nombreTextBox.setValue("");
		if (contrato.getCapacidadPago() != null)
			capacidadPagoTextBox.setValue(contrato.getCapacidadPago() + "");
		else
			capacidadPagoTextBox.setValue("");
		if (contrato.getEdadActual() != null)
			edadActualTextBox.setValue(contrato.getEdadActual() + "");
		else
			edadActualTextBox.setValue("");
		if (contrato.getNroPrestamo() != null)
			nroContrato.setText(contrato.getNroPrestamo());
		else
			nroContrato.setText("");
		if (contrato.getFechaNacimiento() != null)
			fechaNacimientoDateBox.setValue(contrato.getFechaNacimiento());
		else
			fechaNacimientoDateBox.setValue(null);
		;
		if (contrato.getFechaEmision() != null)
			fechaContratoLabel.setText(DateTimeFormat.getFormat("dd/MM/yyyy").format(contrato.getFechaEmision()));
		else
			fechaContratoLabel.setText("");
		if (contrato.getIngresoBase() != null)
			ingresoBaseTextBox.setValue(contrato.getIngresoBase() + "");
		else
			ingresoBaseTextBox.setValue("");
		if (contrato.getMontoBaseCouta() != null)
			montoBaseCuotaTextBox.setValue(contrato.getMontoBaseCouta() + "");
		else
			montoBaseCuotaTextBox.setValue("");
		if (contrato.getNroCuotas() != null)
			nroCuotasTextBox.setValue(contrato.getNroCuotas() + "");
		else
			nroCuotasTextBox.setValue("");
		if (contrato.getMontoPrestamo() != null)
			montoPrestamoLabel.setText(contrato.getMontoPrestamo() + "");
		else
			montoPrestamoLabel.setText("");
		
		if (contrato.getBanco() != null)
			bancoLabel.setText(contrato.getBanco().getNombre());
		else
			bancoLabel.setText("");
		
		dataGrid.setRowData(contrato.getPlanPagosCotnrato());
	}

}
