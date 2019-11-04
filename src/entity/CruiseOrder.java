package entity;

public class CruiseOrder {
	private int cruiseID;
	private int cruiseShipID;
	private int roomNumber;
	private String personID;

	public CruiseOrder(int cruiseID, int cruiseShipID, int roomNumber, String personID) {
		super();
		this.cruiseID = cruiseID;
		this.cruiseShipID = cruiseShipID;
		this.roomNumber = roomNumber;
		this.personID = personID;
	}

	public int getCruiseID() {
		return cruiseID;
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

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getPersonID() {
		return personID;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cruiseID;
		result = prime * result + cruiseShipID;
		result = prime * result + ((personID == null) ? 0 : personID.hashCode());
		result = prime * result + roomNumber;
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
		CruiseOrder other = (CruiseOrder) obj;
		if (cruiseID != other.cruiseID)
			return false;
		if (cruiseShipID != other.cruiseShipID)
			return false;
		if (personID == null) {
			if (other.personID != null)
				return false;
		} else if (!personID.equals(other.personID))
			return false;
		if (roomNumber != other.roomNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CruiseOrder [cruiseID=" + cruiseID + ", cruiseShipID=" + cruiseShipID + ", roomNumber=" + roomNumber
				+ ", personID=" + personID + "]";
	}

}
