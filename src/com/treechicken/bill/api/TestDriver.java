package com.treechicken.bill.api;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

public class TestDriver {

	public static void main(String[] args) throws IOException {
		
		Scanner reader = new Scanner(System.in);

		String username = "get yur own";
		String password = "same here, bro";
		
		Bill bill = new Bill(username, password);
		bill.parseFriends();
		
		User p = bill.parseUserFromPage("sejoo");
		

	}
}
