package entity;

import java.util.Arrays;

public class BookObject {
	private int sailingID;
	private String shipName;
	private int nights;
	private String[] country;

	public BookObject(int sailingID, String shipName, int nights, String[] country) {
		super();
		this.sailingID = sailingID;
		this.shipName = shipName;
		this.nights = nights;
		this.country = country;
	}


	public BookObject(int sailingID, String shipName, int nights) {
		super();
		this.sailingID = sailingID;
		this.shipName = shipName;
		this.nights = nights;
	}


	public int getSailingID() {
		return sailingID;
	}

	public void setSailingID(int sailingID) {
		this.sailingID = sailingID;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public int getNights() {
		return nights;
	}

	public void setNights(int nights) {
		this.nights = nights;
	}

	public String[] getCountry() {
		return country;
	}

	public void setCountry(String[] country) {
		this.country = country;
	}
}
