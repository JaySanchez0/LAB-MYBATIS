package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

import edu.eci.cvds.samples.entities.Cliente;

/**
 *
 * @author 2106913
 */
public interface ClienteMapper {
    
    public Cliente consultarCliente(@Param("idcli") long id); 
    
    /**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'idi'
     * @param id
     * @param idit
     * @param fechainicio
     * @param fechafin 
     */
    public void agregarItemRentadoACliente(@Param("idc") int id , 
            @Param("iditem") int idit, 
            @Param("inicio") Date fechainicio,
            @Param("fin") Date fechafin);

    /**
     * Consultar todos los clientes
     * @return 
     */
    public List<Cliente> consultarClientes();
    @Transactional
    public void registrarCliente(@Param("documento") long documento,@Param("nombre") String nombre,@Param("telefono") String telefono,
    		@Param("direccion") String direccion,@Param("email") String email);

	public void agregarCliente(@Param("cliente") Cliente c);
    
}
