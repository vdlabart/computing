package tollparking;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import tollparking.data.Bill;
import tollparking.data.Car;
import tollparking.data.Parking;
import tollparking.data.Slot;

/**
 * Class with a static map of parking: <br>
 * - "Parking 1" with code "PRK1" has 10 dieselSlot, 20 e20kwSlot, 30 e50kwSlot Pay for hour<br>
 * - "Parking 2" with code "PRK2" has 15 dieselSlot, 10 e20kwSlot, 15 e50kwSlot Pay for hour + fixed amount<br>
 * - "Parking 3" with code "PRK3" has 25 dieselSlot, 15 e20kwSlot, 10 e50kwSlot Pay for hour<br>
 * @author davide
 *
 */
public class ParkService {
   
	private static Map<String, Parking> PARKING_MAP = null;
	
	private ParkService() { 
	} 

	/**
	 * Initialize the park service with 3 example park
	 */
	public static void init() 
	{     	
		if (PARKING_MAP == null) {
			PARKING_MAP = new HashMap<String, Parking>();
	        Parking park1 = new Parking("Parking 1","PRK1", 10, 20, 30, new HourPay(2));
	        Parking park2 = new Parking("Parking 2","PRK2", 15, 10, 15, new FixedHourPay(2,3));
	        Parking park3 = new Parking("Parking 3","PRK3", 25, 15, 10, new HourPay(2));
	        PARKING_MAP.put("PRK1", park1);
	        PARKING_MAP.put("PRK2", park2);
	        PARKING_MAP.put("PRK3", park3);
		}
	} 
	
	/**
	 * Check if there is an empty slot in a specific park for the @CarType of the @Car
	 * @param parkCode park to enter
	 * @param car car to check in
	 * @return Slot if one is empty, null otherwise
	 */
	public static Slot checkPark(String parkCode, Car car) {
		Slot slotToFind = null;

		if (!PARKING_MAP.containsKey(parkCode)) {
			return slotToFind;
		}
		
		Parking p = PARKING_MAP.get(parkCode);
		
		// check if a car is already present
		Set<Slot> listSlot = PARKING_MAP.get(parkCode).getSlots().stream()
				.filter(slot -> {
					if (car.equals(slot.getCar())) {
						return true;
					}
					return false;
				}).collect(Collectors.toSet());
		
		if (listSlot.size() == 1) {
			slotToFind = listSlot.iterator().next();
		}else {
		
			synchronized (p) {
				try {
					slotToFind = p.getSlots().stream()
				    .filter(slot -> {
						if (slot.getCar() == null && slot.getType().equals(car.type)) {
							return true;
						}
						return false;
					}).findAny().get(); 
					slotToFind.setDateIn(new Date());
					slotToFind.setCar(car);
					PARKING_MAP.get(parkCode).getSlots().add(slotToFind);
				} catch (NoSuchElementException e) {
					e.printStackTrace();
				}
			}
		}
		
		return slotToFind;
	}
	
	
	/**
	 * Given a specific park and car, calculate the bill
	 * @param parkCode park to leave
	 * @param car car to check out
	 * @return Bill with amount of money to pay, null if the car is not in this park
	 */
	public static Bill checkOutPark(String parkCode, Car car) {
		Slot slotToFind = null;
		Bill bill = null;
		if (PARKING_MAP.containsKey(parkCode)) {
			try {
				slotToFind = PARKING_MAP.get(parkCode).getSlots().stream()
				.filter(slot -> {
					if (car.equals(slot.getCar())) {
						return true;
					}
					return false;
				}).findFirst().get();
				slotToFind.setCar(null);
				bill = PARKING_MAP.get(parkCode).getPayMethod().pay(slotToFind);
			} catch (NoSuchElementException e) {
				// car no present in the park! 
			}
		}
		return bill;
		
	}
	
	
	
}