package test.tollparking;

import java.util.Date;

import test.tollparking.data.Bill;
import test.tollparking.data.Slot;
import test.tollparking.utils.Utility;

/**
 * 
 * @author davide
 *
 */
public class HourPay implements PayMethod {
	private int hourPrice;
	
	public HourPay(int hourPrice) {
		this.hourPrice = hourPrice;
	}

	@Override
	public Bill pay(Slot slot) {
		return new Bill(Utility.getHour(slot.getDateIn(), new Date())*hourPrice);
	}

}
