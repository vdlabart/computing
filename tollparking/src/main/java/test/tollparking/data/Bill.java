package test.tollparking.data;

import java.io.Serializable;

/**
 * 
 * @author davide
 *
 */
public class Bill implements Serializable {
	
  private static final long serialVersionUID = 1L;
  
    private int totalAmount;

    public Bill(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "Bill [totalAmount=" + totalAmount + "]";
	}

	
  
}
