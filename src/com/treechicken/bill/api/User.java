package com.treechicken.bill.api;

import java.util.ArrayList;

/**
 * Student user
 * 
 *
 */

public class User {

	protected String name;
	protected String hrefName;
	
	public User(){
		name = "John Doe";
		hrefName = "jodoe";
	}

	/**
	 * Creates a new user
	 * 
	 * @param nm
	 * @param hNm
	 * @param yr
	 * @param c
	 * @param iF
	 */
	public User(String nm, String hNm){
		name = nm;
		hrefName = hNm;
	}
	
	//Getters and setters	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHrefName() {
		return hrefName;
	}

	public void setHrefName(String hrefName) {
		this.hrefName = hrefName;
	}
	
	//To String
	public String toString(){
		return name;
	}
	
}
