package tollparking;

import java.util.Date;

import tollparking.data.Bill;
import tollparking.data.Slot;
import tollparking.utils.Utility;

/**
 * Payment by hour
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
