package edu.eci.cvds.sampleprj.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Cliente;

public interface ClienteDAO {
	public Cliente getCliente(long doc) throws ExcepcionServiciosAlquiler;
	public void registrarCliente(long documento,String nombre,String telefono,String direccion,String email);
	public void agregarItemRentado(@Param("idc") int id , 
            @Param("iditem") int idit, 
            @Param("inicio") Date fechainicio,
            @Param("fin") Date fechafin);
	public List<Cliente> consultarClientes();
	public void agregarCliente(Cliente c);
}
