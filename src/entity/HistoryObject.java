package entity;

import java.util.Date;

public class HistoryObject {
	private Date staringDate;
	private Date returnDate;
	private String shipName;
	private int roomNumber;
	private double price;
	/**
	 * @param staringDate
	 * @param returnDate
	 * @param shipName
	 * @param roomNumber
	 * @param price
	 */
	public HistoryObject(Date staringDate, Date returnDate, String shipName, int roomNumber, double price) {
		super();
		this.staringDate = staringDate;
		this.returnDate = returnDate;
		this.shipName = shipName;
		this.roomNumber = roomNumber;
		this.price = price;
	}
	public Date getStaringDate() {
		return staringDate;
	}
	public void setStaringDate(Date staringDate) {
		this.staringDate = staringDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
		result = prime * result + roomNumber;
		result = prime * result + ((shipName == null) ? 0 : shipName.hashCode());
		result = prime * result + ((staringDate == null) ? 0 : staringDate.hashCode());
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
		HistoryObject other = (HistoryObject) obj;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (returnDate == null) {
			if (other.returnDate != null)
				return false;
		} else if (!returnDate.equals(other.returnDate))
			return false;
		if (roomNumber != other.roomNumber)
			return false;
		if (shipName == null) {
			if (other.shipName != null)
				return false;
		} else if (!shipName.equals(other.shipName))
			return false;
		if (staringDate == null) {
			if (other.staringDate != null)
				return false;
		} else if (!staringDate.equals(other.staringDate))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "HistoryObject [staringDate=" + staringDate + ", returnDate=" + returnDate + ", shipName=" + shipName
				+ ", roomNumber=" + roomNumber + ", price=" + price + "]";
	}


}
