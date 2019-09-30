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
public class FixedHourPay implements PayMethod {
	private int fixedAmt;
	private int hourPrice;
	
	public FixedHourPay(int fixedAmt, int hourPrice) {
		super();
		this.fixedAmt = fixedAmt;
		this.hourPrice = hourPrice;
	}

	@Override
	public Bill pay(Slot slot) {
		return new Bill(fixedAmt + Utility.getHour(slot.getDateIn(), new Date())*hourPrice);
	}

}
