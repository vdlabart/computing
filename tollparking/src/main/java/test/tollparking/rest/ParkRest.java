package test.tollparking.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import test.tollparking.ParkService;
import test.tollparking.data.Bill;
import test.tollparking.data.Car;
import test.tollparking.data.Slot;

@Path("park")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ParkRest {

  /*********************************
   ** CONSTRUCTOR & "DESTRUCTOR" ***
   *********************************/

  public ParkRest() {
	  
  }


  /*** PUT ***/
  @PUT
  @Path("/checkIn/{parkCode}")
  public Slot checkIn(@PathParam("parkCode") String parkCode, Car car) {
	  Slot slot = ParkService.checkPark(parkCode, car);
	  if (slot == null) {
		  throw new WebApplicationException(Status.NOT_FOUND);
	  }
	  return slot;
  }

  /*** PUT ***/
  @PUT
  @Path("/checkOut/{parkCode}")
  public Bill checkOut(@PathParam("parkCode") String parkCode, Car car) {
	  Bill bill = ParkService.checkOutPark(parkCode, car);
	  if (bill == null) {
		  throw new WebApplicationException(Status.NOT_FOUND);
	  }
	  return bill;
  }

  
}
