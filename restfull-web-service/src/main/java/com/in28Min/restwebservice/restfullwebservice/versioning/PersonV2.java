package com.in28Min.restwebservice.restfullwebservice.versioning;

public class PersonV2 {

	private String fName;
	private String sName;
	@Override
	public String toString() {
		return "PersonV2 [fName=" + fName + ", sName=" + sName + "]";
	}
	public PersonV2(String fName, String sName) {
		super();
		this.fName = fName;
		this.sName = sName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}
	
	
}
