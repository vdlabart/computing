package test.tollparking;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import test.tollparking.data.Bill;
import test.tollparking.data.Car;
import test.tollparking.data.Parking;
import test.tollparking.data.Slot;

/**
 * 
 * @author davide
 *
 */
public class ParkService {
   
	private static Map<String, Parking> PARKING_MAP = null;
	
	private ParkService() { 
	} 

	public static void init() 
	{     	
		if (PARKING_MAP == null) {
			PARKING_MAP = new HashMap<String, Parking>();
	        Parking park1 = new Parking("Parking 1","PRK1", 10, 20, 30, new HourPay(2));
	        Parking park2 = new Parking("Parking 2","PRK2", 10, 20, 30, new FixedHourPay(2,3));
	        Parking park3 = new Parking("Parking 3","PRK3", 10, 20, 30, new HourPay(2));
	        PARKING_MAP.put("PRK1", park1);
	        PARKING_MAP.put("PRK2", park2);
	        PARKING_MAP.put("PRK3", park3);
		}
	} 
	
	public static void printPark() {
		PARKING_MAP.forEach((k,v) -> System.out.println(k + " " + v)); ;
	}
	
	/**
	 * 
	 * @param parkCode
	 * @param type
	 */
	public static Slot checkPark(String parkCode, Car car) {
		Slot slotToFind = null;

		if (!PARKING_MAP.containsKey(parkCode)) {
			return slotToFind;
		}
		
		Parking p = PARKING_MAP.get(parkCode);
		
		synchronized (p) {
			try {
				slotToFind = p.getSlots().stream() //Set<Slot>
				.filter(slot -> {
					if (slot.getCar() == null && slot.getType().equals(car.type)) {
						return true;
					}
					return false;
				}).findAny().get(); //.collect(Collectors.toSet());
				slotToFind.setDateIn(new Date());
				slotToFind.setCar(car);
				PARKING_MAP.get(parkCode).getSlots().add(slotToFind);
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		
		return slotToFind;
	}
	
	
	/**
	 * 
	 * @param parkCode
	 * @param type
	 */
	public static Bill checkOutPark(String parkCode, Car car) {
		Slot slotToFind = null;
		Bill bill = null;
		if (!PARKING_MAP.containsKey(parkCode)) {
			return bill;
		}
		
		try {
			slotToFind = PARKING_MAP.get(parkCode).getSlots().stream() //Set<Slot>
			.filter(slot -> {
				if (car.equals(slot.getCar())) {
					return true;
				}
				return false;
			}).findFirst().get();
			slotToFind.setCar(null);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return PARKING_MAP.get(parkCode).getPayMethod().pay(slotToFind);
	}
	
	
	
}