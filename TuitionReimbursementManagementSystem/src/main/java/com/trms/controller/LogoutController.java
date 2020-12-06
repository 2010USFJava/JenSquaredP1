package com.trms.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class LogoutController {
	
	public static String logout(HttpServletRequest req) {
		Cookie ck = new Cookie("name","");
		return "html/logout.master";
	}

}
