package tollparking;

import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerWrapper;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import tollparking.rest.ParkRest;
import tollparking.utils.MyObjectMapperProvider;


/**
 * Hello world!
 *
 */
public class Bootstrap 
{
	
	/**
	   * Metodo che fa partire un server http sull'indirizzo e porta specificati
	   * @param httpAddress
	   * @param httpPort
	   * @param singletons endPoints registrati sul server
	   * @return
	   * @throws Exception
	   */
	  private static Server startHttp(String httpAddress, int httpPort, final Set<Object> singletons) throws Exception {
	    
	    URI baseUri = UriBuilder.fromUri("http://"+httpAddress+"/").port(httpPort).build();
	    
	    Server server = JettyHttpContainerFactory.createServer(baseUri, new ResourceConfig()
    		.register(MyObjectMapperProvider.class)
	        .registerInstances(singletons),
	        false);
	    
	    HandlerWrapper hw = new HandlerWrapper() {
	        @Override
	        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
	            throws IOException, ServletException {
	            super.handle(target, baseRequest, request, response);
	        }
	      };
	      
        hw.setHandler(server.getHandler());
      
        server.setHandler(hw);

	    server.setStopAtShutdown(true);
	    server.start();
	    return server;
	  }
	
	
    public static void main( String[] args )
    {
    	
    	if (args.length != 1) {
	      System.err.println("No port to use. Usage: bootstrap port");
	      System.exit(1);
	    }
    	
    	ParkService.init();
    	Set<Object> endpoints = new HashSet<>();
    	endpoints.add(new ParkRest());
    	try {
			startHttp(
			        "127.0.0.1",
			        Integer.valueOf(args[0]),
			        endpoints);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}
