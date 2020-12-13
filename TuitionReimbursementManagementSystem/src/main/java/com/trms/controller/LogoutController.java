package com.trms.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trms.util.LogThis;

public class LogoutController {
	
	public static String logout(HttpServletRequest req, HttpServletResponse res) {
//		Cookie ck = new Cookie("name","");
//		ck.setMaxAge(0);
//		res.addCookie(ck);
		LogThis.LogIt("info", "You have logged out.");
		return "html/logout.html";
	}

}
