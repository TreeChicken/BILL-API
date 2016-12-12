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
		
		System.out.println(bill.getNews());
		
		Student p = (Student)bill.parseUserFromPage("raweng");
		System.out.println(p.getCourses());
		
		while(true){
			float now = System.nanoTime();
			System.out.println(System.nanoTime() - now);
		}
		

	}
}
