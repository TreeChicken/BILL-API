package com.treechicken.bill.api;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TestDriver {

	public static void main(String[] args) throws IOException {
		
		Scanner reader = new Scanner(new File("authcreds.txt"));

		String username = reader.nextLine();
		String password = reader.nextLine();
		
		Bill bill = new Bill(username, password);
		
		Student p = (Student)bill.parseUserFromPage("naleung");
		System.out.println(p.getCourses());
		

	}
}
