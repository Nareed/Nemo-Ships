package entity;

import java.sql.Date;

public class CruiseShip {
	private int cruiseShipID;
	private String shipName;
	private Date manufacturingDate;
	private int maxCapacity;
	private int maxNumberOfPeople;
	public CruiseShip(int cruiseShipID, String shipName, Date manufacturingDate, int maxCapacity,
			int maxNumberOfPeople) {
		super();
		this.cruiseShipID = cruiseShipID;
		this.shipName = shipName;
		this.manufacturingDate = manufacturingDate;
		this.maxCapacity = maxCapacity;
		this.maxNumberOfPeople = maxNumberOfPeople;
	}
	public int getCruiseShipID() {
		return cruiseShipID;
	}
	public void setCruiseShipID(int cruiseShipID) {
		this.cruiseShipID = cruiseShipID;
	}
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public Date getManufacturingDate() {
		return manufacturingDate;
	}
	public void setManufacturingDate(Date manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}
	public int getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	public int getMaxNumberOfPeople() {
		return maxNumberOfPeople;
	}
	public void setMaxNumberOfPeople(int maxNumberOfPeople) {
		this.maxNumberOfPeople = maxNumberOfPeople;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cruiseShipID;
		result = prime * result + ((manufacturingDate == null) ? 0 : manufacturingDate.hashCode());
		result = prime * result + maxCapacity;
		result = prime * result + maxNumberOfPeople;
		result = prime * result + ((shipName == null) ? 0 : shipName.hashCode());
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
		CruiseShip other = (CruiseShip) obj;
		if (cruiseShipID != other.cruiseShipID)
			return false;
		if (manufacturingDate == null) {
			if (other.manufacturingDate != null)
				return false;
		} else if (!manufacturingDate.equals(other.manufacturingDate))
			return false;
		if (maxCapacity != other.maxCapacity)
			return false;
		if (maxNumberOfPeople != other.maxNumberOfPeople)
			return false;
		if (shipName == null) {
			if (other.shipName != null)
				return false;
		} else if (!shipName.equals(other.shipName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CruiseShip [cruiseShipID=" + cruiseShipID + ", shipName=" + shipName + ", manufacturingDate="
				+ manufacturingDate + ", maxCapacity=" + maxCapacity + ", maxNumberOfPeople=" + maxNumberOfPeople + "]";
	}



}
