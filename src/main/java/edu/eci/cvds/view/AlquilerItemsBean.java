package edu.eci.cvds.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.inject.Injector;

import edu.eci.cvds.sampleprj.dao.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.impl.ServiciosAlquilerImpl;


@ManagedBean(name="clienteBean")
@SessionScoped
public class AlquilerItemsBean extends BasePageBean{
	private ServiciosAlquilerImpl serv;
	private long documento;
	private String nombre;
	private String direccion;
	private String telefono;
	private String email;
	private boolean vetado;
	private Cliente cliente;
	private int idItem;
	private List<Cliente> clientes;
	private Date fechaEntrega;
	public AlquilerItemsBean() {
		Injector i = super.getInjector();
		serv = i.getInstance(ServiciosAlquilerImpl.class);
	}
	public List<Cliente> getClientes(){
		if(clientes==null) clientes=  serv.consultarClientes();
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
			Cliente c = new Cliente(nombre, documento, telefono, direccion, email);
			serv.registrarCliente(c);
			clientes.add(0, c);
			
		} catch (ExcepcionServiciosAlquiler e) {
		}
	}
	public String redirect(Cliente c) {
		cliente = c;
		return "registroitem.xhtml?faces-redirect=true";
	}
	public boolean existeCliente() {
		return cliente!=null;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public List<ItemRentado> getRentados(){
		return cliente.getRentados();
	}
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int id) {
		this.idItem = id;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fecha) {
		this.fechaEntrega = fecha;
	}
	public void prestarItem() {
		Item it = new Item();
		it.setId(idItem);
		try {
			serv.registrarAlquilerCliente(new java.sql.Date(fechaEntrega.getYear(),fechaEntrega.getMonth(),fechaEntrega.getDay()), cliente.getDocumento(), it, 3);
			ItemRentado item = serv.getItemRentado(idItem);
			item.setFechafinrenta(new java.sql.Date(fechaEntrega.getYear(),fechaEntrega.getMonth(),fechaEntrega.getDay()));
			java.util.Date dat = Calendar.getInstance().getTime();
			item.setFechainiciorenta(new java.sql.Date(dat.getYear(),dat.getMonth(),dat.getDay()));
			cliente.addItemRentado(item);
		}catch(Exception e) {
		}
	}
}
