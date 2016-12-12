package com.treechicken.bill.api;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Functions within BILL
 * 
 *
 */

public class Bill {

	User you;
	ArrayList<Course> courses;
	ArrayList<Student> friends;
	
	
	public Bill(String u, String p) throws IOException{
		Connect.login(u, p);
		courses = new ArrayList<Course>();
		friends = new ArrayList<Student>();
		
	}
	
	/**
	 * Parse profile
	 * @throws IOException 
	 */
	public void parseProfile() throws IOException{
		String homeBody = Connect.navigate("/home").body().toString();
		String[] homeBodyList = homeBody.split("\n");
		String inf = homeBodyList[7];
		
		inf = inf.substring(inf.indexOf("user/") + 5, inf.indexOf("</a>"));
		String hrefName = "";
		int index = 0;
		while(inf.charAt(index) != '"'){
			hrefName += inf.charAt(index);
			index++;
		}
		
		you = parseUserFromPage(hrefName);
		
	}
	
	/**
	 * Parses a user from a profile page
	 * @throws IOException 
	 */
	public User parseUserFromPage(String hrefName) throws IOException{
		
		String inf = Connect.navigate("/user/" + hrefName).body().toString();
		
		if(inf.indexOf("staff") >= 0){
			return parseTeacherFromPage(hrefName, inf);
		}
		else{
			return parseStudentFromPage(hrefName, inf);
		}
		
	}
	
	/**
	 * Creates a student from a profile page
	 * 
	 * @param hrefName
	 * @return
	 * @throws IOException
	 */
	private Student parseStudentFromPage(String hrefName, String inf){
		
		String name;
		int year;
		ArrayList<Course> courses;
		boolean isFriend;
		
		//Get name
		name = inf.substring(inf.indexOf("<h1>") + 4, inf.indexOf("</h1>"));
		
		//Get year
		year = Integer.parseInt(inf.substring(inf.indexOf("Class of") + 19, (inf.indexOf("Class of") + 23)));
		
		//Get courses
		courses = parseCourses(inf);
		
		//Checks friend
		isFriend = !(inf.indexOf("You are not friends") >= 0);
		
		return new Student(name, hrefName, year, courses, isFriend);
	}
	
	/**
	 * Parses courses from a users profile
	 * @throws IOException 
	 * 
	 */
	private ArrayList<Course> parseCourses(String inf){
	
		ArrayList<Course> courses = new ArrayList<Course>();
		String[] infList = inf.split("\n");
		
		for(int i = 0; i < infList.length; i ++){
			int courseIndex = infList[i].indexOf("pd.");
			//If a class is found, create the course
			if(courseIndex >= 0){
				
				String name;
				String hrefName;
				int period;
				String teacher;
				String room;
				
				//Get period
				period = Integer.parseInt(infList[i].substring(courseIndex + 3, courseIndex + 4));
				
				//Get courseName
				name = "";
				int nameIndex = infList[i + 2].indexOf("</a></td>") - 1;
				while(infList[i+2].charAt(nameIndex) != '>'){
					
					name = infList[i+2].charAt(nameIndex) + name;
					nameIndex--;
				}
				
				//Get course hrefName
				hrefName = infList[i+2].substring(infList[i+2].indexOf("/class/") + 7, infList[i+2].indexOf("/class/") + 24);
				
				//Get teacher
				teacher = "";
				int teacherIndex = infList[i + 3].indexOf("</a></td>") - 1;
				while(infList[i+3].charAt(teacherIndex) != '>'){
					
					teacher = infList[i+3].charAt(teacherIndex) + teacher;
					teacherIndex--;
				
				}
				
				//Get room
				room = "";
				int roomIndex = infList[i + 4].indexOf("</a></td>") - 1;
				while(infList[i+4].charAt(roomIndex) != '>'){
					
					room = infList[i+4].charAt(roomIndex) + room;
					roomIndex--;
				
				}
				
				Course course = new Course(name, hrefName, period, teacher, room);
				courses.add(course);
			}
		}
		
		return courses;
	}
	
	/**
	 * Creates a student from a profile page
	 * 
	 * @param hrefName
	 * @return
	 * @throws IOException
	 */
	private Teacher parseTeacherFromPage(String hrefName, String inf) throws IOException{
		
		String name;
		ArrayList<Course> courses;
		
		//Get name
		name = inf.substring(inf.indexOf("<h1>") + 4, inf.indexOf("</h1>"));
				
		//Get courses
		courses = parseCoursesTeacher(name, inf);
		
		return new Teacher(name, hrefName, courses);
	}
	
	/**
	 * Parses courses from a teachers profile
	 * @throws IOException 
	 * 
	 */
	private ArrayList<Course> parseCoursesTeacher(String tNm, String inf){

		ArrayList<Course> courses = new ArrayList<Course>();
		String[] infList = inf.split("\n");
		
		for(int i = 0; i < infList.length; i ++){
			int courseIndex = infList[i].indexOf("pd.");
			//If a class is found, create the course
			if(courseIndex >= 0){
				
				String name;
				String hrefName;
				int period;
				String teacher;
				String room;
				
				//Get period
				period = Integer.parseInt(infList[i].substring(courseIndex + 3, courseIndex + 4));
				
				//Get courseName
				name = "";
				int nameIndex = infList[i + 2].indexOf("</a></td>") - 1;
				while(infList[i+2].charAt(nameIndex) != '>'){
					
					name = infList[i+2].charAt(nameIndex) + name;
					nameIndex--;
				}
				
				//Get course hrefName
				hrefName = infList[i+2].substring(infList[i+2].indexOf("/class/") + 7, infList[i+2].indexOf("/class/") + 24);
				
				//Get teacher
				teacher = tNm;
				
				//Get room
				room = "";
				int roomIndex = infList[i + 4].indexOf("</a></td>") - 1;
				while(infList[i+4].charAt(roomIndex) != '>'){
					
					room = infList[i+4].charAt(roomIndex) + room;
					roomIndex--;
				
				}
				
				Course course = new Course(name, hrefName, period, teacher, room);
				courses.add(course);
			}
		}
		
		return courses;
	}
	
	/**
	 * Creates an arrayList of users that are friends
	 * 
	 * @throws IOException
	 */
	public void parseFriends() throws IOException{
		String friendBody = Connect.navigate("/friends").body().toString();
		String[] friendBodyList = friendBody.split("\n");
		
		//Go thru classes
		for(String line : friendBodyList){
			if(line.indexOf("<li><a href=\"/user/") >= 0){
				friends.add(parseFriend(line));
				System.out.println(line);
			}
		}
	}
	/**
	 * Creates a user from a line on friends
	 * @throws IOException 
	 * 
	 */
	private Student parseFriend(String inf) throws IOException{
		
		//Get href name
		String hrefName = inf.substring(inf.indexOf("user/") + 5, inf.indexOf("\">"));
		
		return (Student)parseUserFromPage(hrefName);
		
	}
	
	
	
}
