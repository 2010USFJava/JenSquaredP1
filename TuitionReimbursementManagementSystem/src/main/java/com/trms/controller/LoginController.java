package com.trms.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trms.beans.Employee;
import com.trms.util.TRMSService;

public class LoginController {
	
static TRMSService serv = new TRMSService();
	
	public static String login(HttpServletRequest req) {
		if(!req.getMethod().equals("POST")) {
			return "html/index.html";
		}
		String email = req.getParameter("inputEmail");
		String password = req.getParameter("inputPassword");
		Employee e = serv.loginGetEmployee(email, password);
		if(e==null) {
			return serv.wrongCreds();
		}else {
			req.getSession().setAttribute("currentuser", e);
			return "home.master";
		}
	}
}
