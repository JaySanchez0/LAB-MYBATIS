package edu.eci.cvds.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.inject.Injector;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.services.impl.ServiciosAlquilerImpl;

@ManagedBean(name="renterBean")
@SessionScoped
public class ClienteRenterBean extends BasePageBean{
	Cliente c;
	private ServiciosAlquilerImpl serv;
	public ClienteRenterBean() {
		Injector i = super.getInjector();
		serv = i.getInstance(ServiciosAlquilerImpl.class);
	}
	public String redirect(Cliente c) {
		this.c = c;
		return "registroalquiler.xhtml";
	}
	
}
