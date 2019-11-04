package entity;

public class PersonTableItem {
	private String tcid;
	private String tcfirst;
	private String tcsur;
	private String tcbirth;
	private String tcphone;
	private String tcemail;
	private String tcpass;
	public PersonTableItem(String tcid, String tcfirst, String tcsur, String tcbirth, String tcphone, String tcemail,
			String tcpass) {
		super();
		this.tcid = tcid;
		this.tcfirst = tcfirst;
		this.tcsur = tcsur;
		this.tcbirth = tcbirth;
		this.tcphone = tcphone;
		this.tcemail = tcemail;
		this.tcpass = tcpass;
	}
	public String getTcid() {
		return tcid;
	}
	public void setTcid(String tcid) {
		this.tcid = tcid;
	}
	public String getTcfirst() {
		return tcfirst;
	}
	public void setTcfirst(String tcfirst) {
		this.tcfirst = tcfirst;
	}
	public String getTcsur() {
		return tcsur;
	}
	public void setTcsur(String tcsur) {
		this.tcsur = tcsur;
	}
	public String getTcbirth() {
		return tcbirth;
	}
	public void setTcbirth(String tcbirth) {
		this.tcbirth = tcbirth;
	}
	public String getTcphone() {
		return tcphone;
	}
	public void setTcphone(String tcphone) {
		this.tcphone = tcphone;
	}
	public String getTcemail() {
		return tcemail;
	}
	public void setTcemail(String tcemail) {
		this.tcemail = tcemail;
	}
	public String getTcpass() {
		return tcpass;
	}
	public void setTcpass(String tcpass) {
		this.tcpass = tcpass;
	}
	@Override
	public String toString() {
		return "PersonTableItem [tcid=" + tcid + ", tcfirst=" + tcfirst + ", tcsur=" + tcsur + ", tcbirth=" + tcbirth
				+ ", tcphone=" + tcphone + ", tcemail=" + tcemail + ", tcpass=" + tcpass + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tcbirth == null) ? 0 : tcbirth.hashCode());
		result = prime * result + ((tcemail == null) ? 0 : tcemail.hashCode());
		result = prime * result + ((tcfirst == null) ? 0 : tcfirst.hashCode());
		result = prime * result + ((tcid == null) ? 0 : tcid.hashCode());
		result = prime * result + ((tcpass == null) ? 0 : tcpass.hashCode());
		result = prime * result + ((tcphone == null) ? 0 : tcphone.hashCode());
		result = prime * result + ((tcsur == null) ? 0 : tcsur.hashCode());
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
		PersonTableItem other = (PersonTableItem) obj;
		if (tcbirth == null) {
			if (other.tcbirth != null)
				return false;
		} else if (!tcbirth.equals(other.tcbirth))
			return false;
		if (tcemail == null) {
			if (other.tcemail != null)
				return false;
		} else if (!tcemail.equals(other.tcemail))
			return false;
		if (tcfirst == null) {
			if (other.tcfirst != null)
				return false;
		} else if (!tcfirst.equals(other.tcfirst))
			return false;
		if (tcid == null) {
			if (other.tcid != null)
				return false;
		} else if (!tcid.equals(other.tcid))
			return false;
		if (tcpass == null) {
			if (other.tcpass != null)
				return false;
		} else if (!tcpass.equals(other.tcpass))
			return false;
		if (tcphone == null) {
			if (other.tcphone != null)
				return false;
		} else if (!tcphone.equals(other.tcphone))
			return false;
		if (tcsur == null) {
			if (other.tcsur != null)
				return false;
		} else if (!tcsur.equals(other.tcsur))
			return false;
		return true;
	}







}
