package com.jgroup.creditos.view;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.jgroup.creditos.client.ContratoService;
import com.jgroup.creditos.client.CotizacionService;
import com.jgroup.creditos.mensajes.MensageError;
import com.jgroup.creditos.mensajes.MensageExito;
import com.jgroup.creditos.model.Banco;
import com.jgroup.creditos.model.PlanPagosContrato;
import com.jgroup.creditos.validacion.KeyUpFloatValidation;
import com.jgroup.creditos.validacion.KeyUpIntegerValidation;

public class PagoModificacionVista extends DialogBox {

	private CreditosVista creditosVista;
	
	private Label nroPrestamoLabel   = new Label();
	private DateBox fechaPagoDateBox = new DateBox();
	private TextBox nroReciboTextBox = new TextBox();

	private PlanPagosContrato planPagosContratoSeleccionado;
	
	public PagoModificacionVista(CreditosVista creditosVista, PlanPagosContrato planPagosContratoSeleccionado) {
		super();
		this.creditosVista = creditosVista;	
		this.planPagosContratoSeleccionado = planPagosContratoSeleccionado;
		init();
		
	}	
	
	public void init() {
		
		nroReciboTextBox.addKeyUpHandler(new KeyUpIntegerValidation(nroReciboTextBox));
			
		setGlassEnabled(true);
		setAnimationEnabled(false);
		setText("Nuevo Banco");
		
		Button nuevoButton = new Button("Aceptar");
		nuevoButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				ContratoService.Util.getInstance().modificarPlanPagosCredito(planPagosContratoSeleccionado.getId(), fechaPagoDateBox.getValue(), nroReciboTextBox.getValue(), new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						new MensageError(caught.getMessage()).show();
					}
					@Override
					public void onSuccess(Void result) {
						new MensageExito("Guardado exitosamente");
						PagoModificacionVista.this.hide();
					}
				});
			}
		});
		
		Button salirButton = new Button("Salir");
		salirButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				PagoModificacionVista.this.hide();	
			}
		});
		
		FlexTable layout = new FlexTable();
		layout.setCellSpacing(5);
		
		layout.setHTML(0, 0, "Nombre:");
		layout.setWidget(0, 1, nroPrestamoLabel);
		
		layout.setHTML(1, 0, "Interes:");
		layout.setWidget(1, 1, fechaPagoDateBox);
		
		layout.setHTML(2, 0, "Prima Desgravamen:");
		layout.setWidget(2, 1, nroReciboTextBox);
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.add(layout);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setWidth("100%");
		horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		FlexTable layout2 = new FlexTable();
		layout2.setCellSpacing(5);
		layout2.setWidget(0, 0, nuevoButton);
		layout2.setWidget(0, 1, salirButton);
		horizontalPanel.add(layout2);
		
		verticalPanel.add(horizontalPanel);
		
		setWidget(verticalPanel);
		center();
	}
	
}
