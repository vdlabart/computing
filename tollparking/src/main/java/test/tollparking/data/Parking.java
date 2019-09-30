package test.tollparking.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import test.tollparking.PayMethod;

/**
 * 
 * @author davide
 *
 */
public class Parking implements Serializable {
	
  private static final long serialVersionUID = 1L;
  
    private Set<Slot> slots;
    private String name;
    private String code;
    private PayMethod payMethod;
    
	public Parking(String name, String code, int dieselSlot, int e20kwSlot, int e50kwSlot, PayMethod payMethod) {
		int i=0;
		this.name = name;
		this.code = code;
		slots = new HashSet<Slot>();
		for (i=0; i < dieselSlot; i++) {
			slots.add(new Slot(i, CarType.DIESEL));
		}
		for (i=dieselSlot; i < dieselSlot + e20kwSlot; i++) {
			slots.add(new Slot(i, CarType.E20KW));
		}
		for (i=dieselSlot; i < dieselSlot + e20kwSlot; i++) {
			slots.add(new Slot(i, CarType.E50KW));
		}
		this.payMethod = payMethod;
	}

	public Set<Slot> getSlots() {
		return slots;
	}

	public void setSlots(Set<Slot> slots) {
		this.slots = slots;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public PayMethod getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(PayMethod payMethod) {
		this.payMethod = payMethod;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((slots == null) ? 0 : slots.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parking other = (Parking) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (slots == null) {
			if (other.slots != null)
				return false;
		} else if (!slots.equals(other.slots))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Parking [slots=" + slots + ", name=" + name + ", code=" + code + "]";
	} 
	
  
}
