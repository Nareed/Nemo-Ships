package entity;

import java.sql.Date;

public class Sailing {
	private int sailingID;
	private Date leavingTime;
	private Date returnTime;

	public Sailing(int sailingID, Date leavingTime, Date returnTime) {
		super();
		this.sailingID = sailingID;
		this.leavingTime = leavingTime;
		this.returnTime = returnTime;
	}

	public int getSailingID() {
		return sailingID;
	}
	public void setSailingID(int sailingID) {
		this.sailingID = sailingID;
	}
	public Date getLeavingTime() {
		return leavingTime;
	}
	public void setLeavingTime(Date leavingTime) {
		this.leavingTime = leavingTime;
	}
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((leavingTime == null) ? 0 : leavingTime.hashCode());
		result = prime * result + ((returnTime == null) ? 0 : returnTime.hashCode());
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
		Sailing other = (Sailing) obj;
		if (leavingTime == null) {
			if (other.leavingTime != null)
				return false;
		} else if (!leavingTime.equals(other.leavingTime))
			return false;
		if (returnTime == null) {
			if (other.returnTime != null)
				return false;
		} else if (!returnTime.equals(other.returnTime))
			return false;
		if (sailingID != other.sailingID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Sailing [sailingID=" + sailingID + ", leavingTime=" + leavingTime + ", returnTime=" + returnTime + "]";
	}

}
