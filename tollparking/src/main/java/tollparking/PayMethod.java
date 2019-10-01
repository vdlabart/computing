package tollparking;

import tollparking.data.Bill;
import tollparking.data.Slot;

/**
 * Pay method interface
 * @author davide
 *
 */
public interface PayMethod {
	public Bill pay(Slot slot);
}
