package entity;

import java.sql.Date;

public class SailTo {
	private String countryName;
	private String portName;
	private int sailingID;
	private Date arrivalTime;
	private Date leavingTime;

	public SailTo(String countryName, String portName, int sailingID, Date arrivalTime, Date leavingTime) {
		super();
		this.countryName = countryName;
		this.portName = portName;
		this.sailingID = sailingID;
		this.arrivalTime = arrivalTime;
		this.leavingTime = leavingTime;
	}

	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getPortName() {
		return portName;
	}
	public void setPortName(String portName) {
		this.portName = portName;
	}
	public int getSailingID() {
		return sailingID;
	}
	public void setSailingID(int sailingID) {
		this.sailingID = sailingID;
	}
	public Date getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Date getLeavingTime() {
		return leavingTime;
	}
	public void setLeavingTime(Date leavingTime) {
		this.leavingTime = leavingTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalTime == null) ? 0 : arrivalTime.hashCode());
		result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
		result = prime * result + ((leavingTime == null) ? 0 : leavingTime.hashCode());
		result = prime * result + ((portName == null) ? 0 : portName.hashCode());
		result = prime * result + sailingID;
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
		SailTo other = (SailTo) obj;
		if (arrivalTime == null) {
			if (other.arrivalTime != null)
				return false;
		} else if (!arrivalTime.equals(other.arrivalTime))
			return false;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equals(other.countryName))
			return false;
		if (leavingTime == null) {
			if (other.leavingTime != null)
				return false;
		} else if (!leavingTime.equals(other.leavingTime))
			return false;
		if (portName == null) {
			if (other.portName != null)
				return false;
		} else if (!portName.equals(other.portName))
			return false;
		if (sailingID != other.sailingID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SailTo [countryName=" + countryName + ", portName=" + portName + ", sailingID=" + sailingID
				+ ", arrivalTime=" + arrivalTime + ", leavingTime=" + leavingTime + "]";
	}
}
