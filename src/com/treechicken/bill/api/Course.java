package com.treechicken.bill.api;

/**
 * School course
 * 
 *
 */

public class Course {

	private String name;
	private String hrefName;
	private int period;
	private String teacher;
	private String room;
	
	public Course(){
		name = "ChemR&E";
		hrefName = "phamtasticmrpham";
		period = 10;
		teacher = "James Pham";
		room = "389";
	}
	
	public Course(String nm, String hNm, int p, String t, String r){
		name = nm;
		hrefName = hNm;
		period = p;
		teacher = t;
		room = r;
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

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String toString(){
		return period + ":" + name + ":" + teacher;
	}
	
}
