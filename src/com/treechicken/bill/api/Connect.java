package com.treechicken.bill.api;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Connects to BILL
 * 
 *
 */

public class Connect {

	private static Map<String, String> loginCookies;
	
	public static final String BILL_URL = "https://bill.mbhs.edu";

	public static Response res;
	
	public static String username;
	public static String password;

	/**
	 * Login to BILL
	 * 
	 * @param username
	 * @param password
	 * @throws IOException
	 *             by Jsoup
	 */

	public static void login(String u, String p) throws IOException{
		username = u;
		password = p;

		// Login response
		Connection.Response loginForm = Jsoup
				.connect(BILL_URL)
				.data("cookieexists", "false")
				.data("username", username, "password", password)
				.data("login", "Login").method(Connection.Method.GET).execute();
		
		res = loginForm;
		
		//If login fails, make form null
		Document testDoc = Jsoup
				.connect(BILL_URL + "/mail")
				.data("cookieexists", "false")
				.data("username", username, "password", password)
				.data("login", "Login").cookies(res.cookies()).post();
		
		if(testDoc.body().toString().indexOf("You could not be logged in!") >= 0){
			System.out.println("Login failed.");
			res = null;
		}
	}

	/**
	 * Uses response to navigate BILL. Needs to reuthenticate for each page???
	 * 
	 * @param subDir Page to navigate to
	 * @throws IOException
	 */
	public static Document navigate(String subDir) throws IOException{

		// Auth creds?
		Document doc1 = Jsoup
				.connect(BILL_URL + subDir)
				.data("cookieexists", "false")
				.data("username", username, "password", password)
				.data("login", "Login").cookies(res.cookies()).post();

		Map<String, String> loginCookies = res.cookies();

		// Goto subdir
		Document doc2 = Jsoup
				.connect(BILL_URL + subDir)
				.cookies(loginCookies)
				.post();
		
		
		return doc2;
	}

	/*
	 * 
	 * Connection.Response loginForm = Jsoup .connect(BILL_URL)
	 * .method(Connection.Method.GET) .execute();
	 * 
	 * Document document = Jsoup .connect(BILL_URL + "/mail")
	 * .data("cookieexists", "false").data("username", username, "password",
	 * password) .data("login", "Login") .cookies(loginForm.cookies()) .post();
	 * 
	 * System.out.println(document);
	 * 
	 * return document;
	 */

	/**
	 * Connects to a webpage using the cookies of the current session
	 * 
	 * @param url
	 *            to connect to
	 * @return response of webpage
	 * @throws IOException
	 *             called by Jsoup
	 */

	public static Response meTo(String url) throws IOException {
		return Jsoup.connect(url).timeout(0).cookies(loginCookies)
				.userAgent("Chrome/12.0.742.122").execute();
	}
}