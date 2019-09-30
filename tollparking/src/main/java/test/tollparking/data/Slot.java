package test.tollparking.data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author davide
 *
 */
public class Slot implements Serializable {
	
  private static final long serialVersionUID = 1L;
  
    private int number;
    private CarType type;
    private Car car;
    private Date dateIn;

	public Slot(int number, CarType type) {
		this.number = number;
		this.type = type;
		this.car = null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		result = prime * result + number;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Slot other = (Slot) obj;
		if (number != other.number)
			return false;
		return true;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public CarType getType() {
		return type;
	}

	public void setType(CarType type) {
		this.type = type;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	public Date getDateIn() {
		return dateIn;
	}
	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	@Override
	public String toString() {
		return "Slot [number=" + number + ", type=" + type + ", car=" + car + ", dateIn=" + dateIn + "]";
	}
	
  
}
