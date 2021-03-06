package tollparking.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import tollparking.ParkService;
import tollparking.data.Bill;
import tollparking.data.Car;
import tollparking.data.Slot;

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
  /**
   * Check in park
   * @param parkCode park to enter
   * @param car car to check in
   * @return @Slot if one is available, NOT_FOUND web response otherwise
   */
  public Slot checkIn(@PathParam("parkCode") String parkCode, Car car) {
	  if (car.plate.isEmpty() ||  car.plate == null ||
			  car.type == null || parkCode.isEmpty() || parkCode == null) {
		  throw new WebApplicationException(Status.BAD_REQUEST);
	  }
	  Slot slot = ParkService.checkPark(parkCode, car);
	  if (slot == null) {
		  throw new WebApplicationException(Status.NOT_FOUND);
	  }
	  return slot;
  }

  /*** PUT ***/
  @PUT
  @Path("/checkOut/{parkCode}")
  /**
   * Check out park
   * @param parkCode park to leave
   * @param car car to check out
   * @return @Bill
   */
  public Bill checkOut(@PathParam("parkCode") String parkCode, Car car) {
	  if (car.plate.isEmpty() ||  car.plate == null ||
			  car.type == null || parkCode.isEmpty() || parkCode == null) {
		  throw new WebApplicationException(Status.BAD_REQUEST);
	  }
	  Bill bill = ParkService.checkOutPark(parkCode, car);
	  if (bill == null) {
		  throw new WebApplicationException(Status.NOT_FOUND);
	  }
	  return bill;
  }

  
}
