package com.trms.controller;

import javax.servlet.http.HttpServletRequest;

public class HomeController {
	
	public static String home(HttpServletRequest req) {
		return "html/home.html";
	}

}
