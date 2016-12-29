package com.jgroup.creditos.exceptions;

public class JGroupException extends Throwable {

	private static final long serialVersionUID = 3320999822389073370L;

	private Integer witdh = 250;
	private Integer heigth = 50;

	public JGroupException(String mensaje) {
		super(mensaje);
	}

	public JGroupException(String mensaje, Integer ancho, Integer alto) {
		super(mensaje);
		this.witdh = ancho;
		this.heigth = alto;
	}

	public Integer getWitdh() {
		return witdh;
	}

	public Integer getHeigth() {
		return heigth;
	}

}
