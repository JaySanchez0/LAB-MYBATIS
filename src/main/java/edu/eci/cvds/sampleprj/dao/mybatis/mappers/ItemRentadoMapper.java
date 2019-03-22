package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;

public interface ItemRentadoMapper {
	public List<ItemRentado> consultarItemNoEntregados(@Param("cliente") Cliente c);
	public void registrarAlquilerCliente(@Param("date") java.util.Date date,@Param("documento") long docu,@Param("item") Item item,@Param("dias") int numdias);
	public ItemRentado getItemRentado(@Param("id") int idItem);
}