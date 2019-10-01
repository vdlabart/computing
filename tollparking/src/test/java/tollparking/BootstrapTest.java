package tollparking;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.junit.Test;

import tollparking.data.Bill;
import tollparking.data.Car;
import tollparking.data.CarType;
import tollparking.utils.MyObjectMapperProvider;

/**
 * Unit test for simple App.
 */
public class BootstrapTest 
{
	
	static final Client client;
	static final String port = "8180";
	
	static {    
	    ClientConfig cc = new ClientConfig();
	    cc.register(MyObjectMapperProvider.class);
	    cc.property(ClientProperties.CONNECT_TIMEOUT, 3000);
	    cc.property(ClientProperties.READ_TIMEOUT, 20000);
	    client = ClientBuilder.newClient(cc);
	    Bootstrap.main(new String[]{port});
	  }
	    
    @Test
    public void checkIn()
    {
    	Car car = new Car(CarType.DIESEL, "AX981BY");
		Response resp = client.target("http://127.0.0.1:" + port + "/park/checkIn/PRK1")
      	      .request()
      	      .put(Entity.entity(car, MediaType.APPLICATION_JSON));
        	    
	    assertEquals(200, resp.getStatus());
    }
    
    @Test
    public void checkOutOneHour()
    {
    	
    	Car car = new Car(CarType.DIESEL, "AX981BY");
		Response resp = client.target("http://127.0.0.1:" + port + "/park/checkIn/PRK1")
      	      .request()
      	      .put(Entity.entity(car, MediaType.APPLICATION_JSON));
    	
    	assertEquals(200, resp.getStatus());
    	
        resp = client.target("http://127.0.0.1:" + port + "/park/checkOut/PRK1")
    			 .request()
         	     .put(Entity.entity(car, MediaType.APPLICATION_JSON));
        	    
	    assertEquals(200, resp.getStatus());
	    
	    Bill a = (Bill) resp.readEntity(Bill.class);
	    assertEquals(2,  a.getTotalAmount());
    }
    
    
}
