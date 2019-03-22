package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.sampleprj.dao.UnsupportedOperationException;
import edu.eci.cvds.sampleprj.dao.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.util.List;

@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {

   @Inject
   private ItemDAO itemDAO;
   @Inject
   private ClienteDAO clienteDAO;

   @Override
   public int valorMultaRetrasoxDia(int itemId) throws UnsupportedOperationException {
	   
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
       Cliente cli =  clienteDAO.getCliente(docu);
       if(cli==null) throw new ExcepcionServiciosAlquiler("No existe Cliente.");
       return cli;
   }
   

   @Override
   public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }
   @Override
   public List<Cliente> consultarClientes(){
       return clienteDAO.consultarClientes();
   }

   @Override
   public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
       try {
           return itemDAO.load(id);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id,ex);
       }
   }

   @Override
   public List<Item> consultarItemsDisponibles() throws UnsupportedOperationException {
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public void registrarAlquilerCliente(Date fechaEntrega, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
	   consultarCliente(docu);
	   consultarItem(item);
       itemDAO.registrarAlquilerCliente(fechaEntrega,docu,item,numdias);
   }

   private Item consultarItem(Item item) throws ExcepcionServiciosAlquiler {
	Item it = itemDAO.consultarItem(item.getId());
	if(it==null) throw new ExcepcionServiciosAlquiler("No existe este item");
	return it;
	
}

@Override
   public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
	   boolean existenClientes = false;
	   try {
		   consultarCliente(c.getDocumento());
		   existenClientes = true;
	   }
	   catch(ExcepcionServiciosAlquiler e) {
		   try {
			   consultarCliente(c.getEmail());
			   existenClientes = true;
		   }
		   catch(ExcepcionServiciosAlquiler f) {
			   clienteDAO.agregarCliente(c);   
		   }
	   }
	   if(existenClientes) throw new ExcepcionServiciosAlquiler("Ya existe este cliente");
   }

   @Override
   public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }
   @Override
   public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

@Override
public List<ItemRentado> consultarItemNoEntregados(Cliente c) {
	return itemDAO.consultarItemNoEntregados(c);
}

@Override
public Cliente consultarCliente(String correo) throws ExcepcionServiciosAlquiler {
	Cliente c = null;
	c = clienteDAO.getCliente(correo);
	if(c==null) throw new ExcepcionServiciosAlquiler("No existe Cliente.");
	return c;
}








}