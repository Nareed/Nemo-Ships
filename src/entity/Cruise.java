package entity;

public class Cruise {
	private int cruiseID;
	private int cruiseShipID;

	public int getCruiseID() {
		return cruiseID;
	}

	public Cruise(int cruiseID, int cruiseShipID) {
		super();
		this.cruiseID = cruiseID;
		this.cruiseShipID = cruiseShipID;
	}

	public void setCruiseID(int cruiseID) {
		this.cruiseID = cruiseID;
	}

	public int getCruiseShipID() {
		return cruiseShipID;
	}

	public void setCruiseShipID(int cruiseShipID) {
		this.cruiseShipID = cruiseShipID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cruiseID;
		result = prime * result + cruiseShipID;
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
		Cruise other = (Cruise) obj;
		if (cruiseID != other.cruiseID)
			return false;
		if (cruiseShipID != other.cruiseShipID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cruise [cruiseID=" + cruiseID + ", cruiseShipID=" + cruiseShipID + "]";
	}

}
