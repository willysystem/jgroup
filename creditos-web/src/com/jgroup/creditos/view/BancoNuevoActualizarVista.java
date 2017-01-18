package com.jgroup.creditos.view;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jgroup.creditos.client.CotizacionService;
import com.jgroup.creditos.mensajes.MensageError;
import com.jgroup.creditos.mensajes.MensageExito;
import com.jgroup.creditos.model.Banco;
import com.jgroup.creditos.validacion.KeyUpFloatValidation;

public class BancoNuevoActualizarVista extends DialogBox {

	private BancoVista bancoVista;
	
	private TextBox nombreTextBox        = new TextBox();
	private TextBox interesesTextBox     = new TextBox();
	private TextBox primaDesgravamentTextBox = new TextBox();
	private Banco bancoSeleccionado;
	
	private TypeCRUD typeCRUD;
	
	public BancoNuevoActualizarVista(BancoVista bancoVista, TypeCRUD typeCRUD, Banco banco) {
		super();
		this.typeCRUD = typeCRUD;
		this.bancoVista = bancoVista;
		this.bancoSeleccionado = banco;
		init();
	}
	
	
	public BancoNuevoActualizarVista(BancoVista bancoVista, TypeCRUD typeCRUD) {
		super();
		this.typeCRUD = typeCRUD;
		this.bancoVista = bancoVista;	
		init();
		
	}	
	
	public void init(){
		interesesTextBox.addKeyUpHandler(new KeyUpFloatValidation(interesesTextBox));
		primaDesgravamentTextBox.addKeyUpHandler(new KeyUpFloatValidation(primaDesgravamentTextBox));
		
		if(this.bancoSeleccionado != null){
			nombreTextBox.setValue(bancoSeleccionado.getNombre());
			interesesTextBox.setValue(bancoSeleccionado.getTasaInteres()+"");
			primaDesgravamentTextBox.setValue(bancoSeleccionado.getPrimaDesgravamen()+"");
		}
		
		setGlassEnabled(true);
		setAnimationEnabled(false);
		setText("Nuevo Banco");
		
		Button nuevoButton = new Button("Aceptar");
		nuevoButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(BancoNuevoActualizarVista.this.typeCRUD == TypeCRUD.NUEVO){
					Banco bancoNuevo = new Banco();
					bancoNuevo.setNombre(nombreTextBox.getValue());
					String interes = interesesTextBox.getValue();
					String prima = primaDesgravamentTextBox.getValue();
					bancoNuevo.setTasaInteres(Float.valueOf(interes));
					bancoNuevo.setPrimaDesgravamen(Float.valueOf(prima));
					CotizacionService.Util.getInstance().nuevoBanco(bancoNuevo, new AsyncCallback<Void>(){
						@Override
						public void onFailure(Throwable caught) {
							new MensageError(caught.getMessage()).show();
						}

						@Override
						public void onSuccess(Void result) {
							new MensageExito("Guardado exitosamente");
							BancoNuevoActualizarVista.this.bancoVista.cargarDatos();
							BancoNuevoActualizarVista.this.hide();
						}
					  }
					);
				} else {
					bancoSeleccionado.setNombre(nombreTextBox.getValue());
					String interes = interesesTextBox.getValue();
					String prima = primaDesgravamentTextBox.getValue();
					bancoSeleccionado.setTasaInteres(Float.valueOf(interes));
					bancoSeleccionado.setPrimaDesgravamen(Float.valueOf(prima));
					CotizacionService.Util.getInstance().actualizarBanco(bancoSeleccionado, new AsyncCallback<Banco>(){
						@Override
						public void onFailure(Throwable caught) {
							new MensageError(caught.getMessage()).show();
						}

						@Override
						public void onSuccess(Banco result) {
							new MensageExito("Guardado exitosamente");
							BancoNuevoActualizarVista.this.bancoVista.cargarDatos();
							BancoNuevoActualizarVista.this.hide();
						}
					  }
					);
				}
					
			}
		});
		
		Button salirButton = new Button("Salir");
		salirButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				BancoNuevoActualizarVista.this.hide();	
			}
		});
		
		FlexTable layout = new FlexTable();
		layout.setCellSpacing(5);
		
		layout.setHTML(0, 0, "Nombre:");
		layout.setWidget(0, 1, nombreTextBox);
		
		layout.setHTML(1, 0, "Interes:");
		layout.setWidget(1, 1, interesesTextBox);
		
		layout.setHTML(2, 0, "Prima Desgravamen:");
		layout.setWidget(2, 1, primaDesgravamentTextBox);
		
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
