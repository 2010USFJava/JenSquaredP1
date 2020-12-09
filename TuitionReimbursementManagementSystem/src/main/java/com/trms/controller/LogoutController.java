package com.trms.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutController {
	
	public static String logout(HttpServletRequest req, HttpServletResponse res) {
		Cookie ck = new Cookie("name","");
		res.addCookie(ck);
		return "html/logout.html";
	}

}
