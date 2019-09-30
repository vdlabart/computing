package test.tollparking.data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author davide
 *
 */
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    public final CarType type;
    public final String plate;
    
    public Car(@JsonProperty("type" ) CarType type, @JsonProperty("plate" ) String plate) {
    	this.type = type;
    	this.plate = plate;
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((plate == null) ? 0 : plate.hashCode());
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
		Car other = (Car) obj;
		if (plate == null) {
			if (other.plate != null)
				return false;
		} else if (!plate.equals(other.plate))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Car [type=" + type + ", plate=" + plate + "]";
	}
	
}
