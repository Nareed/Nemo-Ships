package entity;

import Utils.E_RoomType;

public class Room {
	private int cruiseShipID;
	private int roomNumber;
	private String bedsAmount;
	private E_RoomType roomType;
	private int price;

	public Room(int cruiseShipID, int roomNumber, String bedsAmount, E_RoomType roomType, int price) {
		super();
		this.cruiseShipID = cruiseShipID;
		this.roomNumber = roomNumber;
		this.bedsAmount = bedsAmount;
		this.roomType = roomType;
		this.price = price;
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
//changed from char to string
	public String getBedsAmount() {
		return bedsAmount;
	}

	public void setBedsAmount(String bedsAmount) {
		this.bedsAmount = bedsAmount;
	}

	public E_RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(E_RoomType roomType) {
		this.roomType = roomType;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bedsAmount == null) ? 0 : bedsAmount.hashCode());
		result = prime * result + cruiseShipID;
		result = prime * result + price;
		result = prime * result + roomNumber;
		result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
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
		Room other = (Room) obj;
		if (bedsAmount == null) {
			if (other.bedsAmount != null)
				return false;
		} else if (!bedsAmount.equals(other.bedsAmount))
			return false;
		if (cruiseShipID != other.cruiseShipID)
			return false;
		if (price != other.price)
			return false;
		if (roomNumber != other.roomNumber)
			return false;
		if (roomType != other.roomType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Room [cruiseShipID=" + cruiseShipID + ", roomNumber=" + roomNumber + ", bedsAmount=" + bedsAmount
				+ ", roomType=" + roomType + ", price=" + price + "]";
	}


}
