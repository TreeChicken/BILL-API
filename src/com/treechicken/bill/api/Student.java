package com.treechicken.bill.api;

import java.util.ArrayList;

public class Student extends User{

	private int year;
	private ArrayList<Course> courses;
	private boolean isFriend;
	
	
	public Student(){
		super();
		year = 1999;
		courses = new ArrayList<Course>();
		isFriend = false;
	}
	
	public Student(String nm, String hNm, int yr, ArrayList<Course> c, boolean iF){
		super(nm, hNm);
		year = yr;
		courses = c;
		isFriend = iF;
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
	
}
