package entity;

import Utils.E_RoomType;

public class TableItem {

	int roomNum;
	String type;
	String numOfBeds;
	int price;
	int criuseShipID;
	public TableItem(int roomNum, String type, String numOfBeds, int price, int criuseShipID) {
		super();
		this.roomNum = roomNum;
		this.type = type;
		this.numOfBeds = numOfBeds;
		this.price = price;
		this.criuseShipID = criuseShipID;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumOfBeds() {
		return numOfBeds;
	}
	public void setNumOfBeds(String numOfBeds) {
		this.numOfBeds = numOfBeds;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCriuseShipID() {
		return criuseShipID;
	}
	public void setCriuseShipID(int criuseShipID) {
		this.criuseShipID = criuseShipID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + criuseShipID;
		result = prime * result + ((numOfBeds == null) ? 0 : numOfBeds.hashCode());
		result = prime * result + price;
		result = prime * result + roomNum;
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
		TableItem other = (TableItem) obj;
		if (criuseShipID != other.criuseShipID)
			return false;
		if (numOfBeds == null) {
			if (other.numOfBeds != null)
				return false;
		} else if (!numOfBeds.equals(other.numOfBeds))
			return false;
		if (price != other.price)
			return false;
		if (roomNum != other.roomNum)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TableItem [roomNum=" + roomNum + ", type=" + type + ", numOfBeds=" + numOfBeds + ", price=" + price
				+ ", criuseShipID=" + criuseShipID + "]";
	}





}
