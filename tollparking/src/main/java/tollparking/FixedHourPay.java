package tollparking;

import java.util.Date;

import tollparking.data.Bill;
import tollparking.data.Slot;
import tollparking.utils.Utility;

/**
 * Payment by hour + fixed amout
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
