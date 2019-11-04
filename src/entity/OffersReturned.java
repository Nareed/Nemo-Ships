package entity;

public class OffersReturned {
    int sailingid;
    String leavingtime;
    String returntime;
    String shipname;
	public OffersReturned(int sailingid, String leavingtime, String returntime, String shipname) {
		super();
		this.sailingid = sailingid;
		this.leavingtime = leavingtime;
		this.returntime = returntime;
		this.shipname = shipname;
	}
	public int getSailingid() {
		return sailingid;
	}
	public void setSailingid(int sailingid) {
		this.sailingid = sailingid;
	}
	public String getLeavingtime() {
		return leavingtime;
	}
	public void setLeavingtime(String leavingtime) {
		this.leavingtime = leavingtime;
	}
	public String getReturntime() {
		return returntime;
	}
	public void setReturntime(String returntime) {
		this.returntime = returntime;
	}
	public String getShipname() {
		return shipname;
	}
	public void setShipname(String shipname) {
		this.shipname = shipname;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((leavingtime == null) ? 0 : leavingtime.hashCode());
		result = prime * result + ((returntime == null) ? 0 : returntime.hashCode());
		result = prime * result + sailingid;
		result = prime * result + ((shipname == null) ? 0 : shipname.hashCode());
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
		OffersReturned other = (OffersReturned) obj;
		if (leavingtime == null) {
			if (other.leavingtime != null)
				return false;
		} else if (!leavingtime.equals(other.leavingtime))
			return false;
		if (returntime == null) {
			if (other.returntime != null)
				return false;
		} else if (!returntime.equals(other.returntime))
			return false;
		if (sailingid != other.sailingid)
			return false;
		if (shipname == null) {
			if (other.shipname != null)
				return false;
		} else if (!shipname.equals(other.shipname))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OffersReturned [sailingid=" + sailingid + ", leavingtime=" + leavingtime + ", returntime=" + returntime
				+ ", shipname=" + shipname + "]";
	}




}
