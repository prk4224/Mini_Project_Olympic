package com.pcwk.library.domain;

import com.pcwk.cmn.DTO;

public class Olympic extends DTO {
	
	private String name; // 이름
	private int age; // 나이
	private String gender; // 성별
	private String mainev; // 주종목

	
	public Olympic() {
		super();
	}

	public Olympic(String name, int age, String gender, String mainev) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.mainev = mainev;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMainev() {
		return mainev;
	}

	public void setMainev(String mainev) {
		this.mainev = mainev;
	}

	@Override
	public String toString() {
		return "Olympic [name=" + name + ", age=" + age + ", gender=" + gender + ", mainev=" + mainev + "]";
	}

	
	
	


}
