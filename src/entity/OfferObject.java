package entity;

import javafx.scene.image.Image;

public class OfferObject {
	private String sailingID;
	private String shipname;
	private String deparCountry;
	private String deparPort;
	private String form;
	private int nights;
	private int last;
	private Image img;
	Image[] imageArrayList = { new Image("Media/offer1.jpg"), new Image("Media/offer2.jpg"),
			new Image("Media/offer3.jpg"), new Image("Media/offer4.jpg"), new Image("Media/offer5.jpg"),
			new Image("Media/offer6.jpg"), new Image("Media/offer7.jpg"), new Image("Media/offer8.jpg") };
	public static int slideshowCount = 0;

	public OfferObject(String sailingID, String shipname, String deparCountry, String deparPort, String form,
			int nights, int last) {
		super();
		this.sailingID = sailingID;
		this.shipname = shipname;
		this.deparCountry = deparCountry;
		this.deparPort = deparPort;
		this.form = form;
		this.nights = nights;
		this.last = last;
		addIMG();
	}

	private void addIMG() {
		slideshowCount++;
		if (slideshowCount >= imageArrayList.length) {
			slideshowCount = 0;
		}
		this.img = imageArrayList[slideshowCount];
	}

	public String getSailingID() {
		return sailingID;
	}

	public void setSailingID(String sailingID) {
		this.sailingID = sailingID;
	}

	public String getShipname() {
		return shipname;
	}

	public void setShipname(String shipname) {
		this.shipname = shipname;
	}

	public String getDeparCountry() {
		return deparCountry;
	}

	public void setDeparCountry(String deparCountry) {
		this.deparCountry = deparCountry;
	}

	public String getDeparPort() {
		return deparPort;
	}

	public void setDeparPort(String deparPort) {
		this.deparPort = deparPort;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public int getNights() {
		return nights;
	}

	public void setNights(int nights) {
		this.nights = nights;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public Image[] getImageArrayList() {
		return imageArrayList;
	}

	public void setImageArrayList(Image[] imageArrayList) {
		this.imageArrayList = imageArrayList;
	}

	public static int getSlideshowCount() {
		return slideshowCount;
	}

	public static void setSlideshowCount(int slideshowCount) {
		OfferObject.slideshowCount = slideshowCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deparCountry == null) ? 0 : deparCountry.hashCode());
		result = prime * result + ((deparPort == null) ? 0 : deparPort.hashCode());
		result = prime * result + ((form == null) ? 0 : form.hashCode());
		result = prime * result + last;
		result = prime * result + nights;
		result = prime * result + ((sailingID == null) ? 0 : sailingID.hashCode());
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
		OfferObject other = (OfferObject) obj;
		if (deparCountry == null) {
			if (other.deparCountry != null)
				return false;
		} else if (!deparCountry.equals(other.deparCountry))
			return false;
		if (deparPort == null) {
			if (other.deparPort != null)
				return false;
		} else if (!deparPort.equals(other.deparPort))
			return false;
		if (form == null) {
			if (other.form != null)
				return false;
		} else if (!form.equals(other.form))
			return false;
		if (last != other.last)
			return false;
		if (nights != other.nights)
			return false;
		if (sailingID == null) {
			if (other.sailingID != null)
				return false;
		} else if (!sailingID.equals(other.sailingID))
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
		return "OfferObject [sailingID=" + sailingID + ", shipname=" + shipname + ", deparCountry=" + deparCountry
				+ ", deparPort=" + deparPort + ", form=" + form + ", nights=" + nights + ", last=" + last + "]";
	}

}
