package edu.eci.cvds.sampleprj.dao.mybatis;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.services.conection.conect;

public class MyBATISClienteDAO implements ClienteDAO{
	@Inject
	private ClienteMapper maper;
	public MyBATISClienteDAO() {
	}
	public Cliente getCliente(long doc) {
		Cliente c = maper.consultarCliente(doc);
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
		List<Cliente> cli = maper.consultarClientes();
		return cli;
	}

}
