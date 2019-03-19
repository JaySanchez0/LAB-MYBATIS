package edu.eci.cvds.sampleprj.dao.mybatis;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ExcepcionServiciosAlquiler;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;

public class MyBATISClienteDAO implements ClienteDAO{
	@Inject
	private ClienteMapper maper;
	public MyBATISClienteDAO() {
	}
	public Cliente getCliente(long doc) throws ExcepcionServiciosAlquiler {
		Cliente c = maper.consultarCliente(doc);
		if(c==null) throw new ExcepcionServiciosAlquiler("No existe cliente");
		return c;
	}
	@Override
	public void registrarCliente(long documento, String nombre, String telefono, String direccion, String email) {
		maper.registrarCliente(documento, nombre, telefono, direccion, email);
		
	}
	@Override
	public void agregarItemRentado(int id, int idit, Date fechainicio, Date fechafin) {
		maper.agregarItemRentadoACliente(id, idit, fechainicio, fechafin);
		
	}
	@Override
	public List<Cliente> consultarClientes() {
		return maper.consultarClientes();
	}
	@Override
	public void agregarCliente(Cliente c) {
		maper.agregarCliente(c);
		
	}


}
