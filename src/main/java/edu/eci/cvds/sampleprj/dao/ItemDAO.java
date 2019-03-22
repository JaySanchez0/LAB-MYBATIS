package edu.eci.cvds.sampleprj.dao;


import java.sql.Date;
import java.util.List;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;

public interface ItemDAO {

   public void save(Item it) throws PersistenceException;

   public Item load(int id) throws PersistenceException;

   public List<ItemRentado> consultarItemNoEntregados(Cliente c);

   public void registrarAlquilerCliente(Date fechaEntrega, long docu, Item item, int numdias);

   public Item consultarItem(int id);

}