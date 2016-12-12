package com.treechicken.bill.api;

import java.util.ArrayList;

/**
 * Creates a teacher
 * 
 *
 */

public class Teacher extends User{
	
	public ArrayList<Course> courses;
	
	public Teacher(){
		super();
		courses = new ArrayList<Course>();
	}
	
	public Teacher(String nm, String hNm, ArrayList<Course> c){
		super(nm, hNm);
		courses = c;
	}
	
	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

}
