package test.tollparking;

import test.tollparking.data.Bill;
import test.tollparking.data.Slot;

/**
 * 
 * @author davide
 *
 */
public interface PayMethod {
	public Bill pay(Slot slot);
}
