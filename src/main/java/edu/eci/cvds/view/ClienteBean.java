package edu.eci.cvds.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.inject.Injector;

import edu.eci.cvds.sampleprj.dao.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.services.impl.ServiciosAlquilerImpl;


@ManagedBean(name="clienteBean")
@SessionScoped
public class ClienteBean extends BasePageBean{
	private ServiciosAlquilerImpl serv;
	private long documento;
	private String nombre;
	private String direccion;
	private String telefono;
	private String email;
	private boolean vetado;
	public ClienteBean() {
		Injector i = super.getInjector();
		serv = i.getInstance(ServiciosAlquilerImpl.class);
	}
	public List<Cliente> getClientes(){
		List<Cliente> clientes=  serv.consultarClientes();
		return clientes;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDocumento(long documento) {
		this.documento = documento;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setVetado(boolean vetado) {
		this.vetado = vetado;
	}
	public String getNombre() {
		return nombre;
	}
	public long getDocumento() {
		return documento;
	}
	public String getDireccion() {
		return direccion;
	}
	public String getEmail() {
		return email;
	}
	public String getTelefono() {
		return telefono;
	}
	public boolean getVetado() {
		return vetado;
	}
	public void registrarCliente() {
		try {
			System.out.println(nombre+" "+documento+" ");
			serv.registrarCliente(new Cliente(nombre, documento, telefono, direccion, email));
		} catch (ExcepcionServiciosAlquiler e) {
			System.out.println(e.getMessage());
		}
	}
}
