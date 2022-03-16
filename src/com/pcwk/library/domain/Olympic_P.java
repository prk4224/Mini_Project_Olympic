package com.pcwk.library.domain;

import com.pcwk.cmn.DTO;

public class Olympic_P extends DTO {
	
	private String name; // 이름
	private String parti;

	
	public Olympic_P() {
		super();
	}


	public Olympic_P(String name, String parti) {
		super();
		this.name = name;
		this.parti = parti;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getParti() {
		return parti;
	}


	public void setParti(String parti) {
		this.parti = parti;
	}


	@Override
	public String toString() {
		return "   | " + name +"\t" + parti + "\t";
	}
	
	


}
