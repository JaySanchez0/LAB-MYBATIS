package edu.eci.cvds.sampleprj.dao;

public class ExcepcionServiciosAlquiler extends Exception{

	public ExcepcionServiciosAlquiler(String mensaje) {
		super(mensaje);
	}

	public ExcepcionServiciosAlquiler(String mensaje, PersistenceException ex) {
		this(mensaje);
	}

}
