package edu.eci.cvds.test;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.quicktheories.core.Gen;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.Generate.*;
import static org.quicktheories.generators.SourceDSL.*;

public class ServiciosAlquilerTest {

    @Inject
    private SqlSession sqlSession;

    ServiciosAlquiler serviciosAlquiler;

    public ServiciosAlquilerTest() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    }

    @Before
    public void setUp() {
    }

    @Test
    public void emptyDB() {
        qt().forAll(longs().from(1).upTo(100)).check((documento) -> {
            boolean r = false;
            try {
                Cliente cliente = serviciosAlquiler.consultarCliente(documento);
            }
            catch (edu.eci.cvds.sampleprj.dao.ExcepcionServiciosAlquiler e) {
				r= true;
			} 
            return r;
        });
    }
    public Gen<Cliente> clientes(){
    	return integers().allPositive().zip(strings().allPossible().ofLength(7), (doc,nombre) -> new Cliente(nombre,doc,"juan","hjsh","hghsg"));
    }
    public Gen<Cliente[]> clientesArray(){
    	Gen<Cliente[]> cliente = hk->{
    		Cliente[] arr = new Cliente[7];
    		for(int i=0;i<7;i++) {
    			arr[i] = clientes().generate(hk);
    		}
    		return arr;
    		
    	};
    	return cliente;
    }
    	@Test
    	public void deberiaAgregarUnClienteQueNoExista() {
    		qt().forAll(clientes()).check(c->{
    			try {
    				serviciosAlquiler.registrarCliente(c);
    				return serviciosAlquiler.consultarCliente(c.getDocumento())!=null;
    			}
    			catch(edu.eci.cvds.sampleprj.dao.ExcepcionServiciosAlquiler e) {
    				return e.getMessage().equals("Ya existe este cliente");
    			}
    		});
    	}
    	@Test
    	public void nodeberiaPermitirIngresarClientesRepetidos() {
    		qt().forAll(clientesArray()).check(cli ->{
    			for(Cliente c:cli) {
    				try {
        				serviciosAlquiler.registrarCliente(c);
        			}
    				catch(edu.eci.cvds.sampleprj.dao.ExcepcionServiciosAlquiler e) {
        			}
    			}
    			try {
    				serviciosAlquiler.registrarCliente(cli[0]);
    				return false;
    			}
				catch(edu.eci.cvds.sampleprj.dao.ExcepcionServiciosAlquiler e) {
					return true;
    			}
    			
    		});
    	}
    	
}