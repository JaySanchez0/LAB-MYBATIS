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
        qt().forAll(longs().from(1).upTo(1000)).check((documento) -> {
            boolean r = false;
            try {
                Cliente cliente = serviciosAlquiler.consultarCliente(documento);
            } catch(IndexOutOfBoundsException e) {
                r = false;
            } catch (edu.eci.cvds.sampleprj.dao.ExcepcionServiciosAlquiler e) {
				r= true;
			}
            return r;
        });
    }
    public Gen<Cliente> clientes(){
    	return integers().allPositive().zip(strings().allPossible().ofLength(7), (doc,nombre) -> new Cliente(nombre,doc,"none","none","none"));
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
    public void noDeberiaPermitirInsertarClientesConIgualDocumento() {
    	qt().forAll(clientesArray()).check(client->{
    		for(Cliente c: client) {
				try {
					serviciosAlquiler.registrarCliente(c);
				} catch (edu.eci.cvds.sampleprj.dao.ExcepcionServiciosAlquiler e) {
					// TODO Auto-generated catch block
					return true;
				}
			}
			try {
				serviciosAlquiler.registrarCliente(client[0]);
			} catch (edu.eci.cvds.sampleprj.dao.ExcepcionServiciosAlquiler e) {
				return true;
			}
				return false;
    	});
    }
    	@Test
    	public void deberiaAgregarUnCliente() {
    		qt().forAll(clientes()).check(c->{
    			try {
    				serviciosAlquiler.registrarCliente(c);
    				return serviciosAlquiler.consultarCliente(c.getDocumento())!=null;
    			}
    			catch(edu.eci.cvds.sampleprj.dao.ExcepcionServiciosAlquiler e) {
    				return true;
    			}
    		});
    	}
    	
}