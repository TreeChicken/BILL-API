package com.treechicken.bill.api;

import java.util.ArrayList;

/**
 * Student user
 * 
 *
 */

public class User {

	private String name;
	private String hrefName;
	private int year;
	private ArrayList<Course> courses;
	private boolean isFriend;
	
	public User(){
		name = "John Doe";
		hrefName = "jodoe";
		year = 1999;
		courses = new ArrayList<Course>();
		isFriend = false;
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
	public User(String nm, String hNm, int yr, ArrayList<Course> c, boolean iF){
		name = nm;
		hrefName = hNm;
		year = yr;
		courses = c;
		isFriend = iF;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	public boolean isFriend() {
		return isFriend;
	}

	public void setFriend(boolean isFriend) {
		this.isFriend = isFriend;
	}
	
	//To String
	public String toString(){
		return name;
	}
	
}
